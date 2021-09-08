package br.com.fairie.partypay.usecase.authentication

import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData
import br.com.fairie.partypay.vo.Email
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthenticationUseCaseImplTest {

    private val fakeToken = GeneratedToken("FAKE TOKEN 1234", "240000")

    private val service = mockk<AuthService>().also { service ->
        every { service.generateToken(any()) } returns fakeToken
    }

    @Test
    fun authenticationTokenGenerationTest(){
        val generatedToken = service.generateToken(LoginData(Email("teste@email.com"), "s3cr3t"))

        assertNotNull(generatedToken)
        assertEquals(generatedToken.token, "FAKE TOKEN 1234")
    }
}