package com.example.cloudkinetics.contactsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
public class ContactDetailsActivity extends AppCompatActivity {

    private Contact c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(VectorDrawableCompat.
                create(getResources(), R.drawable.ic_arrow_white, getTheme()));
        Bundle b = getIntent().getExtras();
        if (b != null) {
            c = b.getParcelable("contact");
        }
        TextView tvName = (TextView) findViewById(R.id.tv_person_name);
        tvName.setText(c.getContactName());
        TextView mContactNumber = (TextView) findViewById(R.id.tv_contact_num);
        mContactNumber.setText(c.getContactNumber());
        ImageView ivSms = (ImageView) findViewById(R.id.iv_sms);
        ivSms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactDetailsActivity.this, ComposeMessageActivity.class);
                i.putExtra("contact", c);
                startActivity(i);
            }
        });
        ImageView icon = (ImageView) findViewById(R.id.icon);
        icon.getLayoutParams().width = (int) (getDisplayWidth() * 0.5f);
    }

    private int getDisplayWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
