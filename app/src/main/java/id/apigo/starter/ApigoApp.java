package id.apigo.starter;

import android.app.Application;

import id.apigo.Apigo;
import id.apigo.LogLevel;

/**
 * Created by Eyro on 02/10/17.
 */

public class ApigoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Apigo.setLogLevel(LogLevel.VERBOSE);
        Apigo.initialize(this,
                "http://116.206.196.88:8104/2/",
                "Xv7KWM",
                "M1sza0PMKro5sGs0fyLvoW9M0fjrwUpG");
    }
}
