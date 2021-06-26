package jp.co.twinearths.androidunittestappp

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FibonacciTest {
    @Test
    fun `when n is 0 return 0`() {
        val result = Fibonacci.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `when n is 1 return 1`() {
        val result = Fibonacci.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun ` when n is 10 return 55`() {
        val result = Fibonacci.fib(10)
        assertThat(result).isEqualTo(55)
    }
}