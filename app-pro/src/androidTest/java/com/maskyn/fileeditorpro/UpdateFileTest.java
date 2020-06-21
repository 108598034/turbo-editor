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
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UpdateFileTest {

    private static final long TIMEOUT = 5000;
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("touch /storage/emulated/0/Download/UpdateFileTest");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @After
    public void tearDown() throws Exception {
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @Test
    public void updateFileTest() throws UiObjectNotFoundException, InterruptedException {
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

        UiObject button =  mDevice.findObject(new UiSelector().className("android.widget.TextView").text("UpdateFileTest"));
        button.waitForExists(TIMEOUT);
        button.clickAndWaitForNewWindow();

        UiObject title =  mDevice.findObject(new UiSelector().text("UpdateFileTest"));
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
        editor.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.im_undo), withContentDescription("Undo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.im_redo), withContentDescription("Redo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView5.perform(click());

        ViewInteraction actionMenuItemView6 = onView(
                allOf(withId(R.id.im_save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView6.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.title), withText("Rename"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(android.R.id.edit), withText("UpdateFileTest"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("UpdateFileTestRENAME"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(android.R.id.edit), withText("UpdateFileTestRENAME"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction editor2 = onView(
                allOf(withId(R.id.editor), withText("test"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_scroll),
                                        childAtPosition(
                                                withId(R.id.text_editor),
                                                0)),
                                0),
                        isDisplayed()));
        editor2.perform(click());

        ViewInteraction editor3 = onView(
                allOf(withId(R.id.editor), withText("test"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_scroll),
                                        childAtPosition(
                                                withId(R.id.text_editor),
                                                0)),
                                0),
                        isDisplayed()));
        editor3.perform(replaceText("test222"));

        ViewInteraction editor4 = onView(
                allOf(withId(R.id.editor), withText("test222"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_scroll),
                                        childAtPosition(
                                                withId(R.id.text_editor),
                                                0)),
                                0),
                        isDisplayed()));
        editor4.perform(closeSoftKeyboard());

        ViewInteraction actionMenuItemView7 = onView(
                allOf(withId(R.id.im_save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView7.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.title), withText("Save as"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        UiObject mDeviceEditText = mDevice.findObject(new UiSelector().className("android.widget.EditText"));
        mDeviceEditText.waitForExists(TIMEOUT);
        mDeviceEditText.setText("UpdateFileSaveAs");

        UiObject saveButton = mDevice.findObject(new UiSelector().resourceId("android:id/button1"));
        saveButton.waitForExists(TIMEOUT);
        saveButton.clickAndWaitForNewWindow();

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
        appCompatImageButton.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withText("Open a file"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatTextView5.perform(click());

        UiObject button2 =  mDevice.findObject(new UiSelector().className("android.widget.TextView").text("UpdateFileSaveAs"));
        button2.waitForExists(TIMEOUT);
        button2.clickAndWaitForNewWindow();

        UiObject title2 =  mDevice.findObject(new UiSelector().text("UpdateFileSaveAs"));
        assertTrue(title2.exists());

        UiObject content =  mDevice.findObject(new UiSelector().text("test222\n"));
        assertTrue(title2.exists());
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
