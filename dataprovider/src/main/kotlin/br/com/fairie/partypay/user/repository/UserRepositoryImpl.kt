package br.com.fairie.partypay.user.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.SQLCallException
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.user.dao.UserDao
import br.com.fairie.partypay.user.repository.mapper.UserRowMapper
import br.com.fairie.partypay.user.repository.mapper.toUserList
import br.com.fairie.partypay.vo.CPF
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapperResultSetExtractor
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.sql.ResultSet

class UserRepositoryImpl(private val jdbc: JdbcTemplate) : UserRepository {

    companion object {
        private const val CALL_FIND_USER_BY_CPF = "{SELECT * FROM users_tbl WHERE cpf = '@p_cpf'}"
        private const val CALL_FIND_USER_BY_EMAIL = "{SELECT * FROM users_tbl WHERE email = '@p_email'}"
        private const val CALL_FIND_USER_BY_ID = "{SELECT * FROM users_tbl WHERE id = '@p_id'}"
        private const val CALL_FIND_ALL_USERS = "{SELECT * FROM users_tbl}"

        private const val ERROR_USER_NOT_FOUND = "User not found."
        private const val ERROR_SQL_CALL_EXCEPTION = "Exception occurred while trying to retrieve data from database."
        private const val ERROR_BAD_REQUEST = "Bad request"

    }

    override fun findUser(cpf: CPF?): List<User> {

        val sql = if (cpf != null) {
            CALL_FIND_USER_BY_CPF.replace("@p_cpf", cpf.value)
        } else {
            CALL_FIND_ALL_USERS
        }

        val users = try {
            jdbc.execute(sql) { ps ->
                println("${javaClass.kotlin.simpleName}: MONTAGEM: SELECT ${ps.toString().substringAfter("SELECT ")}")

                val execute = ps.executeQuery()
                mapUserRows(execute)

            }?.toUserList() ?: arrayListOf()

        } catch (exception: Exception) {
            throw SQLCallException(ERROR_SQL_CALL_EXCEPTION)
        }
        if (users.isEmpty()) throw NotFoundException(ERROR_USER_NOT_FOUND)

        return users
    }

    override fun findUserByEmail(email: String?): User {
        val sql = CALL_FIND_USER_BY_EMAIL.replace("@p_email", email ?: "")

        val users = try {
            jdbc.execute(sql) { ps ->
                println("${javaClass.kotlin.simpleName}: MONTAGEM: SELECT ${ps.toString().substringAfter("SELECT ")}")

                val execute = ps.executeQuery()
                mapUserRows(execute)
            }?.toUserList() ?: arrayListOf()

        } catch (exception: Exception) {
            throw SQLCallException(ERROR_SQL_CALL_EXCEPTION)
        }
        if (users.isEmpty()) throw UsernameNotFoundException(ERROR_USER_NOT_FOUND)

        return users.first()
    }

    override fun findUserById(userId: Long): User {
        val sql = CALL_FIND_USER_BY_ID.replace("@p_id", userId.toString())

        val users = try {
            jdbc.execute(sql) { ps ->
                println("${javaClass.kotlin.simpleName}: MONTAGEM: SELECT ${ps.toString().substringAfter("SELECT ")}")

                val execute = ps.executeQuery()
                mapUserRows(execute)
            }?.toUserList() ?: arrayListOf()

        } catch (exception: Exception) {
            throw SQLCallException(ERROR_SQL_CALL_EXCEPTION)
        }
        if (users.isEmpty()) throw UsernameNotFoundException(ERROR_USER_NOT_FOUND)

        return users.first()    }
}

private fun mapUserRows(resultSet: ResultSet): List<UserDao> {
    return RowMapperResultSetExtractor(UserRowMapper()).extractData(resultSet)
}
