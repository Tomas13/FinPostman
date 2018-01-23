package kazpost.kz.paymentpostman.first;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kazpost.kz.paymentpostman.Const;
import kazpost.kz.paymentpostman.data.network.NetworkService;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentBody;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentData;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.mvp.BaseActivity;
import kazpost.kz.paymentpostman.mvp.Presenter;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


/**
 * Created by root on 1/23/18.
 */

public class CheckPaymentPresenter extends Presenter<CheckView> {


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


    public void checkPaymentRequest() {

        int cacheSize = 10 * 1024 * 1024;
        File cacheDir = ((BaseActivity) getView()).getCacheDir();
        Cache cache = new Cache(cacheDir, cacheSize);

        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.BASE_URL_QIWI)
                .client(provideOkhttpClient(cache))
                .build();

        NetworkService networkService = retrofit.create(NetworkService.class);

        CheckPaymentEnvelope checkPaymentEnvelope = new CheckPaymentEnvelope();
        CheckPaymentData data = new CheckPaymentData();
        CheckPaymentBody body = new CheckPaymentBody();

        data.setApayId();
        data.setBfromCurrency();
        data.setCfromAmount();
        data.setDtoCurrency();
        data.setEtoAmount();
        data.setFservice();
        data.setGaccount();
        data.setHrecId();
        data.setIdate();


        body.setCheckPaymentData(data);
        networkService.checkPayment(checkPaymentEnvelope).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope1 -> {
                    envelope1.getBody().getCheckPaymentResponse().getCheckResult();
                }, throwable -> {
                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
                });

        getView().showCheckPaymentResult();
    }

    @Override
    public void destroy() {

    }

}
