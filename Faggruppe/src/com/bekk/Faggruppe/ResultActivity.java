package com.bekk.Faggruppe;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.bekk.Faggruppe.domain.Result;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ResultActivity extends RoboActivity {
    @InjectView(R.id.resultTextView) TextView resultTextView;
    @Inject Result result;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result_layout);
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