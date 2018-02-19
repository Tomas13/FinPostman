package kazpost.kz.paymentpostman.data.network.sms;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
public class Envelope {

    @Element(name = "Body", required = false)
    Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body value) {
        this.body = value;
    }

    public static class Body {

        @Element(name = "sendResponse", required = false)
        SendUnicodeResponse providerResponse;

        public SendUnicodeResponse getProviderResponse() {
            return providerResponse;
        }
    }


    public static class Element1 {
        @Element(name = "smsId", required = false)
        String aSmsId;

        @Element(name = "status", required = false)
        String bStatus;

        public String getaSmsId() {
            return aSmsId;
        }

        public String getbStatus() {
            return bStatus;
        }
    }

    public static class SendUnicodeResponse {

        @Element(name = "element", required = false)
        Element1 element1;

        public Element1 getElement1() {
            return element1;
        }


    }

}