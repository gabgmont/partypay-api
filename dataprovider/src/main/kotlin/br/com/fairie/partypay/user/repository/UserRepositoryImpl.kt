package br.com.fairie.partypay.user.repository

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

        private const val USER_NOT_FOUND = "User not found."
        private const val SQL_CALL_EXCEPTION = "Exception occurred while trying to retrieve data from database."

    }

    override fun findByCpf(cpf: CPF): List<User> {

        val sql = CALL_FIND_USER_BY_CPF.replace("@p_cpf", cpf.value)
        val users = try {
            jdbc.execute(sql) { cs ->
                println("${javaClass.kotlin.simpleName}: MONTAGEM CALL: ${cs.toString().substringAfter("CALL")}")

                val execute = cs.executeQuery()
                mapUserRows(execute)

            }?.toUserList() ?: arrayListOf()

        } catch (exception: Exception) {
            throw SQLCallException(SQL_CALL_EXCEPTION)
        }
        if (users.isEmpty()) throw NotFoundException(USER_NOT_FOUND)

        return users
    }

    private fun mapUserRows(resultSet: ResultSet): List<UserEntity> {
        return RowMapperResultSetExtractor(UserRowMapper()).extractData(resultSet)
    }
}