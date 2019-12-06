package com.example.maytinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText edtNumber;
private TextView tvResult;
private Button bt1;
private Button bt2;
private Button bt3;
private Button bt4;
private Button bt5;
private Button bt6;
private Button bt7;
private Button bt8;
private Button bt9;
private Button bt0;
private Button btcong;
private Button bttru;
private Button btnhan;
private Button btchia;
private Button btbang;
private Button btclear;
private Button btac;
private Button btcham;
private final String  TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickview();
    };
    public void initWidget(){
        edtNumber = (EditText) findViewById(R.id.edt_input);
        tvResult = (TextView)findViewById(R.id.tv_result);

        bt0 =(Button)findViewById(R.id.bt0);
        bt1 =(Button)findViewById(R.id.bt1);
        bt2 =(Button)findViewById(R.id.bt2);
        bt3 =(Button)findViewById(R.id.bt3);
        bt4 =(Button)findViewById(R.id.bt4);
        bt5 =(Button)findViewById(R.id.bt5);
        bt6 =(Button)findViewById(R.id.bt6);
        bt7 =(Button)findViewById(R.id.bt7);
        bt8 =(Button)findViewById(R.id.bt8);
        bt9 =(Button)findViewById(R.id.bt9);

        btcong =(Button)findViewById(R.id.btadd);
        bttru =(Button)findViewById(R.id.btsub);
        btnhan =(Button)findViewById(R.id.btmul);
        btchia =(Button)findViewById(R.id.btdiv);
        btbang =(Button)findViewById(R.id.btbang);
        btclear =(Button)findViewById(R.id.btclear);
        btac =(Button)findViewById(R.id.btac);
        btcham =(Button)findViewById(R.id.btdec);
    }
    public void setEventClickview(){
        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        btcong.setOnClickListener(this);
        bttru.setOnClickListener(this);
        btnhan.setOnClickListener(this);
        btchia.setOnClickListener(this);
        btcham.setOnClickListener(this);
        btclear.setOnClickListener(this);
        btac.setOnClickListener(this);
        btbang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt0:
            edtNumber.append("0");
            break;
            case R.id.bt1:
                edtNumber.append("1");
                break;
            case R.id.bt2:
                edtNumber.append("2");
                break;
            case R.id.bt3:
                edtNumber.append("3");
                break;
            case R.id.bt4:
                edtNumber.append("4");
                break;
            case R.id.bt5:
                edtNumber.append("5");
                break;
            case R.id.bt6:
                edtNumber.append("6");
                break;
            case R.id.bt7:
                edtNumber.append("7");
                break;
            case R.id.bt8:
                edtNumber.append("8");
                break;
            case R.id.bt9:
                edtNumber.append("9");
                break;
            case R.id.btadd:
                edtNumber.append("+");
                break;
            case R.id.btsub:
                edtNumber.append("-");
                break;
            case R.id.btmul:
                edtNumber.append("*");
                break;
            case R.id.btdiv:
                edtNumber.append("/");
                break;
            case R.id.btclear:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(edtNumber, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                // String numberafter = (edtNumber.getText().toString());
               // edtNumber.setText(numberafter);
                break;
            case R.id.btac:
                edtNumber.setText("");
                tvResult.setText("");
                break;
            case R.id.btdec:
                edtNumber.append(".");
                break;
            case R.id.btbang:
                DecimalFormat df = new DecimalFormat("###.####");
                double result=0;
                addOperation(edtNumber.getText().toString());
                addNumber(edtNumber.getText().toString());
                if (arrOperation.size()>= arrNumber.size() || arrOperation.size()<1){
                    tvResult.setText("Error");
                } else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    result = result + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    result = result * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    result = result / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    tvResult.setText(df.format(result) + "");

                }
                //Log.d(TAG, "onClick: "+ result);
        }
    }
//    public String delete1so (String number){
//        int length = number.length();
//        String ten = number.substring(0,length-1);
//        return ten;
// }
    public ArrayList<String> arrOperation;

    public ArrayList<Double> arrNumber;

    public  int addOperation (String input){
        arrOperation = new ArrayList<>();
        char[] cArray = input.toCharArray();
        for (int i=0; i<cArray.length; i++){
            switch (cArray[i]){
                case '+':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '-':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '*':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '/':
                    arrOperation.add(cArray[i]+"");
                    break;
                    default:
                        break;
            }
        }
        return 0;
    }
    public void addNumber (String stringInput){
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
