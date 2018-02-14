package kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Envelope", strict = false)
@NamespaceList({
        @Namespace( prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "sch", reference = "http://webservices.kazpost.kz/wfoperation/schema"),
})
public class CalcPaymentComEnvelope {

    @Element(name = "soapenv:Header", required = false)
    private String Aheader = "";


    @Element(name = "soapenv:Body", required = false)
    private CalcPaymentComBody calcPaymentComBody;

    public CalcPaymentComBody getCalcPaymentComBody() {
        return calcPaymentComBody;
    }

    public void setCalcPaymentComBody(CalcPaymentComBody calcPaymentComBody) {
        this.calcPaymentComBody = calcPaymentComBody;
    }
}
