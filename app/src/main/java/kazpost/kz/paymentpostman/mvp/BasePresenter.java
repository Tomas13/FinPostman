package kazpost.kz.paymentpostman.mvp;

public abstract class BasePresenter<T extends MvpPresenter> {

    private T view;

    public void attachView(T view) {
        this.view = view;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public T getView() {
        return view;
    }

    public void detachView() {
        view = null;
    }

    public abstract void destroy();
}
