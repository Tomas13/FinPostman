package kazpost.kz.paymentpostman.first;


import kazpost.kz.paymentpostman.mvp.MvpPresenter;

public interface CheckView extends MvpPresenter {

    void onGetProviderByPhoneResult(int res);

    void onCheckPaymentResult(String res);

    void onSavePaymentSrvResult();

    void onAddOfflinePaymentResult(String res);

    void showLoading();

    void hideLoading();

    void onCalcPaymentComResult(int i);

    void onGetPaymentStatus(String res);
}
