package com.example.mirsiddika.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewBillAmount;
    private TextView textViewLabelPercent;
    private TextView TextViewTip;
    private TextView TextViewTotal;

    private double amount = 0.0;
    private double percent = 0.15;
    private double tip = 0;
    private double total = 0;


    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewBillAmount = (TextView) findViewById(R.id.textViewBillAmount);
        textViewLabelPercent = (TextView) findViewById(R.id.textViewLabelPercent);
        TextViewTip = (TextView) findViewById(R.id.TextViewTip);
        TextViewTotal = (TextView) findViewById(R.id.TextViewTotal);
        TextViewTip.setText(currencyFormat.format(0));
        TextViewTotal.setText(currencyFormat.format(0));

        final EditText editTextBillAmount = (EditText) findViewById(R.id.editTextBillAmount);
        editTextBillAmount.addTextChangedListener(amountEditTextWatcher);

        SeekBar seekBarPercent = (SeekBar) findViewById(R.id.seekBarPercent);
        seekBarPercent.setOnSeekBarChangeListener(seekbarListener);
    }

    private void calc() {
        double tip = amount * percent;
        double total = amount + tip;

        TextViewTip.setText(currencyFormat.format(tip));
        TextViewTotal.setText(currencyFormat.format(total));


    }

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                amount = Double.parseDouble(s.toString()) / 100;
                textViewBillAmount.setText(currencyFormat.format(amount));
            } catch (NumberFormatException e)

            {
                textViewBillAmount.setText("");
                amount = 0.0;

            }
            calc();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    };
    private final SeekBar.OnSeekBarChangeListener seekbarListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            textViewLabelPercent.setText(percentFormat.format(percent));
            calc();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {


        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };
}

