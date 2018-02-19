package kazpost.kz.paymentpostman.savepayment;

import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.paymentpostman.R;
import kazpost.kz.paymentpostman.mvp.BaseActivity;

public class FrameActivity extends BaseActivity<SavePaymentView> implements SavePaymentView {

    @BindView(R.id.textView2)
    TextView textView2;


    private SavePaymentPresenterImpl presenter;

    LinkedHashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);

        presenter = new SavePaymentPresenterImpl();

        addPresenter(presenter, this);
initErrorMap();

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


    public void onCalcPaymentComResult(int value) {
        textView2.setText("Value is " + value);
    }

    @Override
    public void savePaymentSrv() {

    }

    @OnClick(R.id.button2)
    public void onViewClicked() {
//        presenter.getProviderByPhone("7072226642", map);
    }
}
