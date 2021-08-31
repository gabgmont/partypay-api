package br.com.fairie.partypay.user.testobj

import br.com.fairie.partypay.user.dao.UserEntity

class UserRepoTest {
    companion object{
        val user1 = UserEntity(
            id = 1,
            name = "Gabriel Monteiro",
            cpf = "11111111111",
            email = "gabriel@partypay.com",
            phone = "(16) 99999-8888",
            photo = "FOTO 1"
        )
        val user2 = UserEntity(
            id = 2,
            name = "Gabriel Bueno",
            cpf = "22222222222",
            email = "bueno@partypay.com",
            phone = "(16) 88877-7788",
            photo = "FOTO 2"
        )
        val user3 = UserEntity(
            id = 3,
            name = "Guilherme Vital",
            cpf = "33333333333",
            email = "guivimar@partypay.com",
            phone = "(69) 11234-2488",
            photo = "FOTO 3"
        )
        val user4 = UserEntity(
            id = 4,
            name = "Giovanni Enrico",
            cpf = "44444444444",
            email = "giovanni@partypay.com",
            phone = "(11) 989172-4918",
            photo = "FOTO 4"
        )
        val user5 = UserEntity(
            id = 5,
            name = "Sara Policarpo",
            cpf = "55555555555",
            email = "sara@partypay.com",
            phone = "(16) 98394-1746",
            photo = "FOTO 5"
        )
    }
}