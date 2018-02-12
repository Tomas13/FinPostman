package kazpost.kz.paymentpostman.first;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.iid.InstanceID;
import com.jakewharton.rxbinding2.widget.RxAdapterView;

import java.util.LinkedHashMap;

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
    private LinkedHashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_payment);
        ButterKnife.bind(this);

        presenter = new CheckPaymentPresenter();
        addPresenter(presenter, this);

        initErrorMap();
        setSpinnerSelectionListener();
    }

    private void initErrorMap() {
        //Init ErrorMap to show correct error message
        String[] keys = this.getResources().getStringArray(R.array.error_keys);
        String[] values = this.getResources().getStringArray(R.array.error_values);
        map = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(keys.length, values.length); ++i) {
            map.put(keys[i], values[i]);
        }
    }

    private void setSpinnerSelectionListener() {
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

    private String getPayId() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String payId = "notInitialized";

        if (sharedPreferences.contains("payId")) {
            payId = sharedPreferences.getString("payId", "-1");
        } else {
            payId = InstanceID.getInstance(this).getId();
        }

        return payId;
    }

    private void upgradePayId() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        sharedPreferences.edit().putString("payId", InstanceID.getInstance(this).getId() + 1).apply();
    }


    @Override
    public void showCheckPaymentResult(String res) {
        showToast(res);
//        Toast toast = Toast.makeText(this, "check " + res, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.TOP, 0, 0);
//        toast.show();
    }

    @OnClick(R.id.btn_check_payment)
    public void onViewClicked() {
        presenter.checkPaymentRequest(System.currentTimeMillis(), 643,
                etAmount.getText().toString(), serviceInt, etAccount.getText().toString(),
                map);
    }

//    @Override
//    public void showLoading() {
//        indeterminateBar.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideLoading() {
//        indeterminateBar.setVisibility(View.INVISIBLE);
//
//    }
}
