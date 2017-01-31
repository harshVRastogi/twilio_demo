package com.example.cloudkinetics.contactsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter mPagerAdapter;
    private ArrayList<String> tags;
    private int lastPos;
    private int lastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (lastIndex == position && lastPos != position) {
                    if (position == 1) {
                        SentMessageFragment fragment = (SentMessageFragment) getSupportFragmentManager()
                                .findFragmentByTag(tags.get(1));
                        fragment.setNewData();
                    }
                }
                lastPos = position;
            }

            @Override
            public void onPageSelected(int position) {
                initializeTags();
                lastIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initializeTags() {
        if (tags == null) {
            tags = new ArrayList<>();
            List<Fragment> frags = getSupportFragmentManager().getFragments();
            for (int i = 0; i < frags.size(); i++) {
                tags.add(frags.get(i).getTag().toString());
            }
        }
    }
}
