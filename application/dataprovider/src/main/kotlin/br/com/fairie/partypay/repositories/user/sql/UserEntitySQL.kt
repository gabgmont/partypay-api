package br.com.fairie.partypay.repositories.user.sql

const val SELECT_FIND_USER_BY_CPF = "{SELECT * FROM users_tbl WHERE cpf = '@p_cpf'}"
const val SELECT_FIND_USER_BY_EMAIL = "{SELECT * FROM users_tbl WHERE email = '@p_email'}"
const val SELECT_FIND_USER_BY_ID = "{SELECT * FROM users_tbl WHERE id = '@p_id'}"
const val SELECT_FIND_ALL_USERS = "{SELECT * FROM users_tbl}"