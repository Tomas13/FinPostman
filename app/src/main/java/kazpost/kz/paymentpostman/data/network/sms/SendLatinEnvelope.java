package kazpost.kz.paymentpostman.data.network.sms;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@NamespaceList({
        @Namespace( prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "sms", reference = "http://kazpost.kz/smsgate"),
})
@Root(name = "soapenv:Envelope", strict = false)
public class SendLatinEnvelope {

    @Element(name = "soapenv:Header", required = false)
    private Header Aheader;


    public static class Header {

        @Element(name = "soapenv:Username")
        String ausername;

        @Element(name = "soapenv:Password")
        String bpassword;

        public void setAusername(String ausername) {
            this.ausername = ausername;
        }

        public void setBpassword(String bpassword) {
            this.bpassword = bpassword;
        }
    }


    public Header getAheader() {
        return Aheader;
    }

    public void setAheader(Header aheader) {
        Aheader = aheader;
    }

    @Element(name = "soapenv:Body", required = false)
    private SendLatinBody latinBody;

    public SendLatinBody getSendLatinBody() {
        return latinBody;
    }

    public void setLatinBody(SendLatinBody latinBody) {
        this.latinBody = latinBody;
    }
}
