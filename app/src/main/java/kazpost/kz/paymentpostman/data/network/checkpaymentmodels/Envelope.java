package kazpost.kz.paymentpostman.data.network.checkpaymentmodels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
@NamespaceList({
        @Namespace( prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "ns2", reference = "http://webservices.kazpost.kz/qiwi/schema"),
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

        @Element(name = "CheckPaymentResponse", required = false)
        CheckPaymentResponse checkPaymentResponse;

        public CheckPaymentResponse getCheckPaymentResponse() {
            return checkPaymentResponse;
        }

        public void setCheckPaymentResponse(CheckPaymentResponse checkPaymentResponse) {
            this.checkPaymentResponse = checkPaymentResponse;
        }
    }


    public static class CheckPaymentResponse {

        @Element(name = "responseResult", required = false)
        String responseResult;

        @Element(name = "checkResult", required = false)
        String checkResult;

        @Element(name = "fatal", required = false)
        String fatal;

        @Element(name = "id", required = false)
        String id;

        @Element(name = "payResult", required = false)
        String payResult;

        @Element(name = "saved", required = false)
        String saved;

        @Element(name = "status", required = false)
        String status;

        @Element(name = "uid", required = false)
        String uid;

        @Element(name = "parserHost", required = false)
        String parserHost;

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        public String getResponseResult() {
            return responseResult;
        }

        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }

        public String getCheckResult() {
            return checkResult;
        }

        public String isFatal() {
            return fatal;
        }

        public String getId() {
            return id;
        }

        public String getPayResult() {
            return payResult;
        }

        public String isSaved() {
            return saved;
        }

        public String getStatus() {
            return status;
        }

        public String getUid() {
            return uid;
        }

        public String getParserHost() {
            return parserHost;
        }
    }

}