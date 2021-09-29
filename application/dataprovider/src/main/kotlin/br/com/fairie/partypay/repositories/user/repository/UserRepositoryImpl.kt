package br.com.fairie.partypay.repositories.user.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.SQLCallException
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.repositories.user.dao.UserDao
import br.com.fairie.partypay.repositories.user.mapper.UserRowMapper
import br.com.fairie.partypay.repositories.user.mapper.toUserList
import br.com.fairie.partypay.repositories.user.sql.*
import br.com.fairie.partypay.vo.CPF
import org.springframework.jdbc.core.CallableStatementCallback
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapperResultSetExtractor
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.sql.ResultSet

class UserRepositoryImpl(private val jdbc: JdbcTemplate) : UserRepository {

    companion object {
        private const val ERROR_USER_NOT_FOUND = "User not found:"
        private const val ERROR_SQL_SELECT_EXCEPTION = "Exception occurred while trying to retrieve data from database."
    }

    override fun registerUser(user: User): User {
        jdbc.execute(INSERT_REGISTER_USER, CallableStatementCallback { cs ->
            cs.setString(1, user.name)
            cs.setString(2, user.cpf.value)
            cs.setString(3, user.email.value)
            cs.setString(4, user.secret)
            cs.setString(5, user.phone.value)

            cs.execute()
        })
        return user
    }


    override fun findUser(cpf: CPF?): List<User> {

        val sql = if (cpf != null) {
            SELECT_FIND_USER_BY_CPF.replace("@p_cpf", cpf.value)
        } else {
            SELECT_FIND_ALL_USERS
        }

        val users = executeRequest(sql)
        if (users.isEmpty()) throw NotFoundException("$ERROR_USER_NOT_FOUND ${cpf?.value}")

        return users
    }

    override fun findUserByEmail(email: String?): User {
        val sql = SELECT_FIND_USER_BY_EMAIL.replace("@p_email", email ?: "")

        val users = executeRequest(sql)
        if (users.isEmpty()) throw UsernameNotFoundException(ERROR_USER_NOT_FOUND)

        return users.first()
    }

    override fun findUserById(userId: Long): User {
        val sql = SELECT_FIND_USER_BY_ID.replace("@p_id", userId.toString())

        val users = executeRequest(sql)
        if (users.isEmpty()) throw UsernameNotFoundException(ERROR_USER_NOT_FOUND)

        return users.first()
    }

    private fun executeRequest(sql: String): List<User> {
        return try {
            jdbc.execute(sql) { ps ->
                println("${javaClass.kotlin.simpleName}: MONTAGEM: SELECT ${ps.toString().substringAfter("SELECT ")}")

                ps.executeQuery().use { rs ->
                    mapUserRows(rs)
                }

            }?.toUserList() ?: arrayListOf()

        } catch (exception: Exception) {
            throw SQLCallException(ERROR_SQL_SELECT_EXCEPTION)
        }
    }

    private fun mapUserRows(resultSet: ResultSet): List<UserDao> {
        return RowMapperResultSetExtractor(UserRowMapper()).extractData(resultSet)
    }
}