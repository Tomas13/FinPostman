package kazpost.kz.paymentpostman.data.network;


import io.reactivex.Observable;
import kazpost.kz.paymentpostman.data.network.addofflinepayment.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.CalcPaymentComEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;
import kazpost.kz.paymentpostman.data.network.getpaymentstatus.GetPaymentStatusEnvelope;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderByPhoneEnvelope;
import kazpost.kz.paymentpostman.data.network.savepaymentsrv.SavePaymentSrvEnvelope;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by root on 1/23/18.
 */

public interface NetworkService {


    @POST("qiwi/endpoint")
    @Headers("Content-Type: text/xml")
    Observable<Envelope> checkPayment(@Body CheckPaymentEnvelope requestEnvelope);

    @POST("wfpayment_off/endpoint")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.savepaymentsrv.Envelope>
    savePaymentSrv(@Body SavePaymentSrvEnvelope requestEnvelope);


    @POST("wfoperation/endpoint")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr.Envelope>
    calcPaymentCom(@Body CalcPaymentComEnvelope requestEnvelope);

    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.addofflinepayment.Envelope> addOfflinePayment(
            @Body AddOfflinePaymentEnvelope requestEnvelope);

    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.getpaymentstatus.Envelope> getPaymentStatus(
            @Body GetPaymentStatusEnvelope requestEnvelope);


    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.getproviderbyphone.Envelope> getProviderByPhone(
            @Body GetProviderByPhoneEnvelope requestEnvelope);
}
