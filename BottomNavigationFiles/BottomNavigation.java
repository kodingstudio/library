package com.haris.ecommerce.presentationutil.ui.activityutil;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.haris.proscanner.MyApplication;
import com.haris.proscanner.R;
import com.haris.proscanner.presentationutil.ui.adapterutil.SimplePagerAdapter;
import com.haris.proscanner.presentationutil.ui.fragmentutil.AccountFragment;
import com.haris.proscanner.presentationutil.ui.fragmentutil.FilesFragment;
import com.haris.proscanner.presentationutil.ui.fragmentutil.HomeFragment;
import com.haris.proscanner.presentationutil.ui.fragmentutil.PremiumFragment;
import com.haris.proscanner.presentationutil.ui.objectutil.PagerTabObject;
import com.haris.proscanner.util.Constant;
import com.haris.proscanner.util.Utility;

import java.util.ArrayList;

public class BottomNavigation02 extends AppCompatActivity {
    ArrayList<PagerTabObject> pagerList = new ArrayList<>();
    TabLayout tabMain;
    ViewPager2 viewPagerLockable;
    SimplePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation02);

        //add data into fragment list

        pagerList.clear();
        pagerList.add(new PagerTabObject()
                .setTitle(Utility.getStringFromRes(this, R.string.home))
                .setIcon(R.drawable.ic_menu_home_light)
                .setSelectedIcon(R.drawable.ic_menu_home_bold)
                .setFragment(new HomeFragment()));

        pagerList.add(new PagerTabObject()
                .setTitle(Utility.getStringFromRes(this, R.string.files))
                .setIcon(R.drawable.ic_menu_folder_light)
                .setSelectedIcon(R.drawable.ic_menu_folder)
                .setFragment(new FilesFragment()));

        pagerList.add(new PagerTabObject()
                .setTitle(Utility.getStringFromRes(this, R.string.premium))
                .setIcon(R.drawable.ic_menu_premium_light)
                .setSelectedIcon(R.drawable.ic_menu_premium_bold)
                .setFragment(new PremiumFragment()));

        pagerList.add(new PagerTabObject()
                .setTitle(Utility.getStringFromRes(this, R.string.account))
                .setIcon(R.drawable.ic_menu_profile_light)
                .setSelectedIcon(R.drawable.ic_menu_profile_bold)
                .setFragment(new AccountFragment()));


        //init. Pager & Tablayout

        tabMain = findViewById(R.id.tab_main);
        viewPagerLockable = findViewById(R.id.view_pager_lockable);
        viewPagerLockable.setUserInputEnabled(false);
        viewPagerLockable.setCurrentItem(4);

        // init. pager adapter
        pagerAdapter = new SimplePagerAdapter(getSupportFragmentManager(), getLifecycle());

        // add data into viewpager
        for (int i = 0; i < pagerList.size(); i++) {
            pagerAdapter.addFragment(pagerList.get(i).getFragment());
        }

        // set the adapter to viewpager
        viewPagerLockable.setAdapter(pagerAdapter);

        // attach the viewpager with tab layout
        TabLayoutMediator layoutMediator = new TabLayoutMediator(tabMain, viewPagerLockable, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        layoutMediator.attach();

        // customize the tab view
        for (int i = 0; i < tabMain.getTabCount(); i++) {

            View customTabView = LayoutInflater.from(this).inflate(R.layout.navigation_item_layout, null);

            TextView tv = customTabView.findViewById(R.id.txt_menu);
            tv.setTextColor(Utility.getColorFromRes(this, R.color.grey));
            tv.setText(Utility.capitalize(pagerList.get(i).getTitle()));

            ImageView imageMenu = customTabView.findViewById(R.id.image_menu);
            imageMenu.setImageResource(pagerList.get(i).getIcon());

            if (i <= 0) {

                tv.setTextColor(Utility.getColorFromRes(this, R.color.blue));
                imageMenu.setImageResource(pagerList.get(i).getSelectedIcon());

            } else {

                tv.setTextColor(Utility.getColorFromRes(this, R.color.grey));

            }

            tabMain.getTabAt(i).setCustomView(customTabView);

        }

        // attach listener
        viewPagerLockable.registerOnPageChangeCallback(onPageChangeCallback);


    }

    public ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

            for (int i = 0; i < tabMain.getTabCount(); i++) {

                Utility.Logger(getClass().getSimpleName(), "Position Tab = " + i);

                View customItemView = tabMain.getTabAt(i).getCustomView();
                TextView txtTab = customItemView.findViewById(R.id.txt_menu);
                ImageView imageMenu = customItemView.findViewById(R.id.image_menu);

                if (i == position) {

                    txtTab.setTextColor(Utility.getColorFromRes(getApplicationContext(), R.color.blue));
                    imageMenu.setImageResource(pagerList.get(i).getSelectedIcon());

                } else {

                    txtTab.setTextColor(Utility.getColorFromRes(getApplicationContext(), R.color.grey));
                    imageMenu.setImageResource(pagerList.get(i).getIcon());


                }

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        // I just override the onBackPressed method
        // comment the super function
        // to prevent the user from going back after login
    }


}
