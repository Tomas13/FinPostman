package kazpost.kz.paymentpostman.first;


import kazpost.kz.paymentpostman.mvp.PresenterView;

public interface CheckView extends PresenterView {
    void showCheckPaymentResult(String res);

    void showLoading();

    void hideLoading();
}
