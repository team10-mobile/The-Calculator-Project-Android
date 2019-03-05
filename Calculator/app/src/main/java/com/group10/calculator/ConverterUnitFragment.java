package com.group10.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * class control fragment when pageviewer changed
 */
class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}


public class ConverterUnitFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_converter_unit,container, false);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = view.findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);
        return view;
    }

    /**
     * Set fragment on each tablayout
     * @param viewPager : container in fragment, use to change other tab
     */

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());

        UnitConverter converter = new UnitConverter();

        UnitFragment frLength = new UnitFragment();
        converter.Confirm("0","LENGTH",0);
        frLength.unitOfFragment = "LENGTH";
        SendDataToChild(converter.GetArrName(),frLength);
        adapter.addFragment(frLength, "LENGTH");

        UnitFragment frArea = new UnitFragment();
        converter.Confirm("0","AREA",0);
        frArea.unitOfFragment = "AREA";
        SendDataToChild(converter.GetArrName(),frArea);
        adapter.addFragment(frArea, "AREA");

        UnitFragment frWeight = new UnitFragment();
        converter.Confirm("0","WEIGHT",0);
        frWeight.unitOfFragment = "WEIGHT";
        SendDataToChild(converter.GetArrName(),frWeight);
        adapter.addFragment(frWeight, "WEIGHT");

        UnitFragment frVolume = new UnitFragment();
        converter.Confirm("0","VOLUME",0);
        frVolume.unitOfFragment = "VOLUME";
        SendDataToChild(converter.GetArrName(),frVolume);
        adapter.addFragment(frVolume, "VOLUME");

        viewPager.setAdapter(adapter);
    }

    /**
     * Send data to children
     * @param sign : array sign of unit
     * @param fr : fragment children
     */
    private void SendDataToChild(String[] sign,UnitFragment fr)
    {
        for(int i=0;i<sign.length;i++)
            fr.listNameUnit.add(sign[i]);
    }
}
