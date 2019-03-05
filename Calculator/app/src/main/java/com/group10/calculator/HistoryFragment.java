package com.group10.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.group10.calculator.dummy.History_Adapter;
import com.group10.calculator.dummy.ItemHistory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is class history perform time result operand
 */
public class HistoryFragment extends Fragment {

    private List<ItemHistory> mListItemHistory;
    private ListView mListView;
    private History_Adapter history_adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Set<String> stringSet;
    private ArrayList<String> array;

    /**
     * This is function handle convert hashset to arraylist
     * then add itemHistory in arraylist
     * value be formated: result | perand | day/month/year hour:minutes:second
     */
    private void AddListHistory()
    {
        sharedPreferences = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        stringSet = new HashSet<>();
        stringSet.add("0|0|04/03/2019 11:51:32");
        stringSet = sharedPreferences.getStringSet("data",stringSet);
        array = new ArrayList(stringSet);
        for(int i=0;i<array.size();i++)
        {

            String data = array.get(i);
            String[] strings = data.split("\\|");
            String result = strings[0];
            String operand = strings[1];
            String time = strings[2];
            mListItemHistory.add(new ItemHistory(result,operand,time));
        }
    }
    /**
     * This is function handle remove value when long click into listview
     */
    private void RemoveItem()
    {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                editor = sharedPreferences.edit();
                stringSet.remove(array.get(position));
                array.remove(position);
                editor.putStringSet("data",stringSet);
                editor.commit();
                mListItemHistory.remove(position);
                history_adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Remove success",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    /**
     * This is function handle create list itemhistory and put it on listview
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        mListItemHistory = new ArrayList<>();
        AddListHistory();
        mListView = view.findViewById(R.id.view_history);
        history_adapter =
                new History_Adapter(getContext(),R.layout.history_view,mListItemHistory);
        mListView.setAdapter(history_adapter);

        //Event remove item
        RemoveItem();
        // Inflate the layout for this fragment
        return view;
    }
}
