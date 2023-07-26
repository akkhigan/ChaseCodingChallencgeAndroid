package com.chase.codechallenge

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    @get:Rule
    var hiltrule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltrule.inject()
    }
    @Test
    fun some(){

    }
}