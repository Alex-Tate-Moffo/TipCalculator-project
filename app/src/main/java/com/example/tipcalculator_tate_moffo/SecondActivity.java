package com.example.tipcalculator_tate_moffo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // Declare private variables
    private Intent i;

    private Float bill;
    private Float numPeople;
    private Float tipPercent;

    private TextView billAmount;
    private TextView tipAmount;
    private TextView totalBill;
    private TextView tipPerPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        i = getIntent();

        bill = Float.parseFloat(i.getExtras().getString("bill"));
        numPeople = Float.parseFloat(i.getExtras().getString("numPeople"));
        tipPercent = i.getExtras().getFloat("tipPercent");

        float tip = (bill * tipPercent) / 100;
        float total = bill + tip;
        float perPerson = tip / numPeople;

        billAmount = findViewById(R.id.value_billAmount);
        tipAmount = findViewById(R.id.value_tipAmount);
        totalBill = findViewById(R.id.value_total);
        tipPerPerson = findViewById(R.id.value_tipPerPerson);

        billAmount.setText(String.valueOf(bill));
        tipAmount.setText(String.valueOf(tip));
        totalBill.setText(String.valueOf(total));
        tipPerPerson.setText(String.valueOf(perPerson));
    }
}