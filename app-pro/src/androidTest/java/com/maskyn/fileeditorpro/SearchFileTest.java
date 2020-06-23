package com.maskyn.fileeditorpro;


import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.Key;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchFileTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("touch /storage/emulated/0/Download/SearchFileTest");
        mDevice.executeShellCommand("touch /storage/emulated/0/Download/213");
        mDevice.executeShellCommand("touch /storage/emulated/0/Download/1234");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @After
    public void tearDown() throws Exception {
        mDevice.executeShellCommand("rm -rf /storage/emulated/0/Download/");
        mDevice.executeShellCommand("mkdir /storage/emulated/0/Download");
        mDevice.executeShellCommand("am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///storage/emulated/0/Download");
    }

    @Test
    public void searchFileTest() throws UiObjectNotFoundException, InterruptedException {
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

        List<UiObject2> item_roots = mDevice.findObjects(By.res("com.android.documentsui:id/item_root"));
        assertEquals(3, item_roots.size());

        UiObject button =  mDevice.findObject(new UiSelector().resourceId("com.android.documentsui:id/option_menu_search"));
        button.waitForExists(3000);
        button.click();

        UiObject input =  mDevice.findObject(new UiSelector().resourceId("com.android.documentsui:id/search_src_text"));
        input.waitForExists(3000);
        input.setText("");
        mDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_S);
        mDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_E);
        Thread.sleep(2000);

        List<UiObject2> item_roots2 = mDevice.findObjects(By.res("com.android.documentsui:id/item_root"));
        assertEquals(1, item_roots2.size());

        UiObject button1 =  mDevice.findObject(new UiSelector().className("android.widget.TextView").text("SearchFileTest"));
        button1.waitForExists(3000);
        button1.clickAndWaitForNewWindow();


        UiObject title =  mDevice.findObject(new UiSelector().text("SearchFileTest"));
        TestCase.assertTrue(title.exists());
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
