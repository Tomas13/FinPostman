package kazpost.kz.paymentpostman.data.network;

import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;
import rx.Observable;

/**
 * Created by root on 1/23/18.
 */

public interface ApiHelper {

    Observable<Envelope> checkPayment(CheckPaymentEnvelope checkPaymentEnvelope);
}
