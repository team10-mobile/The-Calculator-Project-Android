package com.group10.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * This is class extends fragment to display on MainActivity
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {
    View activity;
    TextView txtHistory;
    TextView txtExpression;

    Button[] btnArray;
    List<String> polynomial = new ArrayList<String>();
    ConvertToSuffix appConvert;

    String numberCurrent = "";
    /**
     * To save the expression after clicking the operand
     */
    String expression = "";
    /**
     * To save the expression after clicking the operator
     */
    String expression2 = "";

    boolean operatorClicked;
    int flag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = inflater.inflate(R.layout.fragment_calculator, container, false);
        MappingView();
        return activity;
    }


    /**
     * This is the class to map and capture click event for all views
     */
    private void MappingView() {
        txtHistory = (TextView) activity.findViewById(R.id.textviewHistory);
        txtExpression = (TextView) activity.findViewById(R.id.textviewExpression);
        btnArray = new Button[20];
        appConvert = new ConvertToSuffix();
        ViewID.SetResourcesID();
        for (int i = 0; i < 20; i++) {
            btnArray[i] = (Button) activity.findViewById(ViewID.IDList[i]);
            btnArray[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        Button btn = (Button) activity.findViewById(v.getId());
        String data = btn.getText().toString();
        String tag = btn.getTag().toString();

        if ("num".equals(tag)) {
            OperandButtonClicked(data);
        } else if ("operator".equals(tag)) {
            OperatorButtonClicked(data);
        } else if ("dot".equals(tag)) {
            DotButtonClicked(data);
        } else if ("minus".equals(tag)) {
            PlusMinusButtonClicked();
        } else if ("total".equals(tag)) {
            TotalButtonClicked();
        } else if ("del".equals(tag)) {
            DeleteButtonClicked();
        } else if ("clear".equals(tag)) {
            ClearButtonClicked();
        } else if ("percent".equals(tag)) {
            PercentButtonClicked();
        }
    }

    /**
     * @param data: text of button
     *              This is function handle event Dot Button clicked
     */
    private void DotButtonClicked(String data) {
        if (!numberCurrent.contains(".")) {
            numberCurrent += ".";
        }
        txtExpression.setText(expression2 + numberCurrent);
    }

    /**
     * This is function handle event PlusMinus Button clicked
     */
    private void PlusMinusButtonClicked() {
        if (numberCurrent.contains("-")) {
            numberCurrent = numberCurrent.replace("-", "");
        } else {
            numberCurrent = "-" + numberCurrent;
        }
        txtExpression.setText(expression2 + numberCurrent);
        expression = txtExpression.getText().toString();
    }

    /**
     * @param data: text of button
     *              This is function handle event Operand Button clicked
     */
    private void OperandButtonClicked(String data) {
        numberCurrent += data;
        txtExpression.setText(expression2 + numberCurrent);
        expression = txtExpression.getText().toString();
        operatorClicked = false;
    }

    /**
     * @param data: text of button
     *              This is function handle event Operator Button clicked
     */
    private void OperatorButtonClicked(String data) {
        if (!operatorClicked) {
            if (!numberCurrent.equals("")) {
                polynomial.add(numberCurrent);
                polynomial.add(data);
            }

            txtExpression.setText(expression + data);
            expression2 = txtExpression.getText().toString();
            numberCurrent = "";
            operatorClicked = true;
        }
    }

    /**
     * This is function handle event Total Button clicked
     */
    private void TotalButtonClicked() {

        if (!operatorClicked)
            polynomial.add(numberCurrent);
        int index = polynomial.size() - 1;
        if (appConvert.GetOperator(polynomial.get(index)) != 0) {
            polynomial.remove(index);
        }
        String s = "";
        for (int i = 0; i < polynomial.size(); i++) {
            s = s + " " + polynomial.get(i);
        }
        appConvert.ConvertIntermediateToSuffix(polynomial);
        appConvert.ResultOfExpression();

        txtHistory.setText(s);
        txtExpression.setText(appConvert.getResult());
        numberCurrent = appConvert.getResult();
        polynomial = new ArrayList<String>();
        expression2 = "";
        expression = appConvert.getResult();
        operatorClicked = false;
    }

    /**
     * This is function handle event Clear Button clicked
     */
    private void ClearButtonClicked() {
        expression = "";
        expression2 = "";
        numberCurrent = "";
        appConvert = new ConvertToSuffix();
        polynomial = new ArrayList<String>();
        txtExpression.setText("0");
        txtHistory.setText("0");
    }

    /**
     * This is function handle event Delete Button clicked
     */
    private void DeleteButtonClicked() {
        txtExpression.setText("0");
        numberCurrent = "";
        expression2 = "";
    }

    /**
     * This is function handle event Percent Button clicked
     */
    private void PercentButtonClicked() {
        if (!operatorClicked) {
            String value = String.valueOf((Double.parseDouble(numberCurrent) / 100));
            txtExpression.setText(expression2 + value);
            txtHistory.setText(expression2 + value);
            numberCurrent = value;
            expression = txtExpression.getText().toString();
        }
    }
}
