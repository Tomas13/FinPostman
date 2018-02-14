package kazpost.kz.paymentpostman.data.network.getproviderbyphone;

/**
 * Created by root on 4/19/17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:GetProviderByPhoneRequest", strict = true)
public class GetProviderData {

    @Element(name = "sch:phone")
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

}
