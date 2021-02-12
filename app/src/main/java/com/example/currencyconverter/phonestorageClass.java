package com.example.currencyconverter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class phonestorageClass extends AppCompatActivity{

    private static final String FILE_NAME = "prevConversions.txt";



    public void saveToFile(Context context, Double amountFrom, Double amountTo, String currencyFrom, String currencyTo){
        String text = String.format("%.2f %s = %.2f %s\n", amountFrom, currencyFrom, amountTo, currencyTo);
//        Context ctx = getApplicationContext();
//        Log.i("Context: ", ctx.toString());
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Log.i("Info:", "Writing: " + text + "to file " + FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("Error:", "Cannot save to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
