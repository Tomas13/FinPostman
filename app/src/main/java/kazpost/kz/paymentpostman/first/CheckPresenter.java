package kazpost.kz.paymentpostman.first;

import java.util.LinkedHashMap;

/**
 * Created by root on 2/13/18.
 */

public interface CheckPresenter {

    void checkPaymentRequest(long payId, int currency, String amount, int service, String account, LinkedHashMap<String, String> errorMap);
    void addOfflinePaymentRequest(long payId, int currency, String amount, int service, String account, LinkedHashMap<String, String> errorMap);
}
