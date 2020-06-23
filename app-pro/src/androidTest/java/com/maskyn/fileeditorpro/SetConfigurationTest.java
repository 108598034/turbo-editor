package com.maskyn.fileeditorpro;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SetConfigurationTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void setConfigurationTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Preferences"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_files),
                                                2)),
                                3),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction switchCompat = onView(
                allOf(withId(R.id.switch_line_numbers), withText("Line Numbers"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                1)));
        switchCompat.check(matches(isChecked()));
        switchCompat.perform(scrollTo(), click());
        switchCompat.check(matches(isNotChecked()));

        ViewInteraction switchCompat2 = onView(
                allOf(withId(R.id.switch_line_numbers), withText("Line Numbers"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                1)));
        switchCompat2.check(matches(isNotChecked()));
        switchCompat2.perform(scrollTo(), click());
        switchCompat2.check(matches(isChecked()));

        ViewInteraction switchCompat3 = onView(
                allOf(withId(R.id.switch_syntax), withText("Syntax highlight"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                2)));
        switchCompat3.check(matches(isNotChecked()));
        switchCompat3.perform(scrollTo(), click());
        switchCompat3.check(matches(isChecked()));

        ViewInteraction switchCompat4 = onView(
                allOf(withId(R.id.switch_syntax), withText("Syntax highlight"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                2)));
        switchCompat4.check(matches(isChecked()));
        switchCompat4.perform(scrollTo(), click());
        switchCompat4.check(matches(isNotChecked()));


        ViewInteraction switchCompat5 = onView(
                allOf(withId(R.id.switch_wrap_content), withText("Wrap content"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                3)));
        switchCompat5.check(matches(isChecked()));
        switchCompat5.perform(scrollTo(), click());
        switchCompat5.check(matches(isNotChecked()));

        ViewInteraction switchCompat6 = onView(
                allOf(withId(R.id.switch_wrap_content), withText("Wrap content"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                3)));
        switchCompat6.check(matches(isNotChecked()));
        switchCompat6.perform(scrollTo(), click());
        switchCompat6.check(matches(isChecked()));

        ViewInteraction switchCompat7 = onView(
                allOf(withId(R.id.switch_monospace), withText("Use monospace"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                4)));
        switchCompat7.check(matches(isNotChecked()));
        switchCompat7.perform(scrollTo(), click());
        switchCompat7.check(matches(isChecked()));

        ViewInteraction switchCompat8 = onView(
                allOf(withId(R.id.switch_monospace), withText("Use monospace"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                4)));
        switchCompat8.check(matches(isChecked()));
        switchCompat8.perform(scrollTo(), click());
        switchCompat8.check(matches(isNotChecked()));

        ViewInteraction switchCompat9 = onView(
                allOf(withId(R.id.switch_read_only), withText("Read only"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                5)));
        switchCompat9.check(matches(isNotChecked()));
        switchCompat9.perform(scrollTo(), click());
        switchCompat9.check(matches(isChecked()));

        ViewInteraction switchCompat10 = onView(
                allOf(withId(R.id.switch_read_only), withText("Read only"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                5)));
        switchCompat10.check(matches(isChecked()));
        switchCompat10.perform(scrollTo(), click());
        switchCompat10.check(matches(isNotChecked()));

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.drawer_button_font_size), withText("Font size"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                6)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.drawer_button_font_size), withText("Font size"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                6)));
        appCompatTextView3.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.drawer_button_extra_options), withText("Extra options"),
                        childAtPosition(
                                allOf(withId(R.id.drawer_buttons),
                                        childAtPosition(
                                                withId(R.id.drawer_settings),
                                                0)),
                                7)));
        appCompatTextView4.perform(scrollTo(), click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.drawer_button_theme), withText("Theme"),
                        childAtPosition(
                                allOf(withId(R.id.other_options),
                                        childAtPosition(
                                                withId(R.id.drawer_buttons),
                                                8)),
                                0)));
        appCompatTextView5.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.drawer_button_theme), withText("Theme"),
                        childAtPosition(
                                allOf(withId(R.id.other_options),
                                        childAtPosition(
                                                withId(R.id.drawer_buttons),
                                                8)),
                                0)));
        appCompatTextView6.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction switchCompat11 = onView(
                allOf(withId(R.id.switch_accessory_view), withText("Accessory view"),
                        childAtPosition(
                                allOf(withId(R.id.other_options),
                                        childAtPosition(
                                                withId(R.id.drawer_buttons),
                                                8)),
                                1)));

        switchCompat11.check(matches(isChecked()));
        switchCompat11.perform(scrollTo(), click());
        switchCompat11.check(matches(isNotChecked()));

        ViewInteraction switchCompat12 = onView(
                allOf(withId(R.id.switch_accessory_view), withText("Accessory view"),
                        childAtPosition(
                                allOf(withId(R.id.other_options),
                                        childAtPosition(
                                                withId(R.id.drawer_buttons),
                                                8)),
                                1)));
        switchCompat12.check(matches(isNotChecked()));
        switchCompat12.perform(scrollTo(), click());
        switchCompat12.check(matches(isChecked()));
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
