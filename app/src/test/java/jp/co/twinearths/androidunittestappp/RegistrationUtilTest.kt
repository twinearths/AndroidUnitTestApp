package jp.co.twinearths.androidunittestappp

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated passwordreturn true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "123",
            "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username exists return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Peter",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password is empty return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Peter",
            "",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Philipp",
            "abcdef5",
            "abcdef5"
        )

        assertThat(result).isFalse()
    }
}