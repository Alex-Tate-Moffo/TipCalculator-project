package com.example.tipcalculator_tate_moffo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Alex Tate-Moffo
     * Homework #2
     * CSCI370 (Spring 2021)
     *
     * Tip Calculator App:
     *      A simple app that allows the user to input a bill,
     *      the number of people paying the bill, and the desired
     *      tip percentage, and calculates the tip, total with
     *      tip, and tip per person.
     */

    // Declare global variables
    private String TAG = "MainActivity";
    private Intent i;

    private Button calculateButton;
    private Button resetButton;

    private EditText inputBill;
    private EditText inputPeople;
    private EditText inputCustom;

    private RadioGroup radioGroup;
    private RadioButton fifteenSelected;
    private RadioButton twentySelected;
    private RadioButton twentyFiveSelected;
    private RadioButton customSelected;

    private String bill;
    private String numPeople;
    private float tipPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creates the intent used to go to the SecondActivity
        i = new Intent(this, SecondActivity.class);

        // assigns the reset button and declares an onClick listener
        resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                resetValues();
            }
        });

        // assigns the calculate button and declares an onClick listener
        calculateButton = findViewById(R.id.button_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // gets input fields
                inputBill = findViewById(R.id.input_bill);
                inputPeople = findViewById(R.id.input_people);
                inputCustom = findViewById(R.id.input_percent);

                // gets radio buttons
                fifteenSelected = findViewById(R.id.radio_15);
                twentySelected = findViewById(R.id.radio_20);
                twentyFiveSelected = findViewById(R.id.radio_25);
                customSelected = findViewById(R.id.radio_custom);

                // if the bill field has a value and the value is >= 1 AND
                // the num people field has a value and the value is >= 1
                if (   (!inputBill.getText().toString().equals("") &&
                        Float.parseFloat(inputBill.getText().toString()) >= 1.0)    &&
                       (!inputPeople.getText().toString().equals("") &&
                        Integer.parseInt((inputPeople.getText().toString())) >= 1 ) ) {
                    // if the custom tip percentage radio button is selected
                    // and the input field is empty or has a value < 1%
                    if ((customSelected.isChecked() &&
                        (inputCustom.getText().toString().equals("") ||
                         Float.parseFloat(inputCustom.getText().toString()) < 1.0 ))) {

                         Toast.makeText(getApplicationContext(),
                                "Must enter tip >= 1%", Toast.LENGTH_SHORT).show();
                    }
                    // if the custom tip percentage radio button is selected
                    // and the value is >= 1%
                    else if ((customSelected.isChecked() &&
                            Float.parseFloat(inputCustom.getText().toString()) >= 1.0 )) {

                        // sets the custom tip percent
                        tipPercent = Float.parseFloat(inputCustom.getText().toString());

                        // GO TO SECOND ACTIVITY
                        goToSecondActivity();
                        /*Toast.makeText(getApplicationContext(),
                                "CALCULATING (custom)", Toast.LENGTH_SHORT).show();*/

                    }
                    // if a radio button other than custom is selected
                    else if (fifteenSelected.isChecked() ||
                             twentySelected.isChecked()  ||
                             twentyFiveSelected.isChecked() ) {

                        // GO TO SECOND ACTIVITY
                        goToSecondActivity();
                        /*Toast.makeText(getApplicationContext(),
                                "CALCULATING (radio)", Toast.LENGTH_SHORT).show();*/

                    }
                    // if no radio buttons are selected
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Must select tip %", Toast.LENGTH_SHORT).show();
                    }
                }
                // if something does not have a proper value
                else {
                    Toast.makeText(getApplicationContext(),
                            "Must enter values", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // determines if the button checked
        boolean checked = ((RadioButton) view).isChecked();

        // Checks which radio button was clicked
        // Assigns the tip percent based on the radio button selection
        switch(view.getId()) {
            case R.id.radio_15:
                if (checked)
                    tipPercent = 15;
                    break;
            case R.id.radio_20:
                if (checked)
                    tipPercent = 20;
                    break;
            case R.id.radio_25:
                if (checked)
                    tipPercent = 25;
                    break;
        }

        /*Log.i(TAG, String.valueOf(tipPercent));*/
    }

    // can be called to reset the entered values
    public void resetValues() {

        /*Toast.makeText(getApplicationContext(),
                "RESET VALUES", Toast.LENGTH_SHORT).show();*/

        // gets input fields
        inputBill = findViewById(R.id.input_bill);
        inputPeople = findViewById(R.id.input_people);
        inputCustom = findViewById(R.id.input_percent);

        // sets the input fields to be empty
        inputBill.setText("");
        inputPeople.setText("");
        inputCustom.setText("");

        // clears the radio button selection
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

    }

    // ...

    public void goToSecondActivity() {
        // gets the string values from the input fields
        bill = inputBill.getText().toString();
        numPeople = inputPeople.getText().toString();

        // puts them into the bundle
        i.putExtra("bill", bill);
        i.putExtra("numPeople", numPeople);
        i.putExtra("tipPercent", tipPercent);

        // goes to the SecondActivity
        startActivity(i);
    }

    // ...

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /*Log.i(TAG, "Saving instance state");

        // pulls the current values from the UI
        // ADD

        // saves the current values to the bundle
        outState.putFloat("bill", bill);
        outState.putInt("numPeople", numPeople);
        outState.putFloat("tipPercent", tipPercent);*/
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       /* Log.i(TAG, "Restoring instance state");

        // pulls the saved values from the bundle
        bill = savedInstanceState.getFloat("bill");
        numPeople = savedInstanceState.getInt("numPeople");
        tipPercent = savedInstanceState.getFloat("tipPercent");

        // displays the values
        // EX. textCounter.setText(counterValue);*/


    }

    // ...

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Entering onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Entering onResume");

        // load values from bundle
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Entering onPause");

        // put values into bundle
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Entering onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Entering onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Entering onDestroy");
    }

}