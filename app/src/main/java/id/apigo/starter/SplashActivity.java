package id.apigo.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mesosfer.MesosferUser;

import bolts.Continuation;
import bolts.Task;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    Task.delay(1500).continueWith(new Continuation<Void, Void>() {
      @Override
      public Void then(Task<Void> task) {
        Class<?> cls = MainActivity.class;
        if (MesosferUser.getCurrentUser() == null) {
          cls = LoginActivity.class;
        }

        Intent intent = new Intent(SplashActivity.this, cls);
        startActivity(intent);
        finish();

        return null;
      }
    });
  }
}
