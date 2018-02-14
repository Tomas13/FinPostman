package kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class CalcPaymentComBody {

    @Element(name = "sch:CalcPaymentComRequest", required = true)
    private CalcPaymentComData calcPaymentComData;

    public CalcPaymentComData getCalcPaymentComData() {
        return calcPaymentComData;
    }

    public void setCalcPaymentComData(CalcPaymentComData calcPaymentComData) {
        this.calcPaymentComData = calcPaymentComData;
    }

}
