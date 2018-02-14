package kazpost.kz.paymentpostman.data.network.getproviderbyphone;

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
        @Namespace( prefix = "sch", reference = "http://webservices.kazpost.kz/qiwi/schema"),
})
public class GetProviderByPhoneEnvelope {

    @Element(name = "soapenv:Header", required = false)
    private String Aheader = "";


    @Element(name = "soapenv:Body", required = false)
    private GetProviderBody getProviderBody;

    public GetProviderBody getGetProviderBody() {
        return getProviderBody;
    }

    public void setGetProviderBody(GetProviderBody getProviderBody) {
        this.getProviderBody = getProviderBody;
    }
}
