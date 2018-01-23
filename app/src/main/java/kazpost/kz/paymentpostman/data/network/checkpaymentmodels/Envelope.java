package kazpost.kz.paymentpostman.data.network.checkpaymentmodels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
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

    public static class CheckPaymentResponse {

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        @Element(name = "checkResult", required = false)
        int checkResult;

        @Element(name = "fatal", required = false)
        boolean fatal;

        @Element(name = "id", required = false)
        int id;

        @Element(name = "payResult", required = false)
        int payResult;

        @Element(name = "saved", required = false)
        boolean saved;

        @Element(name = "status", required = false)
        int status;

        @Element(name = "uid", required = false)
        double uid;

        @Element(name = "parserHost", required = false)
        String parserHost;


        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }

        public int getCheckResult() {
            return checkResult;
        }

        public boolean isFatal() {
            return fatal;
        }

        public int getId() {
            return id;
        }

        public int getPayResult() {
            return payResult;
        }

        public boolean isSaved() {
            return saved;
        }

        public int getStatus() {
            return status;
        }

        public double getUid() {
            return uid;
        }

        public String getParserHost() {
            return parserHost;
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

}