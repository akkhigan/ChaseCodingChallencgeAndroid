package com.chase.codechallenge

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chase.codechallenge.domain.repository.ForecastRepository
import com.chase.codechallenge.domain.repository.MyCityRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchViewModelTest {
    @get:Rule
    var hiltrule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: ForecastRepository
    @Inject
    lateinit var repositoryCity: MyCityRepository

    @Before
    fun setUp() {
        hiltrule.inject()
    }

    @Test
    fun some(){
        //
    }
}