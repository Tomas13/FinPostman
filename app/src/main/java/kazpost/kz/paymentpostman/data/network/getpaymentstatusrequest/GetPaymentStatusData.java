package kazpost.kz.paymentpostman.data.network.getpaymentstatusrequest;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:GetPaymentStatusRequest", strict = false)
public class GetPaymentStatusData {

    @Element(name = "sch:payId")
    private int ApayId;

    public void setApayId(int apayId) {
        ApayId = apayId;
    }

}
