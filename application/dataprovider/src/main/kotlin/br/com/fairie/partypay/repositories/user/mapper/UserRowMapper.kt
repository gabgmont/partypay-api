package br.com.fairie.partypay.repositories.user.mapper

import br.com.fairie.partypay.repositories.user.dao.UserDao
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserRowMapper : RowMapper<UserDao> {
    override fun mapRow(rs: ResultSet, p1: Int): UserDao {
        return UserDao(
            id = rs.getLong(UserDao.COLUMN_ID),
            name = rs.getString(UserDao.COLUMN_NAME),
            cpf = rs.getString(UserDao.COLUMN_CPF),
            email = rs.getString(UserDao.COLUMN_EMAIL),
            secret = rs.getString(UserDao.COLUMN_SECRET),
            phone = rs.getString(UserDao.COLUMN_PHONE),
            photo = rs.getString(UserDao.COLUMN_PHOTO)
        )
    }
}