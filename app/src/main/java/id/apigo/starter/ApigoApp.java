package id.apigo.starter;

import android.app.Application;

import id.apigo.Apigo;
import id.apigo.LogLevel;

/**
 * Created by Apigo on 02/10/17.
 */

public class ApigoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Apigo.setLogLevel(LogLevel.VERBOSE);
        Apigo.initialize(this,
                "YOUR_SERVER_URL",
                "YOUR_APPLICATION_ID",
                "YOUR_CLIENT_KEY");
    }
}
