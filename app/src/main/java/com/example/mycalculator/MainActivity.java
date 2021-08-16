package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private String pendingOperation = "=";

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String OPERATION_STATE = "Operand1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.inputNumber);
        displayOperation = findViewById(R.id.operator);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonEquals = findViewById(R.id.buttonEqual);
        Button buttonDivide = findViewById(R.id.buttonDiv);
        Button buttonMultiply = findViewById(R.id.ConstraingLayout);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonplus);

        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b =(Button) v;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listner);
        button1.setOnClickListener(listner);
        button2.setOnClickListener(listner);
        button3.setOnClickListener(listner);
        button4.setOnClickListener(listner);
        button5.setOnClickListener(listner);
        button6.setOnClickListener(listner);
        button7.setOnClickListener(listner);
        button8.setOnClickListener(listner);
        button9.setOnClickListener(listner);
        buttonDot.setOnClickListener(listner);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try{
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue,op);
                }catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation=op;
                displayOperation.setText(pendingOperation);

            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);

        Button buttonNeg = findViewById(R.id.buttonNeg);
        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if(value.length() == 0 ){
                    newNumber.setText("-");
                }else {
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1 ;
                        newNumber.setText(doubleValue.toString());
                    }catch (NumberFormatException e){
                        newNumber.setText("");
                    }
                }
            }
        });

        Button buttonAc = findViewById(R.id.buttonAc);
        buttonAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNumber.setText("");
                operand1 = null;
                result.setText("0.0");
            }
        });
    }

    // save var before it destroy our view
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION,OPERATION_STATE);
        if(operand1 !=null){
            outState.putDouble(OPERATION_STATE,operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(OPERATION_STATE);
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double value, String operation){
        if (null == operand1){
            operand1= value;
        }else{
            if (pendingOperation.equals("=")){
                pendingOperation = operation;
            }
            switch (pendingOperation){
                case"=":
                    operand1=value;
                    break;
                case "/":
                    if (value == 0){
                        operand1=0.0;
                    }else{
                        operand1/=value;
                    }
                    break;
                case "+":
                    operand1+=value;
                    break;
                case "-":
                    operand1-=value;
                    break;
                case "*" :
                    operand1 *= value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
        //displayOperation.setText(operation);
    }
}