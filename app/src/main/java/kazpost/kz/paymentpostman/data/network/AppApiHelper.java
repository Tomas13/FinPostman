package kazpost.kz.paymentpostman.data.network;

import io.reactivex.Observable;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;

/**
 * Created by root on 1/23/18.
 */

public class AppApiHelper implements ApiHelper {

    NetworkService networkService;

    @Override
    public Observable<Envelope> checkPayment(CheckPaymentEnvelope checkPaymentEnvelope) {
        return networkService.checkPayment(checkPaymentEnvelope);
    }
}
