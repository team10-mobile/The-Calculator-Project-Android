package com.group10.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtHistory;
    TextView txtExpression;

    Button[] btnArray;
    List<String> polyomial = new ArrayList<String>();
    ConvertToSuffix appConvert;

    String numberCurrent = "";
    String expression = "";
    String expression2 = "";

    boolean operatorClicked;
=======

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
>>>>>>> refs/remotes/origin/master
=======
>>>>>>> accc9822b680ce40760f19a5898935cb7539fd99
>>>>>>> branch-dev

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        MappingView();
    }

    /**
     * This is the class to map and capture events for views
     */
    private void MappingView() {
        txtHistory = (TextView) findViewById(R.id.textviewHistory);
        txtExpression = (TextView) findViewById(R.id.textviewExpression);
        btnArray = new Button[20];
        appConvert = new ConvertToSuffix();
        ViewID.SetResourcesID();
        for (int i = 0; i < 20; i++) {
            btnArray[i] = (Button) findViewById(ViewID.IDList[i]);
            btnArray[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) findViewById(v.getId());
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
        //Lấy chuỗi của biểu thức sau khi click toán tử + number
        txtExpression.setText(expression2 + numberCurrent);
    }

    /**
     * This is function handle event PlusMinusButton clicked
     */
    private void PlusMinusButtonClicked() {
        if (numberCurrent.contains("-")) {
            numberCurrent = numberCurrent.replace("-", "");
        } else {
            numberCurrent = "-" + numberCurrent;
        }
        //Lấy chuỗi của biểu thức sau khi click toán tử + number
        txtExpression.setText(expression2 + numberCurrent);
        //Cập nhật biểu thức sau khi cập lại number
        expression = txtExpression.getText().toString();
    }

    /**
     * @param data: text of button
     *              This is function handle event Operand Button clicked
     */
    private void OperandButtonClicked(String data) {
        numberCurrent += data;
        //Lấy chuỗi của biểu thức sau khi click toán tử + number
        txtExpression.setText(expression2 + numberCurrent);
        //Cập nhật biểu thức sau khi cập lại number
        expression = txtExpression.getText().toString();//Lan cuoi cung chua operand
        operatorClicked = false;

    }

    /**
     * @param data: text of button
     *              This is function handle event Operator Button clicked
     */
    private void OperatorButtonClicked(String data) {
        if (!operatorClicked) {
            polyomial.add(numberCurrent);
            polyomial.add(data);

            txtExpression.setText(expression + data);
            //Cập nhật lại biểu thức 2 sau khi cộng cho toán tử
            expression2 = txtExpression.getText().toString();
            numberCurrent = "";
            operatorClicked = true;
        }
    }

    /**
     * This is function handle event Total Button clicked
     */
    private void TotalButtonClicked() {
        polyomial.add(numberCurrent);
        int index = polyomial.size() - 1;
        if (appConvert.GetOperator(polyomial.get(index)) != 0) {
            polyomial.remove(index);
        }
        String s = "";
        for (int i = 0; i < polyomial.size(); i++) {
            s = s + " " + polyomial.get(i);
        }
        appConvert.ConvertIntermediateToSuffix(polyomial);
        appConvert.ResultOfExpression();
        txtHistory.setText(s);
        txtExpression.setText(appConvert.getResult());
        numberCurrent = appConvert.getResult();
        polyomial = new ArrayList<String>();
        expression2 = appConvert.getResult();
        expression = appConvert.getResult();
=======

<<<<<<< HEAD
>>>>>>> refs/remotes/origin/master
=======
>>>>>>> accc9822b680ce40760f19a5898935cb7539fd99
>>>>>>> branch-dev
    }
}
