package jp.co.twinearths.androidunittestappp

/**
 * the input is not valid if...
 * ... the username/password is empty
 * ... the ussername is already taken
 * ... the confirmPassword is not equal with real password
 * ... the password contains less than 2 digits
 * */
object RegistrationUtil {

    private val existingUsers = listOf("Peter", "Carl")
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        if (username.isEmpty() || password.isEmpty() ) {
            return false
        }
        if (username in existingUsers) {
            return false
        }
        if (password != confirmPassword) {
            return false
        }
        if(password.count { it.isDigit() } < 2) {
            return false
        }

        return true
    }
}