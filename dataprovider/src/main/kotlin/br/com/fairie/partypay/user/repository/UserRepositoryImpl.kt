package br.com.fairie.partypay.user.repository

import br.com.fairie.partypay.exception.BadRequestException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.SQLCallException
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.user.dao.UserEntity
import br.com.fairie.partypay.user.repository.mapper.UserRowMapper
import br.com.fairie.partypay.user.repository.mapper.toUserList
import br.com.fairie.partypay.vo.CPF
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapperResultSetExtractor
import java.sql.ResultSet

class UserRepositoryImpl(private val jdbc: JdbcTemplate) : UserRepository {

    companion object {
        private const val CALL_FIND_USER_BY_CPF = "{SELECT * from USERS_TBL where CPF = '@p_cpf'}"

        private const val CALL_FIND_ALL_USERS = "{SELECT * from USERS_TBL}"

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
            jdbc.execute(sql) { cs ->
                println("${javaClass.kotlin.simpleName}: MONTAGEM CALL: ${cs.toString().substringAfter("CALL")}")

                val execute = cs.executeQuery()
                mapUserRows(execute)

            }?.toUserList() ?: arrayListOf()

        } catch (exception: Exception) {
            throw SQLCallException(ERROR_SQL_CALL_EXCEPTION)
        }
        if (users.isEmpty()) throw NotFoundException(ERROR_USER_NOT_FOUND)

        return users
    }

    private fun mapUserRows(resultSet: ResultSet): List<UserEntity> {
        return RowMapperResultSetExtractor(UserRowMapper()).extractData(resultSet)
    }
}