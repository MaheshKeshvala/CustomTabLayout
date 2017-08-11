package com.mahesh.customtablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends AppCompatActivity {

    ArrayList<String> tabTitle = new ArrayList<>();
    @BindView(R.id.tlDates)
    TabLayout tlDates;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd \nMMM");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);

        setTitle();

        for (int i = 0; i < tabTitle.size(); i++) {
            TabLayout.Tab tab = tlDates.newTab().setText(tabTitle.get(i));
            tab.setCustomView(R.layout.tabadapter);
            findViewById(R.id.tvTabLayout);
            tlDates.addTab(tab);
        }

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tlDates.post(new Runnable() {
            @Override
            public void run() {
                tlDates.setupWithViewPager(viewpager);
            }
        });

    }

    public void setTitle() {

        for (int i = 1; i <= 5; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i);
            tabTitle.add(simpleDateFormat.format(calendar.getTime()));
        }
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private int NUM_ITEMS = 5;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            // if (lunchpostion == 0) {
            return FragmentMain.newInstance(position, tabTitle.get(position), "");
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }

    }


}
