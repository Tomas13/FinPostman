package kazpost.kz.paymentpostman.data.network;


import io.reactivex.Observable;
import kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;
import kazpost.kz.paymentpostman.data.network.getpaymentstatusrequest.GetPaymentStatusEnvelope;
import kazpost.kz.paymentpostman.data.network.getproviderbyphone.GetProviderByPhoneEnvelope;
import kazpost.kz.paymentpostman.data.network.savepaymentsrvrequest.SavePaymentSrvEnvelope;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    Observable<kazpost.kz.paymentpostman.data.network.savepaymentsrvrequest.Envelope>
    savePaymentSrv(@Body SavePaymentSrvEnvelope requestEnvelope);


    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.Envelope> addOfflinePayment(
            @Body AddOfflinePaymentEnvelope requestEnvelope);

    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.getpaymentstatusrequest.Envelope> getPaymentStatus(
            @Body GetPaymentStatusEnvelope requestEnvelope);


    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.getproviderbyphone.Envelope> getProviderByPhone(
            @Body GetProviderByPhoneEnvelope requestEnvelope);
}
