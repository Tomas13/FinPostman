package kazpost.kz.paymentpostman.first;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kazpost.kz.paymentpostman.Const;
import kazpost.kz.paymentpostman.data.network.NetworkService;
import kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.AddOfflinePaymentBody;
import kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.AddOfflinePaymentData;
import kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentBody;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentData;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.mvp.BaseActivity;
import kazpost.kz.paymentpostman.mvp.Presenter;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

    //    Запрос на проверку возможности оплаты (check) запрос
    public void checkPaymentRequest(double payId, int currency, String amount, int service, String account) {

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

        CheckPaymentEnvelope checkPaymentEnvelope = new CheckPaymentEnvelope();
        CheckPaymentData data = new CheckPaymentData();
        CheckPaymentBody body = new CheckPaymentBody();




        Date date = null;

        SimpleDateFormat formatter3 = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.US);

        try {
            date = formatter3.parse(String.valueOf(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        data.setA1payId(Double.toString(payId));
        data.setB2fromCurrency(Integer.toString(currency));
        data.setfromAmount(amount);
        data.settoCurrency(Integer.toString(currency));
        data.settoAmount(amount);
        data.setservice(Integer.toString(service));
        data.setaccount(account);
        data.setrecId(Double.toString(payId));
        data.setdate(String.valueOf(date));


        body.setCheckPaymentData(data);
        checkPaymentEnvelope.setCheckPaymentBody(body);


        networkService.checkPayment(checkPaymentEnvelope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope1 -> {
                    getView().hideLoading();
                    getView().showCheckPaymentResult(envelope1.getBody().getCheckPaymentResponse().getResponseInfo().getResponseText());
                }, throwable -> {
                    getView().hideLoading();
                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
                });

//        getView().showCheckPaymentResult();
    }


    //<!--  запроса на создание платежа -->
//    public void AddOfflinePaymentRequest() {
//
//        int cacheSize = 10 * 1024 * 1024;
//        File cacheDir = ((BaseActivity) getView()).getCacheDir();
//        Cache cache = new Cache(cacheDir, cacheSize);
//
//        Strategy strategy = new AnnotationStrategy();
//        Serializer serializer = new Persister(strategy);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(Const.BASE_URL_QIWI)
//                .client(provideOkhttpClient(cache))
//                .build();
//
//        NetworkService networkService = retrofit.create(NetworkService.class);
//
//        AddOfflinePaymentEnvelope addOfflinePaymentEnvelope = new AddOfflinePaymentEnvelope();
//        AddOfflinePaymentData data = new AddOfflinePaymentData();
//        AddOfflinePaymentBody body = new AddOfflinePaymentBody();
//
//        data.setApayId();
//        data.setBfromCurrency();
//        data.setCfromAmount();
//        data.setDtoCurrency();
//        data.setEtoAmount();
//        data.setFservice();
//        data.setGaccount();
//        data.setHrecId();
//        data.setIdate();
//
//        body.setAddOfflinePaymentData(data);
//
//        networkService.addOfflinePayment(addOfflinePaymentEnvelope)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(envelope -> {
//                    envelope.getBody().getAddOfflinePaymentResponse().getPaymentResult();
//                }, throwable -> {
//                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
//                });
//
//        getView().showCheckPaymentResult();
//    }


//    public void AddOfflinePaymentRequest() {
//
//        int cacheSize = 10 * 1024 * 1024;
//        File cacheDir = ((BaseActivity) getView()).getCacheDir();
//        Cache cache = new Cache(cacheDir, cacheSize);
//
//        Strategy strategy = new AnnotationStrategy();
//        Serializer serializer = new Persister(strategy);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(Const.BASE_URL_QIWI)
//                .client(provideOkhttpClient(cache))
//                .build();
//
//        NetworkService networkService = retrofit.create(NetworkService.class);
//
//        AddOfflinePaymentEnvelope addOfflinePaymentEnvelope = new AddOfflinePaymentEnvelope();
//        AddOfflinePaymentData data = new AddOfflinePaymentData();
//        AddOfflinePaymentBody body = new AddOfflinePaymentBody();
//
//        data.setApayId();
//        data.setBfromCurrency();
//        data.setCfromAmount();
//        data.setDtoCurrency();
//        data.setEtoAmount();
//        data.setFservice();
//        data.setGaccount();
//        data.setHrecId();
//        data.setIdate();
//
//        body.setAddOfflinePaymentData(data);
//
//        networkService.addOfflinePayment(addOfflinePaymentEnvelope)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(envelope -> {
//                    envelope.getBody().getAddOfflinePaymentResponse().getPaymentResult();
//                }, throwable -> {
//                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
//                });
//
//        getView().showCheckPaymentResult();
//    }


    @Override
    public void destroy() {

    }

}
