package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class previousConversions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_previous_conversions);
    }


    // What happens when the "<-" back button is pressed on any view but the main.
    // Return to main view
    public void backButtonClickFunction (View view){
        switchToMainView();

    }

    private void switchToMainView(){
        Intent intent = new Intent(previousConversions.this, MainActivity.class);
        startActivity(intent);
//        finish();
//        Intent intent = new Intent(this., MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }
}
