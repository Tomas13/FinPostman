package kazpost.kz.paymentpostman.data.network.savepaymentsrvrequest;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "sch:SavePaymentSrvRequest", strict = false)
public class SavePaymentSrvData {

    public static class SavePaymentSrvInfo {

        @Element(name = "UsrCode")
        String UsrCode;
        @Element(name = "DepCode")
        String DepCode;
        @Element(name = "MunDeaCode")
        String MunDeaCode;
        @Element(name = "Data")
        String Data;
        @Element(name = "FIO")
        String FIO;
        @Element(name = "RNN")
        String RNN;
        @Element(name = "ResidFl")
        String ResidFl;
        @Element(name = "PeniaFl")
        String PeniaFl;
        @Element(name = "KNP")
        String KNP;
        @Element(name = "AddDtl")
        String AddDtl;
        @Element(name = "ClientType")
        String ClientType;
        @Element(name = "PaymentType")
        String PaymentType;
        @Element(name = "pFinMonUsr")
        String pFinMonUsr;
        @Element(name = "pItemKey")
        String pItemKey;
        @Element(name = "Sum")
        String Sum;

        public void setUsrCode(String usrCode) {
            UsrCode = usrCode;
        }

        public void setDepCode(String depCode) {
            DepCode = depCode;
        }

        public void setMunDeaCode(String munDeaCode) {
            MunDeaCode = munDeaCode;
        }

        public void setData(String data) {
            Data = data;
        }

        public void setFIO(String FIO) {
            this.FIO = FIO;
        }

        public void setRNN(String RNN) {
            this.RNN = RNN;
        }

        public void setResidFl(String residFl) {
            ResidFl = residFl;
        }

        public void setPeniaFl(String peniaFl) {
            PeniaFl = peniaFl;
        }

        public void setKNP(String KNP) {
            this.KNP = KNP;
        }

        public void setAddDtl(String addDtl) {
            AddDtl = addDtl;
        }

        public void setClientType(String clientType) {
            ClientType = clientType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public void setpFinMonUsr(String pFinMonUsr) {
            this.pFinMonUsr = pFinMonUsr;
        }

        public void setpItemKey(String pItemKey) {
            this.pItemKey = pItemKey;
        }

        public void setSum(String sum) {
            Sum = sum;
        }
    }

    public static class MunSrv {

        @Element(name = "Code")
        private String code;

        @Element(name = "Value")
        private String value;

        @Element(name = "SrvID")
        private String srvId;

    }

    @Element(name = "sch:SavePaymentSrvInfo")
    private SavePaymentSrvInfo savePaymentSrvInfo;

    @ElementList(name = "sch:MunSrvs")
    private List<MunSrv> munSrvList;

    public void setSavePaymentSrvInfo(SavePaymentSrvInfo savePaymentSrvInfo) {
        this.savePaymentSrvInfo = savePaymentSrvInfo;
    }

    public void setMunSrvList(List<MunSrv> munSrvList) {
        this.munSrvList = munSrvList;
    }
}
