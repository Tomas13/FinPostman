package kazpost.kz.paymentpostman;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by root on 1/19/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

}
