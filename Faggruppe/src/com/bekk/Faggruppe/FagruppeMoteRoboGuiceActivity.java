package com.bekk.Faggruppe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import roboguice.event.Observes;
import roboguice.inject.InjectView;

public class FagruppeMoteRoboGuiceActivity extends RoboActivity {
    @InjectView(R.id.firstInputField) EditText firstInputField;
    @InjectView(R.id.secondInputField) EditText secondInputField;
    @InjectView(R.id.plussButton) Button plussButton;
    @InjectView(R.id.minusButton) Button minusButton;
    @Inject EventManager myEventManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        plussButton.setOnClickListener(new KlikkeLytter());
        minusButton.setOnClickListener(new KlikkeLytter());
    }

    class KlikkeLytter implements View.OnClickListener {
        @Override
        public void onClick(View view) {
          myEventManager.fire(new MittKlikkEvent(view));
        }
    }

    private void handleClickEvent(@Observes MittKlikkEvent event) {
        Intent intent = getResultIntent();
        intent.putExtra("firstNumber", firstInputField.getText().toString());
        intent.putExtra("secondNumber", secondInputField.getText().toString());
        if (event.getView() == plussButton) {
            intent.putExtra("operator", "add");
        }
        else if (event.getView() == minusButton) {
            intent.putExtra("operator", "subtract");
        }
        startActivity(intent);
    }

    private Intent getResultIntent() {
        return new Intent(this, ResultActivity.class);
    }

    private class MittKlikkEvent {
        private View view;

        private MittKlikkEvent(View view) {
            this.view = view;
        }

        public View getView() {
            return view;
        }
    }
}