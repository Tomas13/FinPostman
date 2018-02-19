package kazpost.kz.paymentpostman.data.network.sms;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class SendLatinBody {

    @Element(name = "sms:send", required = false)
    private Element2 latinData;

    public Element2 getLatinData() {
        return latinData;
    }

    public void setLatinData(Element2 latinData) {
        this.latinData = latinData;
    }

    public static class Element2 {

        @Element(name = "element")
        InnerElement innerElement;

        public InnerElement getInnerElement() {
            return innerElement;
        }

        public void setInnerElement(InnerElement innerElement) {
            this.innerElement = innerElement;
        }
    }


    public static class InnerElement {

        @Element(name = "phoneNumber")
        String aPhoneNumber;

        @Element(name = "message")
        String bMessage;

        public void setaPhoneNumber(String aPhoneNumber) {
            this.aPhoneNumber = aPhoneNumber;
        }

        public void setbMessage(String bMessage) {
            this.bMessage = bMessage;
        }

    }
}
