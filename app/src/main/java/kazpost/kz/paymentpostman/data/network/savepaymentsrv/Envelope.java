package kazpost.kz.paymentpostman.data.network.savepaymentsrv;

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

    public static class SavePaymentSrvResponse {

        @Element(name = "ColvirReferenceId", required = false)
        int colvirRefenceId;

        @Element(name = "StatusDoc", required = false)
        String statusDoc;

        @Element(name = "StatusDocDescription", required = false)
        String statusDocDescription;

        @Element(name = "DocNumber", required = false)
        long docNumber;

        @Element(name = "ColvirOrdID", required = false)
        long colvirOrdID;

        @Element(name = "ColvirOrdDepID", required = false)
        int colvirOrdDepID;

        @Element(name = "CodeACR", required = false)
        String codeACR;

        @Element(name = "CodeBCR", required = false)
        String codeBCR;

        @Element(name = "NameBCR", required = false)
        String nameBCR;

        @Element(name = "NameCR", required = false)
        String nameCR;

        @Element(name = "RnnCR", required = false)
        long RnnCR;

        @Element(name = "KBE", required = false)
        int kBE;

        @Element(name = "KOD", required = false)
        int kOD;

        @Element(name = "KNP", required = false)
        int kNP;

        @Element(name = "Assign", required = false)
        String assign;

        @Element(name = "CshCode", required = false)
        String cshCode;

        @Element(name = "DepAdr", required = false)
        String depAdr;

        @Element(name = "DepCode", required = false)
        String depCode;
        @Element(name = "DepName", required = false)
        String depName;

        @Element(name = "Dord", required = false)
        String dord;
        @Element(name = "Nomer", required = false)
        String nomer;

        @Element(name = "RealTime", required = false)
        String realTime;
        @Element(name = "Ref", required = false)
        String ref;

        @Element(name = "SCOM", required = false)
        String sCON;

        @Element(name = "SumAll", required = false)
        int sumAll;

        @Element(name = "Total", required = false)
        String total;

        @Element(name = "UsrFio", required = false)
        String usrFio;

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        public int getColvirRefenceId() {
            return colvirRefenceId;
        }

        public String getStatusDoc() {
            return statusDoc;
        }

        public String getStatusDocDescription() {
            return statusDocDescription;
        }

        public long getDocNumber() {
            return docNumber;
        }

        public long getColvirOrdID() {
            return colvirOrdID;
        }

        public int getColvirOrdDepID() {
            return colvirOrdDepID;
        }

        public String getCodeACR() {
            return codeACR;
        }

        public String getCodeBCR() {
            return codeBCR;
        }

        public String getNameBCR() {
            return nameBCR;
        }

        public String getNameCR() {
            return nameCR;
        }

        public long getRnnCR() {
            return RnnCR;
        }

        public int getkBE() {
            return kBE;
        }

        public int getkOD() {
            return kOD;
        }

        public int getkNP() {
            return kNP;
        }

        public String getAssign() {
            return assign;
        }

        public String getCshCode() {
            return cshCode;
        }

        public String getDepAdr() {
            return depAdr;
        }

        public String getDepCode() {
            return depCode;
        }

        public String getDepName() {
            return depName;
        }

        public String getDord() {
            return dord;
        }

        public String getNomer() {
            return nomer;
        }

        public String getRealTime() {
            return realTime;
        }

        public String getRef() {
            return ref;
        }

        public String getsCON() {
            return sCON;
        }

        public int getSumAll() {
            return sumAll;
        }

        public String getTotal() {
            return total;
        }

        public String getUsrFio() {
            return usrFio;
        }

        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }
    }

    public static class Body {

        @Element(name = "SavePaymentSrvResponse", required = false)
        SavePaymentSrvResponse savePaymentSrvResponse;

        public SavePaymentSrvResponse getSavePaymentSrvResponse() {
            return savePaymentSrvResponse;
        }

        public void setSavePaymentSrvResponse(SavePaymentSrvResponse savePaymentSrvResponse) {
            this.savePaymentSrvResponse = savePaymentSrvResponse;
        }
    }

}