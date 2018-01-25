package kazpost.kz.paymentpostman.data.network.addofflinepaymentrequest;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:AddOfflinePaymentRequest", strict = false)
public class AddOfflinePaymentData {

    @Element(name = "sch:payId")
    private int ApayId;

    @Element(name = "sch:fromCurrency")
    private int BfromCurrency;

    @Element(name = "sch:fromAmount")
    private int CfromAmount;

    @Element(name = "sch:toCurrency")
    private int DtoCurrency;

    @Element(name = "sch:toAmount")
    private double EtoAmount;

    @Element(name = "sch:service")
    private int Fservice;

    @Element(name = "sch:account")
    private int Gaccount;

    @Element(name = "sch:recId")
    private int HrecId;

    @Element(name = "sch:date")
    private String Idate;


    public void setApayId(int apayId) {
        ApayId = apayId;
    }

    public void setBfromCurrency(int bfromCurrency) {
        BfromCurrency = bfromCurrency;
    }

    public void setCfromAmount(int cfromAmount) {
        CfromAmount = cfromAmount;
    }

    public void setDtoCurrency(int dtoCurrency) {
        DtoCurrency = dtoCurrency;
    }

    public void setEtoAmount(double etoAmount) {
        EtoAmount = etoAmount;
    }

    public void setFservice(int fservice) {
        Fservice = fservice;
    }

    public void setGaccount(int gaccount) {
        Gaccount = gaccount;
    }

    public void setHrecId(int hrecId) {
        HrecId = hrecId;
    }

    public void setIdate(String idate) {
        Idate = idate;
    }

    public int getApayId() {
        return ApayId;
    }

    public int getBfromCurrency() {
        return BfromCurrency;
    }

    public int getCfromAmount() {
        return CfromAmount;
    }

    public int getDtoCurrency() {
        return DtoCurrency;
    }

    public double getEtoAmount() {
        return EtoAmount;
    }

    public int getFservice() {
        return Fservice;
    }

    public int getGaccount() {
        return Gaccount;
    }

    public int getHrecId() {
        return HrecId;
    }

    public String getIdate() {
        return Idate;
    }
}
