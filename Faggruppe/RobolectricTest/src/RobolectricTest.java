import android.app.Activity;
import android.widget.Button;
import com.bekk.Faggruppe.R;
import com.bekk.Faggruppe.ResultActivity;
import com.bekk.Faggruppe.RoboGuiceActivity;
import com.google.inject.Inject;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.matchers.StartedMatcher;
import com.xtremelabs.robolectric.shadows.ShadowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import roboguice.inject.InjectView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RobolectricTest {

    @Inject private Activity activity;
//    @InjectView(R.id.plussButton) Button plussButton;
    Button plussButton;
    @InjectView(R.id.minusButton) Button minusButton;

    @Before
    public void setUp() {

        // Finne ut hvorfor oncreate er protected
        activity = new RoboGuiceActivity();
        activity.setContentView(R.layout.main);
        plussButton = (Button) activity.findViewById(R.id.plussButton);
    }

    @Test
    public void should_find_correct_activity() throws Exception {
        String appName = new RoboGuiceActivity().getResources().getString(R.string.app_name);

        assertEquals("RoboGuiceActivity", appName);
    }

    @Test
    public void should_start_result_activity_when_user_clicks_add_button() {
        plussButton.performClick();

        assertThat(activity, new StartedMatcher(ResultActivity.class));
    }

    @Test
    public void should_start_result_activity_when_user_clicks_subtract_button() {
        assertEquals(ResultActivity.class, new StartedMatcher(ResultActivity.class));
    }
}
