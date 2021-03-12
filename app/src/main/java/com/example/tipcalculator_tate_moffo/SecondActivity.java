package com.example.tipcalculator_tate_moffo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

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
    private Intent i;
    private Intent back;

    private Float bill;
    private Float numPeople;
    private Float tipPercent;

    private TextView billAmount;
    private TextView tipAmount;
    private TextView totalBill;
    private TextView tipPerPerson;

    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // creates the current intent and the intent used to go back to the MainActivity
        i = getIntent();
        back = new Intent(this, MainActivity.class);

        // pulls the passed values from the bundle
        bill = Float.parseFloat(i.getExtras().getString("bill"));
        numPeople = Float.parseFloat(i.getExtras().getString("numPeople"));
        tipPercent = i.getExtras().getFloat("tipPercent");

        // calculates the necessary amounts
        float tip = (bill * tipPercent) / 100;
        float total = bill + tip;
        float perPerson = tip / numPeople;

        // declares the TextViews
        billAmount = findViewById(R.id.value_billAmount);
        tipAmount = findViewById(R.id.value_tipAmount);
        totalBill = findViewById(R.id.value_total);
        tipPerPerson = findViewById(R.id.value_tipPerPerson);

        // sets the TextViews
        billAmount.setText(String.valueOf(bill));
        tipAmount.setText(String.valueOf(tip));
        totalBill.setText(String.valueOf(total));
        tipPerPerson.setText(String.valueOf(perPerson));

        // assigns the goBack button and declares an onClick listener
        goBackButton = findViewById(R.id.button_calculateNew);
        goBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // returns the to the MainActivity
                startActivity(back);
            }
        });

    }
}