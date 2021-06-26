package jp.co.twinearths.androidunittestappp.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import jp.co.twinearths.androidunittestappp.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingItemDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.shoppingDao()
    }

    @After
    fun teadown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runBlocking {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItems = dao.observerAppShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {

        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteSHoppingItem(shoppingItem)
        val allShoppingItems = dao.observerAppShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalSum() = runBlockingTest {

        val shoppingItem = ShoppingItem("name", 2, 10f, "url", id = 1)
        val shoppingItem1 = ShoppingItem("name1", 4, 5.5f, "url", id = 2)
        val shoppingItem2 = ShoppingItem("name2", 1, 100f, "url", id = 3)
        dao.insertShoppingItem(shoppingItem)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        val totalPriceSum = dao.observerTotalPrice().getOrAwaitValue()

        assertThat(totalPriceSum).isEqualTo(2*10f + 4*5.5f + 100f)
    }
}