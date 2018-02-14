package kazpost.kz.paymentpostman.data.network.savepaymentsrv;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class SavePaymentSrvBody {

    @Element(name = "sch:SavePaymentSrvRequest", required = true)
    private SavePaymentSrvData savePaymentSrvData;

    public SavePaymentSrvData getSavePaymentSrvData() {
        return savePaymentSrvData;
    }

    public void setSavePaymentSrvData(SavePaymentSrvData savePaymentSrvData) {
        this.savePaymentSrvData = savePaymentSrvData;
    }

}
