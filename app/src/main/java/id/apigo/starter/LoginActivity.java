package id.apigo.starter;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mesosfer.LogInCallback;
import com.mesosfer.MesosferException;
import com.mesosfer.MesosferUser;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  public void handleLogin(View view) {
    final ProgressBar progressBar = findViewById(R.id.progressBar);
    TextInputEditText textUsername = findViewById(R.id.textUsername);
    TextInputEditText textPassword = findViewById(R.id.textPassword);

    String username = textUsername.getText().toString();
    String password = textPassword.getText().toString();

    if (TextUtils.isEmpty(username)) {
      textUsername.setError("Username is required");
      textUsername.requestFocus();
      return;
    }

    if (TextUtils.isEmpty(password)) {
      textPassword.setError("Password is required");
      textPassword.requestFocus();
      return;
    }

    progressBar.setVisibility(View.VISIBLE);
    MesosferUser.logInInBackground(username, password, new LogInCallback() {
      @Override
      public void done(MesosferUser mesosferUser, MesosferException e) {
        progressBar.setVisibility(View.INVISIBLE);
        if (e != null) {
          Log.e("LOGIN", "Login failed: " + e);
          return;
        }

        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      }
    });
  }
}
