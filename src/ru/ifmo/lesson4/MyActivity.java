package ru.ifmo.lesson4;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    final static int ENTERING = 0;
    final static int CALCULATED = 1;
    final static int ERROR = -1;

    int statue = 0;
    TextView expText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonMul = (Button) findViewById(R.id.buttonMul);
        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        Button buttonEqu = (Button) findViewById(R.id.buttonEqu);
        Button buttonDel = (Button) findViewById(R.id.buttonDel);
        Button buttonErase = (Button) findViewById(R.id.buttonErase);
        Button buttonBr = (Button) findViewById(R.id.buttonBr);

        expText = (TextView) findViewById(R.id.expText);

        buttonDel.setText("<-");

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("9");
            }
        });


        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd(".");
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd(" + ");
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd(" - ");
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd(" x ");
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd(" / ");
            }
        });
        buttonEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    enterText(Calculator.evaluate(expText.getText().toString())+"");
                    setStatue(CALCULATED);
                    int a = 5;
                } catch (Exception ex){
                    setStatue(ERROR);
                }

            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSymbolAtEnd();
            }
        });
        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllSymbols();
            }
        });
        buttonBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterBracketAtEnd();
            }
        });



    }

    void enterSymbolAtEnd(String s){
        if (statue == CALCULATED && s.length() == 1 && s.charAt(0) <= '9' && s.charAt(0) >= '0'){
            expText.setText("");
        }
        expText.setText(expText.getText().toString() + s);
        setStatue(ENTERING);
    }
    void deleteSymbolAtEnd(){
        String text = expText.getText().toString();
        if (text.isEmpty()) return;
        String s = expText.getText().toString().substring(0, text.length() - 1);
        if (!s.isEmpty()){
            char c = s.charAt(s.length() - 1);
            if ((c == '+' || c == '-' || c == '*' || c == '/') && s.length() > 1) s = s.substring(0, s.length() - 2);
        }
        expText.setText(s);
        setStatue(ENTERING);
    }
    void deleteAllSymbols(){
        expText.setText("");
        setStatue(ENTERING);
    }
    void enterBracketAtEnd(){
        String s = expText.getText().toString();

        char last = s.charAt(s.length() - 1);
        if (last == '+' || last == '-' || last == '*' || last == '/' || last == '(' || last == ' '){
            s += '(';
        } else {
            s += ')';
        }
        expText.setText(s);
        setStatue(ENTERING);
    }
    void enterText(String s){
        expText.setText(s);
    }

    void setStatue(int a){
        if (a == 0){
            expText.setTextColor(0xFF808080);
        } else if (a == 1){
            expText.setTextColor(0xFF0000FF);
        } else if (a == -1){
            expText.setTextColor(0xFFFF0000);
        }
        statue = a;
    }
}