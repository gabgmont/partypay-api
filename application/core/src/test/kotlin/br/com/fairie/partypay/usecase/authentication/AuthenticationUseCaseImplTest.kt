package br.com.fairie.partypay.usecase.authentication

import br.com.fairie.partypay.usecase.authentication.model.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.model.LoginData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class AuthenticationUseCaseImplTest {

    private val fakeToken = GeneratedToken("FAKE TOKEN 1234", "240000")

    private val service = mockk<AuthService>().also { service ->
        every { service.generateToken(any()) } returns fakeToken
    }

    @Test
    fun authenticationTokenGenerationTest(){
        val generatedToken = service.generateToken(LoginData("teste", "s3cr3t"))

        assertNotNull(generatedToken)
        assertEquals(generatedToken.token, "FAKE TOKEN 1234")
    }
}
