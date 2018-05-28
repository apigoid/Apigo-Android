package id.apigo.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mesosfer.DeleteCallback;
import com.mesosfer.FindCallback;
import com.mesosfer.GetCallback;
import com.mesosfer.LogOutCallback;
import com.mesosfer.MesosferException;
import com.mesosfer.MesosferObject;
import com.mesosfer.MesosferQuery;
import com.mesosfer.MesosferUser;
import com.mesosfer.SaveCallback;
import com.mesosfer.SendMessageCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.apigo.Message;
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
        findViewById(R.id.buttonSendMessageDirect).setOnClickListener(onClickListener);
        findViewById(R.id.buttonSendMessageBroadcast).setOnClickListener(onClickListener);


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Param param = null;
            Message message = null;

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
                case R.id.buttonSendMessageDirect:
                    message = new Message.BuilderDirect()
                            .setSender(MessageSender.createWithoutData(MessageSender.class, "SENDER_ID"))
                            .setRecipient(MessageRecipient.createWithoutData(MessageRecipient.class, "RECIPIENT_ID"))
                            .setContent("Test send sms from Android")
                            .setSandbox(true)
                            .setMessageType(MessageType.SMS)
                            .build();
                    break;
                case R.id.buttonSendMessageBroadcast:
                    message = new Message.BuilderBroadcast()
                            .setSender(MessageSender.createWithoutData(MessageSender.class, "SENDER_ID"))
                            .setGroup("EyroDev")
                            .setContent("Test broadcast sms from Android")
                            .setSandbox(true)
                            .setMessageType(MessageType.SMS)
                            .build();
                    break;
                case R.id.buttonSendMessageDirectTemplate:
                    message = new Message.BuilderDirectTemplate()
                            .setSender(MessageSender.createWithoutData(MessageSender.class, "SENDER_ID"))
                            .setRecipient(MessageRecipient.createWithoutData(MessageRecipient.class, "RECIPIENT_ID"))
                            .setTemplate(MessageTemplate.createWithoutData(MessageTemplate.class, "TEMPLATE_ID"))
                            .setSandbox(true)
                            .setMessageType(MessageType.SMS)
                            .build();
                    break;
                case R.id.buttonSendMessageBroadcastTemplate:
                    message = new Message.BuilderBroadcastTemplate()
                            .setSender(MessageSender.createWithoutData(MessageSender.class, "SENDER_ID"))
                            .setTemplate(MessageTemplate.createWithoutData(MessageTemplate.class, "TEMPLATE_ID"))
                            .setGroup("EyroDev")
                            .setSandbox(true)
                            .setMessageType(MessageType.SMS)
                            .build();
                    break;
            }

            if (param != null) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(Param.class.getSimpleName(), param);
                startActivity(intent);
            }

            if (message != null) {
                Toast.makeText(MainActivity.this, "Sending sms...", Toast.LENGTH_SHORT).show();
                message.sendMessageInBackground(new SendMessageCallback() {
                    @Override
                    public void done(List<MessageLog> logs, MesosferException e) {
                        if (e != null) {
                            Log.e("SendSMS", "Error sending message: " + e);
                            return;
                        }

                        Log.d("SendSMS", "Message sent: " + logs.toString());
                    }
                });
            }
        }
    };
}
