package com.maskyn.fileeditorpro;


import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReplaceTextKeepCursorTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("touch /storage/emulated/0/Download/ReplaceTextKeepCursor");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @After
    public void tearDown() throws Exception {
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @Test
    public void replaceTextKeepCursorTest() throws UiObjectNotFoundException {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Open a file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());

        UiObject button =  mDevice.findObject(new UiSelector().className("android.widget.TextView").text("ReplaceTextKeepCursor"));
        button.waitForExists(3000);
        button.clickAndWaitForNewWindow();

        UiObject title =  mDevice.findObject(new UiSelector().text("ReplaceTextKeepCursor"));
        assertTrue(title.exists());

        ViewInteraction editor = onView(
                allOf(withId(R.id.editor),
                        childAtPosition(
                                allOf(withId(R.id.vertical_scroll),
                                        childAtPosition(
                                                withId(R.id.text_editor),
                                                0)),
                                0),
                        isDisplayed()));
        editor.perform(replaceText("test for highlight"), closeSoftKeyboard());

        ViewInteraction editor2 = onView(
                allOf(withId(R.id.editor), withText("test for highlight"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_scroll),
                                        childAtPosition(
                                                withId(R.id.text_editor),
                                                0)),
                                0),
                        isDisplayed()));
        editor2.perform(replaceText("test for replace"));

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
