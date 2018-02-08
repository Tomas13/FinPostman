package kazpost.kz.paymentpostman.data.network.getpaymentstatusrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class GetPaymentStatusBody {

    @Element(name = "sch:GetPaymentStatusRequest", required = true)
    private GetPaymentStatusData getPaymentStatusData;

    public GetPaymentStatusData getGetPaymentStatusData() {
        return getPaymentStatusData;
    }

    public void setGetPaymentStatusData(GetPaymentStatusData getPaymentStatusData) {
        this.getPaymentStatusData = getPaymentStatusData;
    }

}
