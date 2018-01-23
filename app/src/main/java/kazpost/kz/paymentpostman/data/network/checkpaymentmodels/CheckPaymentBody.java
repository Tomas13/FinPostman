package kazpost.kz.paymentpostman.data.network.checkpaymentmodels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class CheckPaymentBody {

    @Element(name = "sch:CheckPaymentRequest", required = true)
    private CheckPaymentData checkPaymentData;

    public CheckPaymentData getCheckPaymentData() {
        return checkPaymentData;
    }

    public void setCheckPaymentData(CheckPaymentData checkPaymentData) {
        this.checkPaymentData = checkPaymentData;
    }

}
