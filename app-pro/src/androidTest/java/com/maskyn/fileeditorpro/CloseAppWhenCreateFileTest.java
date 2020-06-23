package com.maskyn.fileeditorpro;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CloseAppWhenCreateFileTest {

    private static final long TIMEOUT = 3000;
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @After
    public void tearDown() throws Exception {
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
        mActivityTestRule.finishActivity();
        mDevice.pressBack();
        mDevice.pressHome();
    }

    @Test
    public void closeAppWhenCreateFileTest() throws UiObjectNotFoundException {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("New file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatTextView.perform(click());


        UiObject editText = mDevice.findObject(new UiSelector().className("android.widget.EditText"));
        editText.waitForExists(TIMEOUT);
        editText.setText("CloseAppWhenCreateFile");
        mActivityTestRule.finishActivity();
        mDevice.pressBack();
        mDevice.pressHome();
        mActivityTestRule.launchActivity(null);

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Open a file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        UiObject title =  mDevice.findObject(new UiSelector().text("CloseAppWhenCreateFile"));
        assertFalse(title.exists());

        mActivityTestRule.finishActivity();
        mDevice.pressBack();
        mDevice.pressHome();
        mActivityTestRule.launchActivity(null);

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("New file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        UiObject editText2 = mDevice.findObject(new UiSelector().className("android.widget.EditText"));
        editText2.waitForExists(TIMEOUT);
        editText2.setText("CloseAppWhenCreateFile");

        UiObject button = mDevice.findObject(new UiSelector().resourceId("android:id/button1"));
        button.waitForExists(TIMEOUT);
        button.clickAndWaitForNewWindow();

        UiObject title2 =  mDevice.findObject(new UiSelector().text("CloseAppWhenCreateFile"));
        assertTrue(title2.exists());


        mActivityTestRule.finishActivity();
        mDevice.pressBack();
        mDevice.pressHome();
        mActivityTestRule.launchActivity(null);

        ViewInteraction appCompatTextView4 = onView(
                allOf(withText("Open a file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        UiObject title3 =  mDevice.findObject(new UiSelector().text("CloseAppWhenCreateFile"));
        assertTrue(title3.exists());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
