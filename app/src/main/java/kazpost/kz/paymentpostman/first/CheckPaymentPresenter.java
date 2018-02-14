package kazpost.kz.paymentpostman.first;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kazpost.kz.paymentpostman.Const;
import kazpost.kz.paymentpostman.data.network.NetworkService;
import kazpost.kz.paymentpostman.data.network.addofflinepayment.AddOfflinePaymentBody;
import kazpost.kz.paymentpostman.data.network.addofflinepayment.AddOfflinePaymentData;
import kazpost.kz.paymentpostman.data.network.addofflinepayment.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComBody;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComData;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentBody;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentData;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderBody;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderByPhoneEnvelope;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderData;
import kazpost.kz.paymentpostman.mvp.BaseActivity;
import kazpost.kz.paymentpostman.mvp.BasePresenter;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


/**
 * Created by root on 1/23/18.
 */

public class CheckPaymentPresenter extends BasePresenter<CheckView> implements CheckPresenter {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

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


    @Override
    //    Запрос на проверку возможности оплаты (check) запрос
    public void checkPaymentRequest(long payId, int currency, String amount, int service, String account, LinkedHashMap<String, String> errorMap) {

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


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String formatted = format1.format(cal.getTime());


        data.setA1payId(Long.toString(payId));
        data.setB2fromCurrency(Integer.toString(currency));
        data.setfromAmount(amount);
        data.settoCurrency(Integer.toString(currency));
        data.settoAmount(amount);
        data.setservice(Integer.toString(service));
        data.setaccount(account);
        data.setrecId(Long.toString(payId));
        data.setdate(formatted);

        body.setCheckPaymentData(data);
        checkPaymentEnvelope.setCheckPaymentBody(body);

        Disposable disposable = networkService.checkPayment(checkPaymentEnvelope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope1 -> {
                    getView().hideLoading();
                    String respCode = envelope1.getBody().getCheckPaymentResponse().getResponseInfo().getResponseCode();
                    String respText = envelope1.getBody().getCheckPaymentResponse().getResponseInfo().getResponseText();

                    if (respCode.equals("0")) {
                        getView().showCheckPaymentResult(respText);
                    } else {
                        getView().showCheckPaymentResult(errorMap.get(respCode));
                    }
                    closeCache(cache);


                }, throwable -> {
                    getView().hideLoading();
                    getView().showCheckPaymentResult(throwable.getMessage());
                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
                    closeCache(cache);

                });

        compositeDisposable.add(disposable);

//        getView().showCheckPaymentResult();
    }

    public void addOfflinePaymentRequest(long payId, int currency, String amount, int service, String account, LinkedHashMap<String, String> errorMap) {

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

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String formatted = format1.format(cal.getTime());


        AddOfflinePaymentEnvelope addOfflinePaymentEnvelope = new AddOfflinePaymentEnvelope();
        AddOfflinePaymentData data = new AddOfflinePaymentData();
        AddOfflinePaymentBody body = new AddOfflinePaymentBody();

        data.setApayId(Long.toString(payId));
        data.setBfromCurrency(Integer.toString(currency));
        data.setCfromAmount(amount);
        data.setDtoCurrency(Integer.toString(currency));
        data.setEtoAmount(amount);
        data.setFservice(Integer.toString(service));
        data.setGaccount(account);
        data.setHrecId(Long.toString(payId));
        data.setIdate(formatted);

        body.setAddOfflinePaymentData(data);
        addOfflinePaymentEnvelope.setAddOfflinePaymentBody(body);

        Disposable disposable = networkService.addOfflinePayment(addOfflinePaymentEnvelope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope -> {
//                    getView().showAddOfflinePaymentResult();

                    getView().hideLoading();

                    String respCode = envelope.getBody().getAddOfflinePaymentResponse().getResponseInfo().getResponseCode();
                    String respText = envelope.getBody().getAddOfflinePaymentResponse().getResponseInfo().getResponseText();

                    if (respCode.equals("0")) {
                        getView().showAddOfflinePaymentResult(respText);
                    } else {
                        getView().showAddOfflinePaymentResult(errorMap.get(respCode));
                    }


                    ((BaseActivity) getView()).showToast(envelope.getBody().getAddOfflinePaymentResponse().getPaymentResult() + "");
                    closeCache(cache);

                }, throwable -> {

                    getView().hideLoading();

                    ((BaseActivity) getView()).showToast(throwable.getMessage());
                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
                    closeCache(cache);

                });

        compositeDisposable.add(disposable);
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope -> {
//                    getView().showAddOfflinePaymentResult();

                    getView().hideLoading();

                    String respCode = envelope.getBody().getProviderResponse().getResponseInfo().getResponseCode();
                    String respText = envelope.getBody().getProviderResponse().getResponseInfo().getResponseText();

                    if (respCode.equals("0")) {
                        getView().onGetProviderByPhoneResult(Integer.valueOf(envelope.getBody().getProviderResponse().getProviderId()));
                    } else {
                        ((BaseActivity) getView()).showToast(errorMap.get(respCode));
                        getView().onGetProviderByPhoneResult(-1);
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

    public void calcPaymentCom(String login, String sum, int accountOperator, LinkedHashMap<String, String> errorMap) {

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


        CalcPaymentComEnvelope calcPaymentComEnvelope = new CalcPaymentComEnvelope();
        CalcPaymentComBody body = new CalcPaymentComBody();
        CalcPaymentComData data = new CalcPaymentComData();
        data.setaUserCode(login);
        data.setbMundeaCode("5249");
        data.setcSum(sum);

        CalcPaymentComData.TrfAttr trfAttr = new CalcPaymentComData.TrfAttr();
        CalcPaymentComData.TrfAttrList trfAttrList = new CalcPaymentComData.TrfAttrList();
        trfAttrList.setCode("KOMPL_USLUGA");
        trfAttrList.setValue(String.valueOf(accountOperator));
        trfAttr.setTrfAttrList(trfAttrList);
        data.setdTrfAttr(trfAttr);

        body.setCalcPaymentComData(data);
        calcPaymentComEnvelope.setCalcPaymentComBody(body);

        Disposable disposable = networkService.calcPaymentCom(calcPaymentComEnvelope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope -> {

                    getView().hideLoading();

                    String respCode = envelope.getBody().getCalcPaymentResponse().getResponseInfo().getResponseCode();
                    String respText = envelope.getBody().getCalcPaymentResponse().getResponseInfo().getResponseText();

                    if (respText.equals("")){
                        ((BaseActivity) getView()).showToast("Нет комисии");
                    }

                    if (respCode.equals("0")) {
                        getView().onCalcPaymentComResult(Integer.valueOf(envelope.getBody().getCalcPaymentResponse().getCmsAmount()));
                    } else {
                        ((BaseActivity) getView()).showToast(respText);
                        getView().onCalcPaymentComResult(0);
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

