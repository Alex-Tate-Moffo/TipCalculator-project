package com.example.tipcalculator_tate_moffo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    // Declare global variables
    private String TAG = "MainActivity";

    private Button calculateButton;
    private Button resetButton;

    private float bill;
    private int numPeople;
    // FIX? default value should instead be "you must select" message?
    private float tipPercent = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });*/

        /*calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                i.putExtra("bill", bill);
                i.putExtra("numPeople", numPeople);
                i.putExtra("tipPercent", tipPercent);

                startActivity(i);
            }
        });*/
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        // Assign the tip percent
        switch(view.getId()) {
            case R.id.radio_10:
                if (checked)
                    tipPercent = 10;
                    break;
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
            case R.id.radio_custom:
                if (checked)
                    // FIX
                    tipPercent = R.id.input_percent;
                    break;
        }

        Log.i(TAG, String.valueOf(tipPercent));
    }

    // ...

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "Saving instance state");

        // pulls the current values from the UI
        // ADD

        // saves the current values to the bundle
        outState.putFloat("bill", bill);
        outState.putInt("numPeople", numPeople);
        outState.putFloat("tipPercent", tipPercent);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "Restoring instance state");

        // pulls the saved values from the bundle
        bill = savedInstanceState.getFloat("bill");
        numPeople = savedInstanceState.getInt("numPeople");
        tipPercent = savedInstanceState.getFloat("tipPercent");

        // displays the values
        // EX. textCounter.setText(counterValue);


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