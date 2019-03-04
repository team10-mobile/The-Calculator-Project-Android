package com.group10.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class UnitFragment extends Fragment {

    private View fragment;
    private int[] tvUnitIds;
    private int[] tvValueIds;
    private EditText etInput;
    private Spinner spUnit;
    private UnitConverter converter = new UnitConverter();
    private ArrayAdapter<String> adapter;
    private List<String> listNameUnit;
    @Nullable
    @Override
    //GG
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_unit, container, false);
        etInput = fragment.findViewById(R.id.etinput);
        spUnit = fragment.findViewById(R.id.spunit);
        tvUnitIds = new int[]{R.id.tvunit1,R.id.tvunit2,R.id.tvunit3,
                R.id.tvunit4,R.id.tvunit5,R.id.tvunit6};
        tvValueIds = new int[] {R.id.tvvalue1,R.id.tvvalue2,R.id.tvvalue3,
                R.id.tvvalue4,R.id.tvvalue5,R.id.tvvalue6};

        listNameUnit = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, listNameUnit);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spUnit.setAdapter(adapter);
        return fragment;
    }

    //
    public void ChoiceUnit(int sign) {
        switch (sign){
            case 0: converter.Confirm("0","LENGTH",0);break;
            case 1: converter.Confirm("0","AREA",0);break;
            case 2: converter.Confirm("0","WEIGHT",0);break;
            case 3: converter.Confirm("0","VOLUME",0);break;
            default:return;
        }
        AddItemToSpinner(converter.GetArrName());
    }

    public void AddItemToSpinner(String[] item)
    {
        listNameUnit.clear();
        for(int i=0;i<item.length;i++)
            listNameUnit.add(item[i]);
        adapter.notifyDataSetChanged();
    }
}
