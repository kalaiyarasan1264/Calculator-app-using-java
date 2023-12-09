package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import  org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_view, cal_view;
    MaterialButton buttonc, button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,buttonac,buttonopenbrac,buttonclosebrac,buttonadd,buttonsub,buttonmul,buttondiv,buttonequal,buttondot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_view=findViewById(R.id.result);
        cal_view=findViewById(R.id.cal);
        assignId(buttonc,R.id.button_c);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button0,R.id.button_0);
        assignId(buttonac,R.id.button_ac);
        assignId(buttonopenbrac,R.id.button_open_bracket);
        assignId(buttonclosebrac,R.id.button_close_bracket);
        assignId(buttondot,R.id.button_dot);
        assignId(buttonadd,R.id.button_add);
        assignId(buttonsub,R.id.button_sub);
        assignId(buttonmul,R.id.button_multiply);
        assignId(buttondiv,R.id.button_divide);
        assignId(buttonequal,R.id.button_equal);
    }

    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText= button.getText().toString();
        String datatocalculate = cal_view.getText().toString();
        if(buttonText.equals("AC")){
            cal_view.setText("");
            result_view.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            cal_view.setText(result_view.getText());
            return;
        }
        if(buttonText.equals("C")){
            datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);
        }
        else {
            datatocalculate = datatocalculate+buttonText;
        }

        cal_view.setText(datatocalculate);
        if(!datatocalculate.equals("")){
            String finalresult = getResult(datatocalculate);
            if(!finalresult.equals("Err")){
                result_view.setText(finalresult);
            }
        }

    }

    String getResult(String data){
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String final_result = context.evaluateString(scriptable,data,"Javascript",1, null).toString();
            if (final_result.endsWith(".0")){
                final_result=final_result.replace(".0","");
            }
            return final_result;
        }catch (Exception e){
            return "Err";
        }
    }
}