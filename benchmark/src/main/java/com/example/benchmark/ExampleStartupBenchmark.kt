package com.example.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "kth.compose3",
        metrics = listOf(StartupTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun scroll() = benchmarkRule.measureRepeated(
        packageName = "kth.compose3",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.HOT,
        setupBlock = {
            startActivityAndWait()
        }
    ) {

        val list = device.findObject(By.desc("item_list"))

        device.waitForIdle()

        if (list != null) {
            list.setGestureMargin(device.displayWidth / 3)
            repeat(12) { //Should be set to 1/4 of the elements in the complete list
                list.fling(Direction.DOWN, 1000)
                device.waitForIdle()
            }
            repeat(12) {//Same as previous
                list.fling(Direction.UP, 1000)
                device.waitForIdle()
            }
        }

    }

    @Test
    fun navigateUserJourney() = benchmarkRule.measureRepeated(
        packageName = "kth.compose3",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            startActivityAndWait()
        }

    ) {
        device.findObject(By.desc("Heart Button 1")).click()
        device.findObject(By.desc("My Favorites")).click()
        device.findObject(By.desc("Drink Card 1")).click()
    }

    @Test
    fun navigateBottomMenu() = benchmarkRule.measureRepeated(
        packageName = "kth.compose3",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            startActivityAndWait()
        }

    ) {
        repeat(5) {
            device.findObject(By.desc("Drinks")).click()
            device.findObject(By.desc("My Favorites")).click()
            device.findObject(By.desc("Suggestion")).click()
        }

    }


}
