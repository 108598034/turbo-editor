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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewFileTest {
    private int TIMEOUT = 3000;
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
    }

    @Test
    public void newFileTest() throws UiObjectNotFoundException, InterruptedException {
        ViewInteraction textView = onView(
                allOf(withText("New file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("New file")));

        ViewInteraction textView2 = onView(
                allOf(withText("Open a file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("Open a file")));

        ViewInteraction textView3 = onView(
                allOf(withText("Preferences"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                3),
                        isDisplayed()));
        textView3.check(matches(withText("Preferences")));

        ViewInteraction textView4 = onView(
                allOf(withText("Info"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                4),
                        isDisplayed()));
        textView4.check(matches(withText("Info")));

        textView.perform(click());

        UiObject noItems =  mDevice.findObject(new UiSelector().text("No items"));
        noItems.waitForExists(TIMEOUT);

        UiObject editText = mDevice.findObject(new UiSelector().className("android.widget.EditText"));
        editText.waitForExists(TIMEOUT);
        editText.setText("testNewFile");

        UiObject button = mDevice.findObject(new UiSelector().resourceId("android:id/button1"));
        button.waitForExists(TIMEOUT);
        button.clickAndWaitForNewWindow();

        UiObject title =  mDevice.findObject(new UiSelector().text("testNewFile"));
        assertTrue(title.exists());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Turbo Editor"),
                        childAtPosition(
                                allOf(withId(R.id.my_awesome_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        appCompatImageButton.perform(click());


        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.button_remove_from_list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());
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
