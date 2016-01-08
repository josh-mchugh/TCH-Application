package com.redrumming.thecreaturehub.view.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.tablayout.pager.NavigationPagerAdapter;
import com.redrumming.thecreaturehub.view.tablayout.slidding.SlidingTabLayout;

/**
 * Created by ME on 12/16/2015.
 */
public class TabbedContent extends Fragment {

    public static final String TAG = "TabbedContentFragment";

    private NavigationPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tabbed_content, viewGroup, false);

        //Init tabbed content
        pagerAdapter = new NavigationPagerAdapter(getChildFragmentManager());

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        SlidingTabLayout tabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {

                return getResources().getColor(R.color.ColorPrimary);
            }
        });
        tabLayout.setViewPager(viewPager);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("selectedPage", viewPager.getCurrentItem());
    }

    public void updateTabbedContent(){

        pagerAdapter.updateFragments();
    }
}
