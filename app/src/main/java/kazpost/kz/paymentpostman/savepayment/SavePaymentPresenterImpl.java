package kazpost.kz.paymentpostman.savepayment;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kazpost.kz.paymentpostman.Const;
import kazpost.kz.paymentpostman.data.network.NetworkService;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComBody;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComData;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComEnvelope;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderBody;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderByPhoneEnvelope;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderData;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvBody;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvData;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvEnvelope;
import kazpost.kz.paymentpostman.data.network.sms.SendLatinBody;
import kazpost.kz.paymentpostman.data.network.sms.SendLatinEnvelope;
import kazpost.kz.paymentpostman.mvp.BaseActivity;
import kazpost.kz.paymentpostman.mvp.BasePresenter;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by root on 2/12/18.
 */

public class SavePaymentPresenterImpl extends BasePresenter<SavePaymentView> implements SavePaymentPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String TAG = "Presn";

    private OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addNetworkInterceptor(new StethoInterceptor());
        client.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);
        client.cache(cache);
        return client.build();
    }




    public void getProviderByPhone(String account, LinkedHashMap<String, String> errorMap) {

        getView().showLoading();

        int cacheSize = 10 * 1024 * 1024;
        File cacheDir = ((BaseActivity) getView()).getCacheDir();
        Cache cache = new Cache(cacheDir, cacheSize);

        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Const.BASE_URL_QIWI)
                .client(provideOkhttpClient(cache))
                .build();

        NetworkService networkService = retrofit.create(NetworkService.class);

        GetProviderByPhoneEnvelope getProviderByPhoneEnvelope = new GetProviderByPhoneEnvelope();
        GetProviderBody body = new GetProviderBody();
        GetProviderData data = new GetProviderData();
        data.setPhone(account);
        body.setGetProviderData(data);
        getProviderByPhoneEnvelope.setGetProviderBody(body);

        Disposable disposable = networkService.getProviderByPhone(getProviderByPhoneEnvelope)
                .subscribeOn(Schedulers.io())
                .cache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope -> {

                    getView().hideLoading();

                    String respCode = envelope.getBody().getProviderResponse().getResponseInfo().getResponseCode();
                    String respText = envelope.getBody().getProviderResponse().getResponseInfo().getResponseText();

                    if (respCode.equals("0")) {
                        getView().onCalcPaymentComResult(Integer.valueOf(envelope.getBody().getProviderResponse().getProviderId()));
                    } else {
                        ((BaseActivity) getView()).showToast(errorMap.get(respCode));
                        getView().onCalcPaymentComResult(-1);
                    }

                    closeCache(cache);

                }, throwable -> {
                    getView().hideLoading();

                    ((BaseActivity) getView()).showToast(throwable.getMessage());
                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());

                    closeCache(cache);
                });

        compositeDisposable.add(disposable);
    }


    private void closeCache(Cache cache) {
        if (cache != null) {
            try {
                cache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
    }


}
