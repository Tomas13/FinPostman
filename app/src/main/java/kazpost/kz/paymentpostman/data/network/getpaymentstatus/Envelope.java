package kazpost.kz.paymentpostman.data.network.getpaymentstatus;

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

    public static class GetPaymentStatusResponse {

        @Element(name = "responseResult", required = false)
        String responseResult;

        @Element(name = "statusResult", required = false)
        String statusResult;

        @Element(name = "resultDscr", required = false)
        String resultDscr;

        @Element(name = "date", required = false)
        String date;

        @Element(name = "fatal", required = false)
        boolean fatal;

        @Element(name = "id", required = false)
        String id;

        @Element(name = "payResult", required = false)
        String payResult;

        @Element(name = "status", required = false)
        String status;

        @Element(name = "uid", required = false)
        double uid;

        @Element(name = "parserHost", required = false)
        String parserHost;

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        public String getResultDscr() {
            return resultDscr;
        }

        public String getResponseResult() {
            return responseResult;
        }

        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }

        public String getStatusResult() {
            return statusResult;
        }

        public String getDate() {
            return date;
        }

        public boolean isFatal() {
            return fatal;
        }

        public String getId() {
            return id;
        }

        public String getPayResult() {
            return payResult;
        }

        public String getStatus() {
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

        @Element(name = "GetPaymentStatusResponse", required = false)
        GetPaymentStatusResponse getPaymentStatusResponse;

        public GetPaymentStatusResponse getGetPaymentStatusResponse() {
            return getPaymentStatusResponse;
        }

        public void setGetPaymentStatusResponse(GetPaymentStatusResponse getPaymentStatusResponse) {
            this.getPaymentStatusResponse = getPaymentStatusResponse;
        }
    }

}