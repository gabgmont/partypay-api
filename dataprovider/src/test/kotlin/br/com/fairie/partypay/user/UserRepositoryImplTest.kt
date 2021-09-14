package br.com.fairie.partypay.user

import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import java.sql.PreparedStatement
import java.sql.ResultSet

@SpringBootTest
class UserRepositoryImplTest {

    private val mockResultSet = mockk<ResultSet>().also { rs ->
        every { rs.getString(any<String>()) } returns "teste"
        every { rs.getLong(any<String>()) } returns 999L
    }

    private val mockPreparedStatement = mockk<PreparedStatement>().also { ps ->
        every { ps.executeQuery() } returns mockResultSet
    }

//    private val mockJdbc = mockk<JdbcTemplate>().also { jdbc ->
//        every { jdbc.execute(any()) } returns mockPreparedStatement
//    }
}