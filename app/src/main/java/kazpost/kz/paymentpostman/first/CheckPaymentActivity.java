package kazpost.kz.paymentpostman.first;

import android.os.Bundle;
import android.util.Log;

import java.io.File;

import kazpost.kz.paymentpostman.R;
import kazpost.kz.paymentpostman.mvp.MVPBaseActivity;

public class CheckPaymentActivity extends MVPBaseActivity<CheckView> implements CheckView {

    public static final String TAG = "CheckPaymentTag";

    CheckPaymentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_payment);

        presenter = new CheckPaymentPresenter();

        addPresenter(presenter, this);

        presenter.checkPaymentRequest();

    }

    @Override
    public void showCheckPaymentResult() {

        Log.d(TAG, "showCheckPaymentResult: called");
    }
}
