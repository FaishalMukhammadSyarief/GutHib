package com.zhalz.guthib.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zhalz.guthib.data.retrofit.ApiService
import com.zhalz.guthib.data.room.user.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var userDao: UserDao

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var isFavObserver: Observer<Boolean>

    private lateinit var viewModel: DetailViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(apiService, userDao)
        viewModel.isFav.observeForever(isFavObserver)
    }

    @Test
    fun `test checkFav when user is favorite`() = runTest {
        val userId = 7
        Mockito.`when`(userDao.checkFav(userId)).thenReturn(1)
        viewModel.checkFav(userId)
        Mockito.verify(isFavObserver).onChanged(true)
    }

    @Test
    fun `test checkFav when user is not favorite`() = runTest {
        val userId = 10
        Mockito.`when`(userDao.checkFav(userId)).thenReturn(0)
        viewModel.checkFav(userId)
        Mockito.verify(isFavObserver).onChanged(false)
    }

}
