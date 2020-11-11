package com.creasia.ui.main;
import com.creasia.ui.main.route.RouteFragment;
import com.creasia.ui.main.routeMonth.RouteMonthFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class RoutePlanPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public RoutePlanPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return RouteFragment.newInstance();
            case 1:
                return RouteMonthFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
