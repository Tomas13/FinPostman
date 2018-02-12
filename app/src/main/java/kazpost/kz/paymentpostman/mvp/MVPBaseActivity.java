package kazpost.kz.paymentpostman.mvp;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import kazpost.kz.paymentpostman.CommonUtils;

public class MVPBaseActivity<V extends PresenterView> extends AppCompatActivity implements MvpView {


    private ProgressDialog mProgressDialog;

    private Presenter<V> presenter;

    protected void addPresenter(Presenter<V> presenter, V presenterView) {
        this.presenter = presenter;
        presenter.attachView(presenterView);
    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);

        //trying to increase the toast font
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(18);


        toast.setText(msg);
        toast.show();
    }

    @Override
    public void showLoading() {
        hideLoading();

        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
        }
        super.onDestroy();
    }
}
