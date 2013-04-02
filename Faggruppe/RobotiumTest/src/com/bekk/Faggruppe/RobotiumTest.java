package com.bekk.Faggruppe;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.jayway.android.robotium.solo.Solo;

public class RobotiumTest extends ActivityInstrumentationTestCase2<RoboGuiceActivity> {

    private Solo solo;
    private EditText firstInputView;
    private EditText secondInputView;
    private Button plussButton;
    private Button minusButton;

    public RobotiumTest() {
        super(RoboGuiceActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());

        firstInputView = (EditText) solo.getView(R.id.firstInputField);
        secondInputView = (EditText) solo.getView(R.id.secondInputField);
        plussButton = (Button) solo.getView(R.id.plussButton);
        minusButton = (Button) solo.getView(R.id.minusButton);
    }

    public void test_that_clicking_pluss_button_starts_result_activity() {
        clickButton(plussButton);

        solo.assertCurrentActivity("Should be ResultActivity", ResultActivity.class);
    }

    public void test_that_clicking_minus_button_starts_result_activity() {
        clickButton(minusButton);

        solo.assertCurrentActivity("Should be ResultActivity", ResultActivity.class);
    }

    public void test_add_numbers_when_pluss_button_is_clicked() {
        solo.enterText(firstInputView, "2");
        solo.enterText(secondInputView, "2");

        clickButton(plussButton);

        solo.assertCurrentActivity("Should be ResultActivity", ResultActivity.class);
        TextView resultView = (TextView) solo.getView(R.id.resultTextView);

        assertEquals("4", resultView.getText());
    }

    public void test_subtract_numbers_when_minus_button_is_clicked() {
        solo.enterText(firstInputView, "5");
        solo.enterText(secondInputView, "2");

        clickButton(minusButton);

        solo.assertCurrentActivity("Should be ResultActivity", ResultActivity.class);
        TextView resultView = (TextView) solo.getView(R.id.resultTextView);

        assertEquals("3", resultView.getText());
    }

    @Override
    public void tearDown() {
        solo.finishOpenedActivities();
    }

    private void clickButton(final Button button) {
        solo.getCurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                button.requestFocus();
            }
        });
        solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
    }

}
