package com.krish.flowerapp.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.SearchView;

import com.krish.flowerapp.fragments.CustomersFragment;
import com.krish.flowerapp.fragments.FlowersFragment;
import com.krish.flowerapp.fragments.VendorsFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int no_of_pages;
    SearchView.OnQueryTextListener context;
    public MyPagerAdapter(FragmentManager fm, int numofPages) {
        super(fm);
        this.no_of_pages=numofPages;
    }

    public MyPagerAdapter(FragmentManager fm, SearchView.OnQueryTextListener context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FlowersFragment();

            case 1:
                return new CustomersFragment();

            case 2:
                return new VendorsFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return no_of_pages;
    }
}
