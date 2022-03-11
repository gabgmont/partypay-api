package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.impl.UserUseCaseImpl
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class UserUseCaseImplTest {

    private val user = User(
            id = 1,
            name = "Gabriel",
            username = "gabriel",
            email = Email("gabriel@email.com"),
            secret = "secret",
            photo = null,
            profiles = arrayListOf()
        )

    private val repository = mockk<UserRepository>().also { repository ->
        every { repository.findUserByUsername(any()) } returns arrayListOf(user)
        every { repository.findUserByUsername(null) } returns arrayListOf(user, user, user)
    }

    private val useCase = UserUseCaseImpl(repository)

    @Test
    fun getUserWithUsernameTest() {
        val user = useCase.get("gabgmont")

        assertNotNull(user)
        assert(user.size == 1)
        assertEquals(user.first().name, "Gabriel")
    }

    @Test
    fun getAllUsersTest() {
        val user = useCase.get(null)

        assertNotNull(user)
        assert(user.size > 1)
    }
}
