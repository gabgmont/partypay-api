package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.usecase.user.impl.UserUseCaseImpl
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserUseCaseImplTest {

    private val user = User(
            id = 1,
            name = "Gabriel",
            cpf = CPF("999.999.999-00"),
            email = Email("gabriel@email.com"),
            secret = "secret",
            phone = Phone("99 9999-9999"),
            photo = null,
            profiles = arrayListOf()
        )

    private val repository = mockk<UserRepository>().also { repository ->
        every { repository.findUser(any()) } returns arrayListOf(user)
        every { repository.findUser(null) } returns arrayListOf(user, user, user)
    }

    private val useCase = UserUseCaseImpl(repository)

    @Test
    fun getUserWithMaskedCpfTest() {
        val user = useCase.get(CPF("999.999.999-00"))

        assertNotNull(user)
        assert(user.size == 1)
        assertEquals(user.first().name, "Gabriel")
    }

    @Test
    fun getUserWithCpfNumberTest() {
        val user = useCase.get(CPF("99999999900"))

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