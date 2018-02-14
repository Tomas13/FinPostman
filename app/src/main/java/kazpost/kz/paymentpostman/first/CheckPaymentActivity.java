package kazpost.kz.paymentpostman.first;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import kazpost.kz.paymentpostman.R;
import kazpost.kz.paymentpostman.mvp.BaseActivity;

import static kazpost.kz.paymentpostman.Const.PREF_NAME;
import static kazpost.kz.paymentpostman.Const.PREF_OPERATOR_CODE;
import static kazpost.kz.paymentpostman.Const.PREF_PAY_REC_ID;

public class CheckPaymentActivity extends BaseActivity<CheckView> implements CheckView {

    public static final String TAG = "CheckPaymentTag";
    @BindView(R.id.btn_addoffline_payment)
    Button btnAddofflinePayment;
    @BindView(R.id.img_operator)
    ImageView imgOperator;
    @BindView(R.id.tv_operator_name)
    TextView tvOperatorName;
    @BindView(R.id.tv_commision)
    TextView tvCommision;

    private CheckPaymentPresenter presenter;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.spinner_service)
    Spinner spinnerService;
    @BindView(R.id.indeterminateBar)
    ProgressBar indeterminateBar;

    private int serviceInt;
    private long payAndRecId;
    private SharedPreferences sharedPreferences;

    private LinkedHashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_payment);
        ButterKnife.bind(this);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        presenter = new CheckPaymentPresenter();
        addPresenter(presenter, this);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);


        initErrorMap();
        setSpinnerSelectionListener();

        initSumFieldForCommision();

        getPhone();

    }

    private void initSumFieldForCommision() {

        RxTextView.textChanges(etAmount)
                .skipInitialValue()
                .filter(charSequence -> charSequence.length() > 1)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sum -> presenter.calcPaymentCom("AST_GAUKHARO", sum.toString(), getAccountOperator(), map));
    }


    private void getPhone() {
        RxTextView.textChanges(etAccount).skipInitialValue()
                .map(charSequence -> charSequence.length() == 10)
                .map(aBoolean -> aBoolean ? Color.BLACK : Color.RED)
                .subscribe(color -> etAccount.setTextColor(color));


        RxTextView.textChanges(etAccount).skipInitialValue()
                .filter(charSequence -> charSequence.length() == 10)
                .subscribe(charSequence -> presenter.getProviderByPhone(charSequence.toString(), map));


        presenter.getPaymentStatus(String.valueOf(getPayAndRecId()), map);

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

    private Long getPayAndRecId() {
        return payAndRecId = sharedPreferences.getLong(PREF_PAY_REC_ID, 0);
    }

    private void savePayAndRecId() {
        sharedPreferences.edit().putLong(PREF_PAY_REC_ID, payAndRecId).apply();
    }

    private void saveAccountOperator(int operatorCode) {
        sharedPreferences.edit().putInt(PREF_OPERATOR_CODE, operatorCode).apply();
    }

    private int getAccountOperator() {
        return sharedPreferences.getInt(PREF_OPERATOR_CODE, 0);
    }

    @Override
    public void onGetProviderByPhoneResult(int res) {

        saveAccountOperator(res);

        imgOperator.setVisibility(View.VISIBLE);
        tvOperatorName.setVisibility(View.VISIBLE);

        switch (res) {
            case 142:
                spinnerService.post(() -> spinnerService.setSelection(0));//beeline
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_beeline));
                tvOperatorName.setText(getResources().getString(R.string.beeline));
                break;
            case 431:
                spinnerService.post(() -> spinnerService.setSelection(1));
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_tele2));
                tvOperatorName.setText(getResources().getString(R.string.tele2));
                break;  //tele2
            case 240:
                spinnerService.post(() -> spinnerService.setSelection(2));
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.altelg));
                tvOperatorName.setText(getResources().getString(R.string.altel));
                break;  //altel
            case 644:
                spinnerService.post(() -> spinnerService.setSelection(3));
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_activ));
                tvOperatorName.setText(getResources().getString(R.string.activ));
                break;  //activ
            case 643:
                spinnerService.post(() -> spinnerService.setSelection(4));
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_kcell));
                tvOperatorName.setText(getResources().getString(R.string.kcell));
                break;  //kcell
            case 7882:
                spinnerService.post(() -> spinnerService.setSelection(5));
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.qiwi));
                tvOperatorName.setText(getResources().getString(R.string.qiwi));
                break;  //qiwi
            case -1:
                imgOperator.setVisibility(View.INVISIBLE);
                tvOperatorName.setVisibility(View.INVISIBLE);
                break;
        }
    }


    @Override
    public void showCheckPaymentResult(String res) {
        showToast(res);

        if (res.equals("success")) {
            btnAddofflinePayment.setAlpha(1);
            btnAddofflinePayment.setClickable(true);
        }
    }

    @Override
    public void showAddOfflinePaymentResult(String res) {
        showToast(res);
    }

    @Override
    public void onCalcPaymentComResult(int i) {
        showToast("OnCalcPaymentResult" + i);
        tvCommision.setText(i + " тенге");
    }

    @Override
    public void onGetPaymentStatus(String result) {
        Toast.makeText(this, "onGetPayment " + result, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_check_payment)
    public void onViewClicked() {

        //generate random id for payId and recId fields and save to sharedpref
        payAndRecId = System.currentTimeMillis();
        savePayAndRecId();

        presenter.checkPaymentRequest(payAndRecId, 643,
                etAmount.getText().toString(), serviceInt, etAccount.getText().toString(), map);
    }

    @OnClick(R.id.btn_addoffline_payment)
    public void onBtnAddOfflineClicked() {
        presenter.addOfflinePaymentRequest(getPayAndRecId(), 643,
                etAmount.getText().toString(), serviceInt, etAccount.getText().toString(), map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences = null;
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
