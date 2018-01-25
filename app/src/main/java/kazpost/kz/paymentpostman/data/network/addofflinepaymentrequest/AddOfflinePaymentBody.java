package kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class AddOfflinePaymentBody {

    @Element(name = "sch:AddOfflinePaymentRequest", required = true)
    private AddOfflinePaymentData addOfflinePaymentData;

    public AddOfflinePaymentData getAddOfflinePaymentData() {
        return addOfflinePaymentData;
    }

    public void setAddOfflinePaymentData(AddOfflinePaymentData addOfflinePaymentData) {
        this.addOfflinePaymentData = addOfflinePaymentData;
    }

}
