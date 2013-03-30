package com.bekk.Faggruppe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import roboguice.event.Observes;
import roboguice.inject.InjectView;

public class RoboGuiceActivity extends RoboActivity {
    @InjectView(R.id.firstInputField) TextView firstInputView;
    @InjectView(R.id.secondInputField) TextView secondInputView;
    @InjectView(R.id.minusButton) Button minusButton;
    @InjectView(R.id.plussButton) Button plussButton;
    @Inject private EventManager eventManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        plussButton.setOnClickListener(new KlikkeLytter());
        minusButton.setOnClickListener(new KlikkeLytter());
    }

    class KlikkeLytter implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            eventManager.fire(new MyEvent(view));
        }
    }

    private void handleEvent(@Observes MyEvent event) {
        Intent intent = getResultIntent();
        intent.putExtra("firstNumber", firstInputView.getText().toString());
        intent.putExtra("secondNumber", secondInputView.getText().toString());
        if (event.getView() == plussButton) {
            intent.putExtra("operator","add");
        }
        else if (event.getView() == minusButton) {
            intent.putExtra("operator","sub");
        }
        startActivity(intent);
    }

    private Intent getResultIntent() {
        return new Intent(this, ResultActivity.class);
    }
}
