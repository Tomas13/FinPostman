package kazpost.kz.paymentpostman.data.network.calcpaymentcommissionr;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:CalcPaymentComRequest", strict = true)
public class CalcPaymentComData {

    @Element(name = "sch:UserCode", required = false)
    private String aUserCode;
    @Element(name = "sch:MundeaCode", required = false)
    private String bMundeaCode;
    @Element(name = "sch:Sum", required = false)
    private String cSum;
    @Element(name = "sch:TrfAttr", required = false)
    private TrfAttr dTrfAttr;


    public static class TrfAttr{
        @Element(name = "sch:TrfAttrList", required = false)
        private TrfAttrList TrfAttrList;

        public TrfAttrList getTrfAttrList() {
            return TrfAttrList;
        }

        public void setTrfAttrList(TrfAttrList trfAttrList) {
            TrfAttrList = trfAttrList;
        }
    }

    public static class TrfAttrList{
        @Element(name = "sch:CODE", required = false)
        private String code;

        @Element(name = "sch:VALUE", required = false)
        private String value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getaUserCode() {
        return aUserCode;
    }

    public void setaUserCode(String aUserCode) {
        this.aUserCode = aUserCode;
    }

    public String getbMundeaCode() {
        return bMundeaCode;
    }

    public void setbMundeaCode(String bMundeaCode) {
        this.bMundeaCode = bMundeaCode;
    }

    public String getcSum() {
        return cSum;
    }

    public void setcSum(String cSum) {
        this.cSum = cSum;
    }

    public TrfAttr getdTrfAttr() {
        return dTrfAttr;
    }

    public void setdTrfAttr(TrfAttr dTrfAttr) {
        this.dTrfAttr = dTrfAttr;
    }
}
