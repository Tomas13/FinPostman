package kazpost.kz.paymentpostman.savepayment;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.paymentpostman.R;
import kazpost.kz.paymentpostman.mvp.BaseActivity;

public class FrameActivity extends BaseActivity<SavePaymentView> implements SavePaymentView {

    @BindView(R.id.textView2)
    TextView textView2;


    private SavePaymentPresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);

        presenter = new SavePaymentPresenterImpl();

        addPresenter(presenter, this);



    }


    @Override
    public void savePaymentSrv() {

    }

    @OnClick(R.id.button2)
    public void onViewClicked() {
        presenter.savePaymentSrvRequest();
    }
}
