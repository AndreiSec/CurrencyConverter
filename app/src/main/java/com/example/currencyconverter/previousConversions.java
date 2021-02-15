package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class previousConversions extends AppCompatActivity {
    ListView listViewPreviousConversions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_previous_conversions);
        listViewPreviousConversions  = (ListView) findViewById(R.id.lvPrevConversions);
        new phonestorageClass(getApplicationContext()).fetchFromDBIntoList(getApplicationContext(), listViewPreviousConversions);

    }


    // What happens when the "<-" back button is pressed on any view but the main.
    // Return to main view
    public void backButtonClickFunction (View view){
        switchToMainView();

    }

    private void switchToMainView(){
        Intent intent = new Intent(previousConversions.this, MainActivity.class);
        startActivity(intent);

    }



}
