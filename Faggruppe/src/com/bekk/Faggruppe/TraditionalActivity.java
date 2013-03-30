package com.bekk.Faggruppe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TraditionalActivity extends Activity {
    private TextView firstInputView;
    private TextView secondInputView;
    private Button plussButton;
    private Button minusButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        firstInputView = (TextView) findViewById(R.id.firstInputField);
        secondInputView = (TextView) findViewById(R.id.secondInputField);
        plussButton = (Button) findViewById(R.id.plussButton);
        minusButton = (Button) findViewById(R.id.minusButton);

        plussButton.setOnClickListener(new KlikkeLytter());
        minusButton.setOnClickListener(new KlikkeLytter());
    }


    class KlikkeLytter implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = getResultIntent();
            intent.putExtra("firstNumber", firstInputView.getText().toString());
            intent.putExtra("secondNumber", secondInputView.getText().toString());
            if (view == plussButton) {
                intent.putExtra("operator", "add");
            }
            else if (view == minusButton) {
                intent.putExtra("operator", "subtract");
            }
            startActivity(intent);
        }
    }

    private Intent getResultIntent() {
        return new Intent(this, ResultActivity.class);
    }
}

