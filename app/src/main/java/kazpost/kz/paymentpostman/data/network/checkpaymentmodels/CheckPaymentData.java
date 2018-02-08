package kazpost.kz.paymentpostman.data.network.checkpaymentmodels;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

import java.util.Date;

@Root(name = "sch:CheckPaymentRequest", strict = true)
public class CheckPaymentData {

    @Element(name = "sch:payId")
    private String Aaa1;

    @Element(name = "sch:fromCurrency")
    private String Bbb2;

    @Element(name = "sch:fromAmount")
    private String Ccc3;

    @Element(name = "sch:toCurrency")
    private String Ddd4;

    @Element(name = "sch:toAmount")
    private String Eee5;

    @Element(name = "sch:service")
    private String Fff6;

    @Element(name = "sch:account")
    private String Ggg7;

    @Element(name = "sch:recId")
    private String Hhh8;

    @Element(name = "sch:date")
    private String Iii9;

    public String getA1payId() {
        return Aaa1;
    }

    public void setA1payId(String a1payId) {
        Aaa1 = a1payId;
    }

    public String getB2fromCurrency() {
        return Bbb2;
    }

    public void setB2fromCurrency(String b2fromCurrency) {
        Bbb2 = b2fromCurrency;
    }

    public String getfromAmount() {
        return Ccc3;
    }

    public void setfromAmount(String fromAmount) {
        this.Ccc3 = fromAmount;
    }

    public String gettoCurrency() {
        return Ddd4;
    }

    public void settoCurrency(String toCurrency) {
        this.Ddd4 = toCurrency;
    }

    public String gettoAmount() {
        return Eee5;
    }

    public void settoAmount(String toAmount) {
        this.Eee5 = toAmount;
    }

    public String getservice() {
        return Fff6;
    }

    public void setservice(String service) {
        this.Fff6 = service;
    }

    public String getaccount() {
        return Ggg7;
    }

    public void setaccount(String account) {
        this.Ggg7 = account;
    }

    public String getrecId() {
        return Hhh8;
    }

    public void setrecId(String recId) {
        this.Hhh8 = recId;
    }

    public String getdate() {
        return Iii9;
    }

    public void setdate(String date) {
        this.Iii9 = date;
    }
}
