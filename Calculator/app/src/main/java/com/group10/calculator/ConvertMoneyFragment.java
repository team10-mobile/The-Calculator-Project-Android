package com.group10.calculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ConvertMoneyFragment extends Fragment {
    private UnitConverter mCurrencyAdapter;
    private int[] mEditTextMoneyListID;
    private EditText[] mEditTextMoneyList;
    private View FragmentConvertMoney;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentConvertMoney =
                inflater.inflate(R.layout.fragment_convertmoney, container, false);
        MapArrayEditText();
        AssignEventToEditText();
        return FragmentConvertMoney;
    }
    /**
     * Assign Event To EditText and first Text
     */
    private void AssignEventToEditText(){
        for (int i = 0; i < 11; i++){
            mEditTextMoneyList[i].setText("0");
            mEditTextMoneyList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText ThisEditText = (EditText)view;
                    ThisEditText.setText("0");
                }
            });
            mEditTextMoneyList[i].setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    EditText ThisEditText = (EditText)view;
                    int ThisTag = Integer.parseInt(ThisEditText.getTag().toString());
                    mCurrencyAdapter.Confirm(mEditTextMoneyList[ThisTag].getText().toString(),"CURRENCY", ThisTag);
                    for (int j = 0; j < 11 ; j++){
                        if (j == ThisTag)
                            continue;
                        mEditTextMoneyList[j].setText(mCurrencyAdapter.GetArrValue()[j] + "");
                    }
                    return false;
                }
            });
        }
    }
    /**
     * Map Array Edit Text from Int Array
     */
    private void MapArrayEditText(){
        AssignIdToIntArray();
        mCurrencyAdapter = new UnitConverter();
        mEditTextMoneyList = new EditText[11];
        for (int i = 0; i < 11; i++){
            mEditTextMoneyList[i] = FragmentConvertMoney.findViewById(mEditTextMoneyListID[i]);
        }
    }
    /**
     * Assign Id To Int Array
     */
    private void AssignIdToIntArray(){
        mEditTextMoneyListID  = new int[11];
        mEditTextMoneyListID[0] = R.id.edt_VND;
        mEditTextMoneyListID[1] = R.id.edt_EUR;
        mEditTextMoneyListID[2] = R.id.edt_GBP;
        mEditTextMoneyListID[3] = R.id.edt_JPY;
        mEditTextMoneyListID[4] = R.id.edt_USD;
        mEditTextMoneyListID[5] = R.id.edt_AUD;
        mEditTextMoneyListID[6] = R.id.edt_CAD;
        mEditTextMoneyListID[7] = R.id.edt_CHF;
        mEditTextMoneyListID[8] = R.id.edt_CNY;
        mEditTextMoneyListID[9] = R.id.edt_KRW;
        mEditTextMoneyListID[10] = R.id.edt_SGD;
    }
}