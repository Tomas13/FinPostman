package kazpost.kz.paymentpostman.first;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.tv_result_sum)
    TextView tvResultSum;
    @BindView(R.id.btn_calc_commission)
    Button btnCalcCommission;

    private CheckPaymentPresenter presenter;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.indeterminateBar)
    ProgressBar indeterminateBar;

    private long payAndRecId;
    private SharedPreferences sharedPreferences;
    private int commission = 0;

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
        initSumFieldForCommision();
        getPhone();
    }

    private void initSumFieldForCommision() {

//        RxTextView.textChanges(etAmount)
//                .skipInitialValue()
//                .filter(charSequence -> charSequence.length() > 1)
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(sum -> presenter.calcPaymentCom("AST_GAUKHARO", sum.toString(), getAccountOperator(), map));

        RxTextView.textChanges(etAmount)
                .skipInitialValue()
                .subscribe(charSequence -> {
                    if (charSequence.length() > 0) {
                        btnCalcCommission.setAlpha(1);
                        btnCalcCommission.setEnabled(true);
                    } else {
                        btnCalcCommission.setAlpha(0.5F);
                        btnCalcCommission.setEnabled(false);
                    }
                });
    }


    private void getPhone() {
        RxTextView.textChanges(etAccount).skipInitialValue()
                .map(charSequence -> charSequence.length() == 10)
                .map(aBoolean -> aBoolean ? Color.rgb(0, 153, 51) : Color.RED)
                .subscribe(color -> etAccount.setTextColor(color));


        RxTextView.textChanges(etAccount).skipInitialValue()
                .filter(charSequence -> charSequence.length() == 10)
                .subscribe(charSequence -> presenter.getProviderByPhone(charSequence.toString(), map));


        //TODO move this call somewhere else
//        presenter.getPaymentStatus(String.valueOf(getPayAndRecId()), map);

    }

    @Override
    public void onGetProviderByPhoneResult(int res) {

        saveAccountOperator(res);

        imgOperator.setVisibility(View.VISIBLE);
        tvOperatorName.setVisibility(View.VISIBLE);

        switch (res) {
            case 142:
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_beeline));
                tvOperatorName.setText(getResources().getString(R.string.beeline));
                break;
            case 431:
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_tele2));
                tvOperatorName.setText(getResources().getString(R.string.tele2));
                break;  //tele2
            case 240:
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.altelg));
                tvOperatorName.setText(getResources().getString(R.string.altel));
                break;  //altel
            case 644:
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_activ));
                tvOperatorName.setText(getResources().getString(R.string.activ));
                break;  //activ
            case 643:
                imgOperator.setImageDrawable(getResources().getDrawable(R.drawable.ic_kcell));
                tvOperatorName.setText(getResources().getString(R.string.kcell));
                break;  //kcell
            case 7882:
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
    public void onCheckPaymentResult(String res) {
//        showToast(res);

        if (res.equals("success")) {
            btnAddofflinePayment.setAlpha(1);
            btnAddofflinePayment.setEnabled(true);
        }
    }

    @Override
    public void onGetPaymentStatus(String result) {
        Toast.makeText(this, "onGetPayment " + result, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_addoffline_payment)
    public void onBtnAddOfflineClicked() {
        presenter.savePaymentSrvRequest(String.valueOf(getAccountOperator()), etAccount.getText().toString(),
                etAmount.getText().toString(), String.valueOf(getPayAndRecId()), String.valueOf(commission), map);


    }

    @Override
    public void onSavePaymentSrvResult() {

        presenter.addOfflinePaymentRequest(getPayAndRecId(), 643,
                etAmount.getText().toString(), getAccountOperator(), etAccount.getText().toString(), map);

    }

    @Override
    public void onAddOfflinePaymentResult(String res) {
//        showToast(res);

        presenter.getPaymentStatus(String.valueOf(getPayAndRecId()), map);
    }

    @OnClick(R.id.btn_calc_commission)
    public void onCalcCommissionClicked() {
        presenter.calcPaymentCom("AST_GAUKHARO", etAmount.getText().toString(), getAccountOperator(), map);
    }

    @Override
    public void onCalcPaymentComResult(int i) {
        tvCommision.setText(i + " тенге");
        commission = i;

        int amount = Integer.valueOf(etAmount.getText().toString());
        String res = String.valueOf(amount + commission);
        tvResultSum.setText(res + " тенге");
        checkPayment();
    }

    private void checkPayment() {
        //generate random id for payId and recId fields and save to sharedpref
        payAndRecId = System.currentTimeMillis();
        savePayAndRecId();

        presenter.checkPaymentRequest(payAndRecId, 643,
                etAmount.getText().toString(), getAccountOperator(), etAccount.getText().toString(), map);
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


    public void showErrorDialog(String message) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message);

        builder.setCancelable(false);
//        builder.setPositiveButton("Да", (dialog, which) -> super.onBackPressed());
        builder.setNegativeButton("OK", ((dialog, which) -> dialog.dismiss()));

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences = null;
    }

    @OnClick(R.id.btn_send_check)
    public void onViewClicked() {
        presenter.sendLatinSms("test111", "test111", "87072226642", "Hello man");

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
