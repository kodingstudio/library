package com.haris.ecommerce.presentationutil.ui.adapterutil;

import android.view.ViewGroup;

import com.haris.ecommerce.presentationutil.ui.objectutil.PagerTabObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TextualPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<PagerTabObject> fragmentArrayList = new ArrayList<>();

    public TextualPagerAdapter(FragmentManager fm, List<PagerTabObject> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList.addAll(fragmentArrayList);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentArrayList.get(position).getTitle();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

}

