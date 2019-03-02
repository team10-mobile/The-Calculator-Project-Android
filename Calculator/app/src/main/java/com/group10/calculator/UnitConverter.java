package com.group10.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * class use value input, type unit, specific units
 * for return 2 array : 1 array contain all name unit
 * in proportion 1 array contain all result convert
 */
public class UnitConverter extends AppCompatActivity {

    private float inputValue;
    private int indexChoice;
    private String[] nameUnit;
    private float[] resultValue;
    private float[] exchangeRate;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    /**
     * This function process user confirm data convert
     */
    public void Confirm(String input,String unit,int indexUnit)
    {
        try{
            inputValue  = Float.parseFloat(input);
            indexChoice = indexUnit;
        }
        catch (Exception e) {
            inputValue  = 0;
            indexChoice = 0;
        }
        switch(unit) {
            case "LENGTH" : LengthConverter(); break;
            case "VOLUME" : VolumeConverter(); break;
            case "WEIGHT" : WeightConverter(); break;
            case "AREA"   : AreaConverter();   break;
            case"CURRENCY":CurrencyConverter();break;
            default: Toast.makeText(UnitConverter.this,"Invalid unit",Toast.LENGTH_LONG).show();
                return;
        }

        inputValue *= exchangeRate[indexChoice];        //Change to value standard
        ProcessConverting();
    }
    /**
     * This is a function to convert length unit
     */
    private void LengthConverter() {
        nameUnit = new String[] {"mm","cm","dm","m","km","inch"};
        resultValue = new float[6];
        exchangeRate = new float[]{0.001f,0.01f,0.1f,1,1000,0.03f};
    }

    /**
     * This is a function to convert volume unit
     */
    private void VolumeConverter() {
        nameUnit = new String[] {"ml3","cm3","dm3","m3","inch3"};
        resultValue = new float[5];
        exchangeRate = new float[]{0.001f,1,1000,1000000,16.39f};
    }

    /**
     * This is a function to convert weight unit
     */
    private void WeightConverter() {
        nameUnit = new String[] {"mg","g","kg","ton"};
        resultValue = new float[4];
        exchangeRate = new float[]{0.001f,1,1000,1000000,1000000};
    }

    /**
     * This is a function to convert area unit
     */
    private void AreaConverter() {
        nameUnit = new String[] {"mm2","cm2","dm2","m2","km2"};
        resultValue = new float[5];
        exchangeRate = new float[]{0.0001f,0.01f,1,100,100000000};
    }

    /**
     * This is a function to convert currency unit
     */
    private void CurrencyConverter() {
        nameUnit = new String[] {"VND","EUR","GBP","JPY","USD","AUD","CAD","CHF","CNY","KRW","SGD"};
        resultValue = new float[11];
        exchangeRate = new float[]{1, 26374.31f, 30650.68f, 207.28f, 23199.5f, 16432.57f,
                17447.82f, 23220.63f, 3467.69f, 20.69f, 17118.88f};
    }

    /**
     * This is function support to convert unit
     * finally result to save on array resultValue
     */
    private void ProcessConverting()
    {
        for(int i = resultValue.length-1;i>=0;i--) {
            resultValue[i] = exchangeRate[i]/inputValue;
        }
        //TODO : Process to return value show on display
    }

}
