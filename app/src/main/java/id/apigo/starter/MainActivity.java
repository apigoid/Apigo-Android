package id.apigo.starter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mesosfer.MesosferException;
import com.mesosfer.MesosferUser;
import com.mesosfer.SendBroadcastCallback;
import com.mesosfer.SendMessageCallback;

import java.util.HashMap;
import java.util.List;

import id.apigo.Message;
import id.apigo.MessageBroadcast;
import id.apigo.MessageLog;
import id.apigo.MessageRecipient;
import id.apigo.MessageSender;
import id.apigo.MessageTemplate;
import id.apigo.MessageType;

/**
 * Created by Apigo on 02/10/17.
 */

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.buttonSender).setOnClickListener(onClickListener);
    findViewById(R.id.buttonRecipient).setOnClickListener(onClickListener);
    findViewById(R.id.buttonTemplate).setOnClickListener(onClickListener);
    findViewById(R.id.buttonLog).setOnClickListener(onClickListener);
    findViewById(R.id.buttonSendMessageDirectTemplate).setOnClickListener(onClickListener);
    findViewById(R.id.buttonSendMessageBroadcastTemplate).setOnClickListener(onClickListener);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_logout) {
      new AlertDialog.Builder(this)
          .setTitle("Confirmation")
          .setMessage("Do you want to log out now?")
          .setNegativeButton(android.R.string.cancel, null)
          .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              MesosferUser.logOut();
              Intent intent = new Intent(MainActivity.this, SplashActivity.class);
              startActivity(intent);
              finish();
            }
          }).show();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Param param = null;

      switch (view.getId()) {
        case R.id.buttonSender:
          param = new Param<MessageSender>()
              .setaClass(MessageSender.class);
          break;
        case R.id.buttonRecipient:
          param = new Param<MessageRecipient>()
              .setaClass(MessageRecipient.class);
          break;
        case R.id.buttonTemplate:
          param = new Param<MessageTemplate>()
              .setaClass(MessageTemplate.class);
          break;
        case R.id.buttonLog:
          param = new Param<MessageLog>()
              .setaClass(MessageLog.class)
              .setCreate(false)
              .setUpdate(false)
              .setDelete(false);
          break;
        case R.id.buttonSendMessageDirectTemplate:
          Message message = new Message.BuilderTemplate()
              .setSender(MessageSender.createWithoutData(MessageSender.class, "5rDbpKgkAV"))
              .setRecipient("6281331134837")
              .setTemplate(MessageTemplate.createWithoutData(MessageTemplate.class, "QeE5pvQnXe"))
              .setParams(new HashMap<String, String>() {{
                put("kode", "abc123");
              }})
              .setMessageType(MessageType.SMS)
              .build();
          Toast.makeText(MainActivity.this, "Sending sms...", Toast.LENGTH_SHORT).show();
          message.sendMessageInBackground(new SendMessageCallback() {
            @Override
            public void done(MessageLog log, MesosferException e) {
              if (e != null) {
                Log.e("SendSMS", "Error sending message: " + e);
                return;
              }

              Log.d("SendSMS", "Message sent: " + log.toString());
            }
          });
          break;
        case R.id.buttonSendMessageBroadcastTemplate:
          MessageBroadcast broadcast = new MessageBroadcast.BuilderTemplate()
              .setSender(MessageSender.createWithoutData(MessageSender.class, "5rDbpKgkAV"))
              .setGroup("ITDev")
              .setTemplate(MessageTemplate.createWithoutData(MessageTemplate.class, "QeE5pvQnXe"))
              .setMessageType(MessageType.SMS)
              .build();
          broadcast.sendBroadcastInBackground(new SendBroadcastCallback() {
            @Override
            public void done(List<MessageLog> logs, MesosferException e) {
              if (e != null) {
                Log.e("SendBroadcast", "Error sending message: " + e);
                return;
              }

              Log.d("SendBroadcast", "Message sent: " + logs.toString());
            }
          });
          break;
      }

      if (param != null) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra(Param.class.getSimpleName(), param);
        startActivity(intent);
      }
    }
  };
}
