package com.bekk.Faggruppe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.bekk.Faggruppe.domain.Result;

public class TraditionalResultActivity extends Activity {
    TextView resultTextView;
    Result result;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        result = new Result();
        Bundle extras = getIntent().getExtras();
        String first = extras.getString("firstNumber");
        String second = extras.getString("secondNumber");
        String operator = extras.getString("operator");

        if (!first.equals("") && !second.equals("")) {
            if (operator.equals("add")) {
                int firstNumber = Integer.parseInt(first);
                int secondNumber = Integer.parseInt(second);
                result.setNumber((firstNumber + secondNumber)+ "");
            }
            else {
                int firstNumber = Integer.parseInt(first);
                int secondNumber = Integer.parseInt(second);
                result.setNumber((firstNumber - secondNumber) + "");
                resultTextView.setText(result.getNumber() + "");
            }
            resultTextView.setText(result.getNumber() + "");
        }
    }
}