package kazpost.kz.paymentpostman;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;

/**
 * Created by root on 1/19/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        if(BuildConfig.DEBUG) {
            initStrictMode();
        }
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    private void initStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
    }

}
