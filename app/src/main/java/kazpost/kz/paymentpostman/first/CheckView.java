package kazpost.kz.paymentpostman.first;


import kazpost.kz.paymentpostman.mvp.MvpPresenter;

public interface CheckView extends MvpPresenter {

    void onGetProviderByPhoneResult(int res);

    void showCheckPaymentResult(String res);

    void showAddOfflinePaymentResult(String res);

    void showLoading();

    void hideLoading();

}
