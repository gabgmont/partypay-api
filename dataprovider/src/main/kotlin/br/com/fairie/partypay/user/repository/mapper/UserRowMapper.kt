package br.com.fairie.partypay.user.repository.mapper

import br.com.fairie.partypay.user.dao.UserEntity
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserRowMapper : RowMapper<UserEntity> {
    override fun mapRow(rs: ResultSet, p1: Int): UserEntity {
        return UserEntity(
            id = rs.getLong(UserEntity.COLUMN_ID),
            name = rs.getString(UserEntity.COLUMN_NAME),
            cpf = rs.getString(UserEntity.COLUMN_CPF),
            email = rs.getString(UserEntity.COLUMN_EMAIL),
            phone = rs.getString(UserEntity.COLUMN_PHONE),
            photo = rs.getString(UserEntity.COLUMN_PHOTO)
        )
    }
}