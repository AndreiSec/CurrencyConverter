package com.example.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // CREATE TO AND FROM CURRENCY PICKER SPINNERS
        Spinner spinnerFrom = findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = findViewById(R.id.spinnerTo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setAdapter(adapter);
        spinnerTo.setOnItemSelectedListener(this);
        //

<<<<<<< Updated upstream

=======
        // Init application context. This will be passed to a variety of functions.
        context = getApplicationContext();
        Log.i("Context:", context.toString());
>>>>>>> Stashed changes


    }

    // Switches the view to the previous conversions activity
    public void switchViewToPreviousConversions() {
        Intent intent = new Intent(MainActivity.this, previousConversions.class);
        startActivity(intent);

    }


    // Initializes the "menu" on the screen when the user clicks the cog wheel FAB
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.dropdownmenu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.previousConversionXML:
                        // Switch view when button pressed
                        switchViewToPreviousConversions();
//                        LayoutInflater layoutInflater = LayoutInflater.from(context);
//                        layoutInflater.inflate(R.layout.activity_previous_conversions, null);


                        return true;

                    default:
                        return true;
                }
            }
        });

        popup.show(); //showing popup menu
    }


<<<<<<< Updated upstream
        // What happens when "convert" button is pressed!!
        public void convertButtonClickFunction (View view){
            // CREATE OBJECT FOR NUMBER INPUT FIELD
            EditText inputNumber = findViewById(R.id.editTextFromInput);
            TextView output = findViewById(R.id.textViewToCurrency);
            Log.i("CurrencyFrom: ", CurrencyClass.currencyFrom);
            Log.i("CurrencyTo: ", CurrencyClass.currencyTo);
            Log.i("Input Value: ", inputNumber.getText().toString());

            // Since all variables are public, this function does not need any parameters.
            if (!inputNumber.getText().toString().equals("")) {
                String result = CurrencyClass.currencyConvertFunction(inputNumber);
                output.setText(result);
            } else {
                Toast.makeText(getApplicationContext(), "Please enter a number.", Toast.LENGTH_SHORT).show();
            }

=======
    // What happens when "convert" button is pressed!!
    public void convertButtonClickFunction(View view) {
        // CREATE OBJECT FOR NUMBER INPUT FIELD
        EditText inputNumber = findViewById(R.id.editTextFromInput);
        TextView output = findViewById(R.id.textViewToCurrency);
        Log.i("CurrencyFrom: ", CurrencyClass.currencyFrom);
        Log.i("CurrencyTo: ", CurrencyClass.currencyTo);
        Log.i("Input Value: ", inputNumber.getText().toString());

        // Since all variables are public, this function does not need any parameters.
        if (!inputNumber.getText().toString().equals("")) {
            String result = CurrencyClass.currencyConvertFunction(context, inputNumber);
            output.setText(result);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a number.", Toast.LENGTH_SHORT).show();
>>>>>>> Stashed changes
        }

    }


    // When an item is selected in the drop-down spinners containing to and from currencies
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerFrom:
                CurrencyClass.currencyFrom = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinnerTo:
                CurrencyClass.currencyTo = parent.getItemAtPosition(position).toString();
        }
    }


    // Empty method if nothing selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
