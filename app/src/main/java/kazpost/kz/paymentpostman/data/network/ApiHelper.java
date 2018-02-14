package kazpost.kz.paymentpostman.data.network;

import io.reactivex.Observable;
import kazpost.kz.paymentpostman.data.network.addofflinepayment.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;
import kazpost.kz.paymentpostman.data.network.getpaymentstatus.GetPaymentStatusEnvelope;

/**
 * Created by root on 1/23/18.
 */

public interface ApiHelper {

    Observable<Envelope> checkPayment(CheckPaymentEnvelope checkPaymentEnvelope);

    Observable<kazpost.kz.paymentpostman.data.network.addofflinepayment.Envelope> addOfflinePayment(
            AddOfflinePaymentEnvelope addOfflinePaymentEnvelope);


    Observable<kazpost.kz.paymentpostman.data.network.getpaymentstatus.Envelope> getPaymentStatus(
            GetPaymentStatusEnvelope getPaymentStatusEnvelope);

}
