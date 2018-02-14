package kazpost.kz.paymentpostman.data.network.savepaymentsrv;

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

        @Element(name = "sch:UsrCode")
        String aUsrCode;
        @Element(name = "sch:DepCode")
        String bDepCode;
        @Element(name = "sch:MunDeaCode")
        String cMunDeaCode;
        @Element(name = "sch:Data", required = false)
        String Data;
        @Element(name = "sch:FIO")
        String eFIO;
        @Element(name = "sch:RNN", required = false)
        String fRNN;
        @Element(name = "sch:ResidFl")
        String gResidFl;
        @Element(name = "sch:PeniaFl")
        String hPeniaFl;
        @Element(name = "sch:KNP")
        String iKNP;
        @Element(name = "sch:AddDtl", required = false)
        String jAddDtl;
        @Element(name = "sch:ClientType")
        String kClientType;
        @Element(name = "sch:PaymentType")
        String lPaymentType;
        @Element(name = "sch:pFinMonUsr", required = false)
        String mpFinMonUsr;
        @Element(name = "sch:pItemKey")
        String npItemKey;
        @Element(name = "sch:Sum")
        String oSum;

        public void setaUsrCode(String usrCode) {
            aUsrCode = usrCode;
        }

        public void setbDepCode(String depCode) {
            bDepCode = depCode;
        }

        public void setcMunDeaCode(String munDeaCode) {
            cMunDeaCode = munDeaCode;
        }

        public void setData(String data) {
            Data = data;
        }

        public void seteFIO(String FIO) {
            this.eFIO = FIO;
        }

        public void setfRNN(String RNN) {
            this.fRNN = RNN;
        }

        public void setgResidFl(String residFl) {
            gResidFl = residFl;
        }

        public void sethPeniaFl(String peniaFl) {
            hPeniaFl = peniaFl;
        }

        public void setiKNP(String KNP) {
            this.iKNP = KNP;
        }

        public void setjAddDtl(String addDtl) {
            jAddDtl = addDtl;
        }

        public void setkClientType(String clientType) {
            kClientType = clientType;
        }

        public void setlPaymentType(String paymentType) {
            lPaymentType = paymentType;
        }

        public void setmpFinMonUsr(String pFinMonUsr) {
            this.mpFinMonUsr = pFinMonUsr;
        }

        public void setnpItemKey(String pItemKey) {
            this.npItemKey = pItemKey;
        }

        public void setoSum(String sum) {
            oSum = sum;
        }
    }

    @Element(name = "sch:MunSrv")
    public static class MunSrv {

        @Element(name = "sch:Code")
        private String acode;

        @Element(name = "sch:Value")
        private String bvalue;

        @Element(name = "sch:SrvID")
        private String csrvId;

        public String getAcode() {
            return acode;
        }

        public void setAcode(String acode) {
            this.acode = acode;
        }

        public String getBvalue() {
            return bvalue;
        }

        public void setBvalue(String bvalue) {
            this.bvalue = bvalue;
        }

        public String getCsrvId() {
            return csrvId;
        }

        public void setCsrvId(String srvId) {
            this.csrvId = srvId;
        }
    }

    @Element(name = "sch:SavePaymentSrvInfo")
    private SavePaymentSrvInfo asavePaymentSrvInfo;

    @ElementList(name = "sch:MunSrvs")
    private List<MunSrv> bmunSrvList;

    public void setSavePaymentSrvInfo(SavePaymentSrvInfo savePaymentSrvInfo) {
        this.asavePaymentSrvInfo = savePaymentSrvInfo;
    }

    public void setMunSrvList(List<MunSrv> munSrvList) {
        this.bmunSrvList = munSrvList;
    }
}
