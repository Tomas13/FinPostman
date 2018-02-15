package kazpost.kz.paymentpostman.savepayment;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kazpost.kz.paymentpostman.Const;
import kazpost.kz.paymentpostman.data.network.NetworkService;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvBody;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvData;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvEnvelope;
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
    public void savePaymentSrvRequest() {

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

        SavePaymentSrvEnvelope savePaymentSrvEnvelope = new SavePaymentSrvEnvelope();
        SavePaymentSrvData data = new SavePaymentSrvData();
        SavePaymentSrvBody body = new SavePaymentSrvBody();

        SavePaymentSrvData.SavePaymentSrvInfo srvInfo = new SavePaymentSrvData.SavePaymentSrvInfo();
        srvInfo.setaUsrCode("ASTLUSH");
        srvInfo.setbDepCode("000000");
        srvInfo.setcMunDeaCode("5006");
        srvInfo.seteFIO("Abay");
        srvInfo.setgResidFl("1");  //1 - resident, 0 - nonresident
        srvInfo.sethPeniaFl("0");
        srvInfo.setiKNP("852");
        srvInfo.setkClientType("100");
        srvInfo.setlPaymentType("1");
        srvInfo.setnpItemKey("47637");
        srvInfo.setoSum("500");

        ArrayList<SavePaymentSrvData.MunSrv> munSrvList = new ArrayList<>();
        SavePaymentSrvData.MunSrv munSrvCellphone = new SavePaymentSrvData.MunSrv();
        SavePaymentSrvData.MunSrv munSrvAmount = new SavePaymentSrvData.MunSrv();
        SavePaymentSrvData.MunSrv munSrvCommision = new SavePaymentSrvData.MunSrv();

        munSrvCellphone.setAcode("CELLPHONE");
        munSrvCellphone.setBvalue("+77072226642");
        munSrvCellphone.setCsrvId("503");

        munSrvAmount.setAcode("AMOUNT");
        munSrvAmount.setBvalue("500");
        munSrvAmount.setCsrvId("503");

        munSrvCommision.setAcode("AW_CMS");
        munSrvCommision.setBvalue("50");
        munSrvCommision.setCsrvId("503");


        munSrvList.add(munSrvCellphone);
        munSrvList.add(munSrvAmount);
        munSrvList.add(munSrvCommision);

        data.setMunSrvList(munSrvList);
        data.setSavePaymentSrvInfo(srvInfo);

        body.setSavePaymentSrvData(data);

        savePaymentSrvEnvelope.setSavePaymentSrvBody(body);


       /* Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String formatted = format1.format(cal.getTime());*/


        networkService.savePaymentSrv(savePaymentSrvEnvelope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope1 -> {
                    getView().hideLoading();
                    String respCode = envelope1.getBody().getSavePaymentSrvResponse().getResponseInfo().getResponseCode();
                    String respText = envelope1.getBody().getSavePaymentSrvResponse().getResponseInfo().getResponseText();

                    if (respCode.equals("0")) {
                        ((BaseActivity) getView()).showToast(respText);
                    } else {
//                        ((BaseActivity) getView()).showToast(errorMap.get(respCode));
                        ((BaseActivity) getView()).showToast(respCode);
                    }

                }, throwable -> {
                    getView().hideLoading();
                    ((BaseActivity) getView()).showToast(throwable.getMessage());
                    Log.d(TAG, "checkPaymentRequest: " + throwable.getMessage());
                });

//        getView().showCheckPaymentResult();
    }

    @Override
    public void destroy() {

    }


}
