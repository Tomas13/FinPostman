package kazpost.kz.paymentpostman.data.network.addofflinepayment;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:AddOfflinePaymentRequest", strict = false)
public class AddOfflinePaymentData {

    @Element(name = "sch:payId")
    private String ApayId;

    @Element(name = "sch:fromCurrency")
    private String BfromCurrency;

    @Element(name = "sch:fromAmount")
    private String CfromAmount;

    @Element(name = "sch:toCurrency")
    private String DtoCurrency;

    @Element(name = "sch:toAmount")
    private String EtoAmount;

    @Element(name = "sch:service")
    private String Fservice;

    @Element(name = "sch:account")
    private String Gaccount;

    @Element(name = "sch:recId")
    private String HrecId;

    @Element(name = "sch:date")
    private String Idate;


    public void setApayId(String apayId) {
        ApayId = apayId;
    }

    public void setBfromCurrency(String bfromCurrency) {
        BfromCurrency = bfromCurrency;
    }

    public void setCfromAmount(String cfromAmount) {
        CfromAmount = cfromAmount;
    }

    public void setDtoCurrency(String dtoCurrency) {
        DtoCurrency = dtoCurrency;
    }

    public void setEtoAmount(String  etoAmount) {
        EtoAmount = etoAmount;
    }

    public void setFservice(String fservice) {
        Fservice = fservice;
    }

    public void setGaccount(String gaccount) {
        Gaccount = gaccount;
    }

    public void setHrecId(String hrecId) {
        HrecId = hrecId;
    }

    public void setIdate(String idate) {
        Idate = idate;
    }

}
