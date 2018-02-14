package kazpost.kz.paymentpostman.data.network;

import io.reactivex.Observable;
import kazpost.kz.paymentpostman.data.network.addofflinepayment.AddOfflinePaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.CheckPaymentEnvelope;
import kazpost.kz.paymentpostman.data.network.checkpaymentmodels.Envelope;
import kazpost.kz.paymentpostman.data.network.getpaymentstatus.GetPaymentStatusEnvelope;

/**
 * Created by root on 1/23/18.
 */

public class AppApiHelper implements ApiHelper {

    NetworkService networkService;

    @Override
    public Observable<Envelope> checkPayment(CheckPaymentEnvelope checkPaymentEnvelope) {
        return networkService.checkPayment(checkPaymentEnvelope);
    }

    @Override
    public Observable<kazpost.kz.paymentpostman.data.network.addofflinepayment.Envelope> addOfflinePayment(
            AddOfflinePaymentEnvelope addOfflinePaymentEnvelope) {
        return networkService.addOfflinePayment(addOfflinePaymentEnvelope);
    }

    @Override
    public Observable<kazpost.kz.paymentpostman.data.network.getpaymentstatus.Envelope> getPaymentStatus(
            GetPaymentStatusEnvelope getPaymentStatusEnvelope) {
        return networkService.getPaymentStatus(getPaymentStatusEnvelope);
    }
}
