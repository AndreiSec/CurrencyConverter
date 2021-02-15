package com.example.currencyconverter;

import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyClass {
    private static double USDtoCad = 1.27;
    private static double USDtoEur = 0.83;
    private static double USDtoJPY = 104.60;
    private static double USDtoGbp = 0.72;
    private static double USDtoChf = 0.89;


    public static String currencyFrom;
    public static String currencyTo;

    // Converts currencies and outputs the result as a string
<<<<<<< Updated upstream
    public static String currencyConvertFunction(EditText inputNumber){
    String result;
    Double numberResult;
    Double amountFrom = Double.parseDouble(inputNumber.getText().toString());

    Double USDAmount;

    switch(currencyFrom){

        case "USD":
            USDAmount = amountFrom;
            break;
        case "CAD":
            USDAmount = amountFrom/USDtoCad;
            break;
        case "EUR":
            USDAmount = amountFrom/USDtoEur;
            break;
        case "JPY":
            USDAmount = amountFrom/USDtoJPY;
            break;
        case "GBP":
            USDAmount = amountFrom/USDtoGbp;
            break;
        case "CHF":
            USDAmount = amountFrom/USDtoChf;
            break;
        default:
            USDAmount = 0.00;
    }
=======
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
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream

=======
        // FINALLY, SAVE TO FILE. This is done so the user can see their previous conversions even after closing the app
        phonestorageClass store = new phonestorageClass(context);
        store.saveToDB(context, round(amountFrom, 2), numberResult, currencyFrom, currencyTo);
>>>>>>> Stashed changes


        return result;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
