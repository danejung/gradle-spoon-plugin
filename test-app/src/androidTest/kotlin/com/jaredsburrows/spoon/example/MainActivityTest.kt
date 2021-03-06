package com.jaredsburrows.spoon.example

import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import android.widget.TextView
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import com.jaredsburrows.spoon.example.MainActivity
import com.squareup.spoon.SpoonRule
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  private val spoon = SpoonRule()
  private val activityRule = ActivityTestRule(MainActivity::class.java)
  @get:Rule val chain: RuleChain = RuleChain.outerRule(activityRule)
    .around(spoon)

  @Test
  @UiThreadTest
  fun testSetText() {
    val activity = activityRule.getActivity()
    val text = activity.findViewById(android.R.id.text1) as TextView
    spoon.screenshot(activity, "start")

    val steps = 5
    for (i in 1..steps) {
      val step = i.toString()
      activity.setText(step)
      spoon.screenshot(activity, "step-" + i)
      assertThat(text.getText().toString()).isEqualTo(step)
    }
  }

  @SmallTest fun testSmallTest() {
    val activity = activityRule.getActivity()

    spoon.screenshot(activity, "smallTest")
  }

  @MediumTest fun testMediumTest() {
    val activity = activityRule.getActivity()

    spoon.screenshot(activity, "mediumTest")
  }

  @LargeTest fun testLargeTest() {
    val activity = activityRule.getActivity()

    spoon.screenshot(activity, "largeTest")
  }
}
