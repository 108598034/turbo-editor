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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FindFileTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("touch /storage/emulated/0/Download/findFileTest");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @After
    public void tearDown() throws Exception {
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @Test
    public void findFileTest() throws UiObjectNotFoundException {
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

        UiObject button =  mDevice.findObject(new UiSelector().className("android.widget.TextView").text("findFileTest"));
        button.waitForExists(3000);
        button.clickAndWaitForNewWindow();

        UiObject title =  mDevice.findObject(new UiSelector().text("findFileTest"));
        assertTrue(title.exists());

        ViewInteraction editor0 = onView(
                allOf(withId(R.id.editor),
                        childAtPosition(
                                allOf(withId(R.id.vertical_scroll),
                                        childAtPosition(
                                                withId(R.id.text_editor),
                                                0)),
                                0),
                        isDisplayed()));

        editor0.perform(replaceText("Software testing is an investigation conducted to provide stakeholders with information about the quality of the software product or service under test.[1] Software testing can also provide an objective, independent view of the software to allow the business to appreciate and understand the risks of software implementation. Test techniques include the process of executing a program or application with the intent of finding software bugs (errors or other defects), and verifying that the software product is fit for use.\n" +
                "\n" +
                "Software testing involves the execution of a software component or system component to evaluate one or more properties of interest. In general, these properties indicate the extent to which the component or system under test:\n" +
                "\n" +
                "    meets the requirements that guided its design and development,\n" +
                "    responds correctly to all kinds of inputs,\n" +
                "    performs its functions within an acceptable time,\n" +
                "    it is sufficiently usable,\n" +
                "    can be installed and run in its intended environments, and\n" +
                "    achieves the general result its stakeholders desire."), closeSoftKeyboard());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.im_save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

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

        ViewInteraction appCompatTextView2_ = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2_.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Software testing"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("CANCEL")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.im_next_item), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("NEXT")));

        ViewInteraction actionMenuItemView0 = onView(
                allOf(withId(R.id.im_next_item), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView0.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("CANCEL")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.im_previous_item), withText("PREVIOUS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("PREVIOUS")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.im_next_item), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        textView5.check(matches(withText("NEXT")));

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.im_next_item), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("CANCEL")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.im_previous_item), withText("PREVIOUS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("PREVIOUS")));

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Soqwe"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Software.*ing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.regex_check), withText("Regular Expression"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.im_next_item), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("NEXT")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("CANCEL")));

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction overflowMenuButton4 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton4.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("Soft..asd"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.regex_check), withText("Regular Expression"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton5 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton5.perform(click());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView6.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Software test"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.im_next_item), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("NEXT")));

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView5.perform(click());

        ViewInteraction overflowMenuButton6 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton6.perform(click());

        ViewInteraction appCompatTextView7 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView7.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("software testing is"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox4 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox4.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton7 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton7.perform(click());

        ViewInteraction appCompatTextView8 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView8.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("Software testing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox5 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox5.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("software testing"), closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.im_replace), withText("REPLACE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        textView11.check(matches(withText("REPLACE")));

        ViewInteraction actionMenuItemView6 = onView(
                allOf(withId(R.id.im_replace), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        actionMenuItemView6.perform(click());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.im_replace), withText("REPLACE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        textView12.check(matches(withText("REPLACE")));

        ViewInteraction actionMenuItemView7 = onView(
                allOf(withId(R.id.im_replace), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        actionMenuItemView7.perform(click());

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.im_replace), withText("REPLACE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView13.check(matches(withText("REPLACE")));

        ViewInteraction actionMenuItemView8 = onView(
                allOf(withId(R.id.im_replace), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView8.perform(click());

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView14.check(matches(withText("CANCEL")));

        ViewInteraction actionMenuItemView9 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView9.perform(click());

        ViewInteraction overflowMenuButton8 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton8.perform(click());

        ViewInteraction appCompatTextView9 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView9.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("Software testing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox6 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox6.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("software testing"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.text_to_find), withText("Software testing"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("Software testingq"));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.text_to_find), withText("Software testingq"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton9 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton9.perform(click());

        ViewInteraction appCompatTextView10 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView10.perform(click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("software testing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox7 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox7.perform(click());

        ViewInteraction appCompatCheckBox8 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox8.perform(click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText14.perform(replaceText("Software testing"), closeSoftKeyboard());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.im_replace), withText("REPLACE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        textView15.check(matches(withText("REPLACE")));

        ViewInteraction actionMenuItemView10 = onView(
                allOf(withId(R.id.im_replace), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        actionMenuItemView10.perform(click());

        ViewInteraction actionMenuItemView11 = onView(
                allOf(withId(R.id.im_replace), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        actionMenuItemView11.perform(click());

        ViewInteraction actionMenuItemView12 = onView(
                allOf(withId(R.id.im_replace), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView12.perform(click());

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView16.check(matches(withText("CANCEL")));

        ViewInteraction actionMenuItemView13 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView13.perform(click());

        ViewInteraction overflowMenuButton10 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton10.perform(click());

        ViewInteraction appCompatTextView11 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView11.perform(click());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText15.perform(replaceText("software testing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox9 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox9.perform(click());

        ViewInteraction appCompatCheckBox10 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox10.perform(click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText16.perform(replaceText("q"), closeSoftKeyboard());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton11 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton11.perform(click());

        ViewInteraction appCompatTextView12 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView12.perform(click());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("Software.*ing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox11 = onView(
                allOf(withId(R.id.regex_check), withText("Regular Expression"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatCheckBox11.perform(click());

        ViewInteraction appCompatCheckBox12 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox12.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView17.check(matches(withText("CANCEL")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.im_next_item), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        textView18.check(matches(withText("NEXT")));

        ViewInteraction actionMenuItemView14 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView14.perform(click());

        ViewInteraction overflowMenuButton12 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton12.perform(click());

        ViewInteraction appCompatTextView13 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView13.perform(click());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText18.perform(replaceText("software.*ing is"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox13 = onView(
                allOf(withId(R.id.regex_check), withText("Regular Expression"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatCheckBox13.perform(click());

        ViewInteraction appCompatCheckBox14 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox14.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton13 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton13.perform(click());

        ViewInteraction appCompatTextView14 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView14.perform(click());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText19.perform(replaceText("Software.*ing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox15 = onView(
                allOf(withId(R.id.regex_check), withText("Regular Expression"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatCheckBox15.perform(click());

        ViewInteraction appCompatCheckBox16 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox16.perform(click());

        ViewInteraction appCompatCheckBox17 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox17.perform(click());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText20.perform(replaceText("q"), closeSoftKeyboard());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.im_replace), withText("REPLACE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        textView19.check(matches(withText("REPLACE")));

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView20.check(matches(withText("CANCEL")));

        ViewInteraction actionMenuItemView15 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView15.perform(click());

        ViewInteraction overflowMenuButton14 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton14.perform(click());

        ViewInteraction appCompatTextView15 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView15.perform(click());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText21.perform(replaceText("software.*ing is"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox18 = onView(
                allOf(withId(R.id.regex_check), withText("Regular Expression"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatCheckBox18.perform(click());

        ViewInteraction appCompatCheckBox19 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox19.perform(click());

        ViewInteraction appCompatCheckBox20 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox20.perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText22.perform(replaceText("q"), closeSoftKeyboard());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton14.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton15 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton15.perform(click());

        ViewInteraction appCompatTextView16 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView16.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText23.perform(replaceText("Software.*ing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox21 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox21.perform(click());

        ViewInteraction appCompatCheckBox22 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox22.perform(click());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText24.perform(replaceText("q"), closeSoftKeyboard());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton15.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton16 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        overflowMenuButton16.perform(click());

        ViewInteraction appCompatTextView17 = onView(
                allOf(withId(R.id.title), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView17.perform(click());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.text_to_find),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText25.perform(replaceText("Software testing"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox23 = onView(
                allOf(withId(R.id.match_case_check), withText("Match case"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatCheckBox23.perform(click());

        ViewInteraction appCompatCheckBox24 = onView(
                allOf(withId(R.id.replace_check), withText("Replace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatCheckBox24.perform(click());

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.text_to_replace),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText26.perform(replaceText("q"), closeSoftKeyboard());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(android.R.id.button1), withText("Find"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton16.perform(scrollTo(), click());

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.im_replace), withText("REPLACE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                2),
                        isDisplayed()));
        textView21.check(matches(withText("REPLACE")));

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.im_cancel), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView22.check(matches(withText("CANCEL")));

        ViewInteraction actionMenuItemView16 = onView(
                allOf(withId(R.id.im_cancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_awesome_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView16.perform(click());
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
