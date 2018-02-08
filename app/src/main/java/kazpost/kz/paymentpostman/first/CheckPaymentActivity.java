package kazpost.kz.paymentpostman.first;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxAdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.paymentpostman.R;
import kazpost.kz.paymentpostman.mvp.MVPBaseActivity;

public class CheckPaymentActivity extends MVPBaseActivity<CheckView> implements CheckView {

    public static final String TAG = "CheckPaymentTag";

    CheckPaymentPresenter presenter;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.spinner_service)
    Spinner spinnerService;
    @BindView(R.id.indeterminateBar)
    ProgressBar indeterminateBar;

    private int serviceInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_payment);
        ButterKnife.bind(this);

        presenter = new CheckPaymentPresenter();

        addPresenter(presenter, this);

        RxAdapterView.itemSelections(spinnerService)
                .skipInitialValue()
                .subscribe(integer -> {
                    switch (integer) {
                        case 0:
                            serviceInt = 142;
                            break;  //beeline
                        case 1:
                            serviceInt = 431;
                            break;  //tele2
                        case 2:
                            serviceInt = 240;
                            break;  //altel
                        case 3:
                            serviceInt = 644;
                            break;  //activ
                        case 4:
                            serviceInt = 643;
                            break;  //kcell
                        case 5:
                            serviceInt = 7882;
                            break;  //qiwi
                    }
                });


    }

    private long getPayId() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        long payId = 0L;

        if (sharedPreferences.contains("payId")) {
            payId = sharedPreferences.getLong("payId", 1);
        }
        sharedPreferences.edit().putLong("payId", ++payId).apply();

        return payId;

    }


    @Override
    public void showCheckPaymentResult(String res) {
        Toast.makeText(this, "check " + res, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showCheckPaymentResult: called " + res);
    }

    @OnClick(R.id.btn_check_payment)
    public void onViewClicked() {
        presenter.checkPaymentRequest(getPayId(), 643, etAmount.getText().toString(), serviceInt, etAccount.getText().toString());
    }

    @Override
    public void showLoading() {
        indeterminateBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        indeterminateBar.setVisibility(View.INVISIBLE);

    }
}
