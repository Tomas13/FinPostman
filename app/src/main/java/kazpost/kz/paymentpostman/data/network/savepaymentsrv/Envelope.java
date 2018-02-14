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

        @Element(name = "ColvirReferenceId")
        int colvirRefenceId;

        @Element(name = "StatusDoc")
        String statusDoc;

        @Element(name = "StatusDocDescription")
        String statusDocDescription;

        @Element(name = "DocNumber")
        long docNumber;

        @Element(name = "ColvirOrdID")
        long colvirOrdID;

        @Element(name = "ColvirOrdDepID")
        int colvirOrdDepID;

        @Element(name = "CodeACR")
        String codeACR;

        @Element(name = "CodeBCR")
        String codeBCR;

        @Element(name = "NameBCR")
        String nameBCR;

        @Element(name = "NameCR")
        String nameCR;

        @Element(name = "RnnCR")
        long RnnCR;

        @Element(name = "KBE")
        int kBE;

        @Element(name = "KOD")
        int kOD;

        @Element(name = "KNP")
        int kNP;

        @Element(name = "Assign")
        String assign;

        @Element(name = "CshCode")
        String cshCode;

        @Element(name = "DepAdr")
        String depAdr;

        @Element(name = "DepCode")
        String depCode;
        @Element(name = "DepName")
        String depName;

        @Element(name = "Dord")
        String dord;
        @Element(name = "Nomer")
        String nomer;

        @Element(name = "RealTime")
        String realTime;
        @Element(name = "Ref")
        String ref;

        @Element(name = "SCOM")
        String sCON;

        @Element(name = "SumAll")
        int sumAll;

        @Element(name = "Total")
        String total;

        @Element(name = "UsrFio")
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