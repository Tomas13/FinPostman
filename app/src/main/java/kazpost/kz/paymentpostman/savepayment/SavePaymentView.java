package kazpost.kz.paymentpostman.savepayment;

import kazpost.kz.paymentpostman.mvp.MvpPresenter;
import kazpost.kz.paymentpostman.mvp.MvpView;

/**
 * Created by root on 2/12/18.
 */

public interface SavePaymentView extends MvpPresenter{

    void savePaymentSrv();

    void onCalcPaymentComResult(int value);

    void showLoading();

    void hideLoading();
}
