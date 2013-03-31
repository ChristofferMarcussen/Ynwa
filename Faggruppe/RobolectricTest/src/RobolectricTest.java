import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.bekk.Faggruppe.R;
import com.bekk.Faggruppe.ResultActivity;
import com.bekk.Faggruppe.TraditionalActivity;
import com.bekk.Faggruppe.TraditionalResultActivity;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;
import com.xtremelabs.robolectric.shadows.ShadowTextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RobolectricTest {
    private TraditionalResultActivity resultActivity;
    private TraditionalActivity activity;
    private Button plussButton;
    private Button minusButton;
    private EditText firstInput;
    private EditText secondInput;
    private TextView resultTextView;

    @Before
    public void setUp() {
        activity = new TraditionalActivity();
        resultActivity = new TraditionalResultActivity();
        activity.onCreate(null);

        plussButton = (Button) activity.findViewById(R.id.plussButton);
        minusButton = (Button) activity.findViewById(R.id.minusButton);
        firstInput = (EditText) activity.findViewById(R.id.firstInputField);
        secondInput = (EditText) activity.findViewById(R.id.secondInputField);
    }

    @Test
    public void should_have_two_buttons() {
        assertNotNull(plussButton);
        assertNotNull(minusButton);
    }

    @Test
    public void should_have_two_input_fields() {
        assertNotNull(firstInput);
        assertNotNull(secondInput);
    }

    @Test
    public void should_start_result_activity_when_pluss_button_is_clicked() {
        plussButton.performClick();

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(ResultActivity.class.getName()));
    }

    @Test
    public void should_start_result_activity_when_minus_button_is_clicked() {
        plussButton.performClick();

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(ResultActivity.class.getName()));
    }

    @Test
    public void should_start_result_activity_when_user_clicks_add_button_and_add_numbers() {
        firstInput.setText("2");
        secondInput.setText("1");
        plussButton.performClick();

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

        resultActivity.setIntent(startedIntent);
        resultActivity.onCreate(null);
        resultTextView = (TextView) resultActivity.findViewById(R.id.resultTextView);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(ResultActivity.class.getName()));
        assertEquals("add", shadowIntent.getExtras().getString("operator"));
        assertEquals("2", shadowIntent.getExtras().getString("firstNumber"));
        assertEquals("1", shadowIntent.getExtras().getString("secondNumber"));
        assertEquals("3", resultTextView.getText());
    }

    @Test
    public void should_start_result_activity_when_user_clicks_subtract_button_and_subtract_numbers() {
        firstInput.setText("2");
        secondInput.setText("1");
        minusButton.performClick();

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

        resultActivity = new TraditionalResultActivity();
        resultActivity.setIntent(startedIntent);
        resultActivity.onCreate(null);
        resultTextView = (TextView) resultActivity.findViewById(R.id.resultTextView);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(ResultActivity.class.getName()));
        assertEquals("subtract", shadowIntent.getExtras().getString("operator"));
        assertEquals("2", shadowIntent.getExtras().getString("firstNumber"));
        assertEquals("1", shadowIntent.getExtras().getString("secondNumber"));
        assertEquals("1", resultTextView.getText());
    }

}
