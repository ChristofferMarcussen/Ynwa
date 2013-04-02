package com.bekk.Faggruppe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.bekk.Faggruppe.http.Http;

import java.io.IOException;

public class TraditionalResultActivity extends Activity {
    private TextView resultTextView;
    private Http http;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        http = new Http();

        Bundle extras = getIntent().getExtras();
        String first = extras.getString("firstNumber");
        String second = extras.getString("secondNumber");
        String operator = extras.getString("operator");

        String result = "";

        if (!first.equals("") && !second.equals("")) {
            if (operator.equals("add")) {
                Http.Response response = http.add(first, second);
                try {
                    result = Http.fromStream(response.getResponseBody())+ "";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Http.Response response = http.subtract(first, second);
                try {
                    result = Http.fromStream(response.getResponseBody())+ "";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resultTextView.setText(result);
        }
    }
}