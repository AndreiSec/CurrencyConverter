package com.example.currencyconverter;

import android.content.Context;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyClass {
    private static final double USDtoCad = 1.27;
    private static final double USDtoEur = 0.83;
    private static final double USDtoJPY = 104.60;
    private static final double USDtoGbp = 0.72;
    private static final double USDtoChf = 0.89;


    public static String currencyFrom;
    public static String currencyTo;

    // Converts currencies and outputs the result as a string
    public static String currencyConvertFunction(Context context, EditText inputNumber) {
        String result;
        Double numberResult;
        Double amountFrom = Double.parseDouble(inputNumber.getText().toString());

        Double USDAmount;

        switch (currencyFrom) {

            case "USD":
                USDAmount = amountFrom;
                break;
            case "CAD":
                USDAmount = amountFrom / USDtoCad;
                break;
            case "EUR":
                USDAmount = amountFrom / USDtoEur;
                break;
            case "JPY":
                USDAmount = amountFrom / USDtoJPY;
                break;
            case "GBP":
                USDAmount = amountFrom / USDtoGbp;
                break;
            case "CHF":
                USDAmount = amountFrom / USDtoChf;
                break;
            default:
                USDAmount = 0.00;
        }

        switch (currencyTo) {

            case "USD":
                numberResult = USDAmount;
                break;
            case "CAD":
                numberResult = USDAmount * USDtoCad;
                break;
            case "EUR":
                numberResult = USDAmount * USDtoEur;
                break;
            case "JPY":
                numberResult = USDAmount * USDtoJPY;
                break;
            case "GBP":
                numberResult = USDAmount * USDtoGbp;
                break;
            case "CHF":
                numberResult = USDAmount * USDtoChf;
                break;
            default:
                numberResult = 0.00;
        }

        numberResult = round(numberResult, 2);
        result = String.valueOf(numberResult);

        // FINALLY, SAVE TO FILE. This is done so the user can see their previous conversions even after closing the app
        phonestorageClass store = new phonestorageClass(context);
        store.saveToDB(context, round(amountFrom, 2), numberResult, currencyFrom, currencyTo);


        return result;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
