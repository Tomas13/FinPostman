package kazpost.kz.paymentpostman.mvp;


import android.app.ProgressDialog;

import kazpost.kz.paymentpostman.CommonUtils;

public class MVPBaseActivity<V extends PresenterView> extends BaseActivity {



    private Presenter<V> presenter;

    protected void addPresenter(Presenter<V> presenter, V presenterView) {
        this.presenter = presenter;
        presenter.attachView(presenterView);
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
