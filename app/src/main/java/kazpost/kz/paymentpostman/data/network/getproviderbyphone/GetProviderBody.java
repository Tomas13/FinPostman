package kazpost.kz.paymentpostman.data.network.getproviderbyphone;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class GetProviderBody {

    @Element(name = "sch:GetProviderByPhoneRequest", required = true)
    private GetProviderData getProviderData;

    public GetProviderData getGetProviderData() {
        return getProviderData;
    }

    public void setGetProviderData(GetProviderData getProviderData) {
        this.getProviderData = getProviderData;
    }

}
