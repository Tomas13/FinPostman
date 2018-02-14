package kazpost.kz.paymentpostman.mvp;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment<V extends MvpPresenter> extends Fragment implements MvpView {


    public BaseFragment() {
        // Required empty public constructor
    }

    private BasePresenter<V> presenter;

    public void addPresenter(BasePresenter<V> presenter, V presenterView) {
        this.presenter = presenter;
        presenter.attachView(presenterView);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }

    @Override
    public void showLoading() {
        ((BaseActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((BaseActivity) getActivity()).hideLoading();
    }
}
