package kazpost.kz.paymentpostman.data.network.savepaymentsrv;

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
        @Namespace( prefix = "sch", reference = "http://webservices.kazpost.kz/wfpayment/schema"),
})
public class SavePaymentSrvEnvelope {

    @Element(name = "soapenv:Header", required = false)
    private String header = "";


    @Element(name = "soapenv:Body", required = false)
    private SavePaymentSrvBody savePaymentSrvBody;

    public SavePaymentSrvBody getSavePaymentSrvBody() {
        return savePaymentSrvBody;
    }

    public void setSavePaymentSrvBody(SavePaymentSrvBody savePaymentSrvBody) {
        this.savePaymentSrvBody = savePaymentSrvBody;
    }
}
