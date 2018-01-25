package kazpost.kz.paymentpostman.data.network;


import io.reactivex.Observable;
import kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by root on 1/23/18.
 */

public interface NetworkService {

    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<Envelope> checkPayment(@Body CheckPaymentEnvelope requestEnvelope);


    @POST("qiwi/qiwi.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest.Envelope> addOfflinePayment(
            @Body AddOfflinePaymentEnvelope requestEnvelope);


}
