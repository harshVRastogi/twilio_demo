package com.example.cloudkinetics.contactsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Harsh Rastogi on 1/28/2017.
 */
public class ComposeMessageActivity extends AppCompatActivity {
    private Contact c;
    private String msg = "Hi. Your OTP is: ";
    private RelativeLayout mProgressBar;
    private EditText etMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(VectorDrawableCompat.create(getResources(),
                R.drawable.ic_arrow_white, getTheme()));
        Bundle b = getIntent().getExtras();
        if (b == null) {
            finish();
			return;
        } else {
            c = b.getParcelable("contact");
        }
        TextView tvContactName = (TextView) findViewById(R.id.tv_contact_name);
        tvContactName.setText(c.getContactName());
        etMsg = (EditText) findViewById(R.id.et_msg);
        String randomNumber = String.valueOf(new Random().nextInt(1000000));
        String msgBody = msg + randomNumber;
        etMsg.setText(msgBody);
        mProgressBar = (RelativeLayout) findViewById(R.id.progress_bar);
        ImageView ivSend = (ImageView) findViewById(R.id.iv_send);
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etMsg.getText().toString().trim())) {
                    return;
                }
                showProgressBar();
                MessageApi.call( getCallback(),etMsg.getText().toString());
            }
        }

            );
    }

    private void insertMessageToDB(Message m) {
        MyDatabase db = new MyDatabase(this);
        db.insertMessage(m);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ComposeMessageActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setBlankText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etMsg.setText("");
            }
        });
    }
	
	private Callback getCallback(){
		 return new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        hideProgressBar();
                        if (e instanceof ConnectException || e instanceof UnknownHostException) {
                            showToast("Internet not connected");
                        } else {
                            showToast("Message not sent");
                        }
                    }
					@Override
					public void onResponse (Call call, Response response)throws IOException {
						String resStr = response.body().string();
						Log.d("Response",resStr);
						try {
							JSONObject object = new JSONObject(resStr);
							if(object.has("sid")){
								Message m = new Message();
								m.setReceiverName(c.getContactName());
								m.setReceiverNumber(c.getContactNumber());
								m.setSentTimestamp(System.currentTimeMillis());
								m.setMessageBody(etMsg.getText().toString());
								insertMessageToDB(m);
								showToast("Message sent");
								setBlankText();
							} else {
								showToast("Message not sent");
							}

						} catch (JSONException e) {
							e.printStackTrace();

						}finally {
							hideProgressBar();
						}

					}
				}
	}
}
