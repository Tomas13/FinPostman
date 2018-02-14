package kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "ns2", reference = "http://webservices.kazpost.kz/qiwi/schema"),
})
public class Envelope {

    @Element(name = "Header", required = false)
    String header;

    @Element(name = "Body", required = false)
    Body body;

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String value) {
        this.header = value;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body value) {
        this.body = value;
    }

    public static class ResponseInfo {

        @Element(name = "ResponseCode", required = false)
        String responseCode;

        @Element(name = "ResponseText", required = false)
        String responseText;

        @Element(name = "ResponseGenTime", required = false)
        String responseGenTime;

        public String getResponseCode() {
            return this.responseCode;
        }

        public void setResponseCode(String value) {
            this.responseCode = value;
        }

        public String getResponseText() {
            return this.responseText;
        }

        public void setResponseText(String value) {
            this.responseText = value;
        }

        public String getResponseGenTime() {
            return this.responseGenTime;
        }

        public void setResponseGenTime(String value) {
            this.responseGenTime = value;
        }

    }


    public static class Body {

        @Element(name = "CalcPaymentComResponse", required = false)
        CalcPaymentResponse calcPaymentResponse;

        public CalcPaymentResponse getCalcPaymentResponse() {
            return calcPaymentResponse;
        }
    }

    public static class CalcPaymentResponse {

        @Element(name = "CmsAmount", required = false)
        String cmsAmount;

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        public String getCmsAmount() {
            return cmsAmount;
        }

        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }
    }

}