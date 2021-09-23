package br.com.fairie.partypay.repositories.session.mapper

import br.com.fairie.partypay.entity.OrderEntity
import br.com.fairie.partypay.entity.SessionEntity
import br.com.fairie.partypay.entity.SessionOrderEntity
import br.com.fairie.partypay.entity.UserEntity
import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

fun SessionEntity.toSession(): Session {
    return Session(
        id = id,
        restaurant = restaurant,
        table = counter,
        status = status,
        users = users.toUserList(),
        orders = orders.toSessionOrderList()
    )
}

fun List<UserEntity>.toUserList(): MutableList<User> {
    val userList = ArrayList<User>()

    forEach { user ->
        userList.add(
            User(
                id = user.id,
                name = user.name,
                cpf = CPF(user.cpf),
                email = Email(user.email),
                secret = user.secret,
                phone = Phone(user.phone),
                photo = Photo(user.photo ?: ""),
                profiles = arrayListOf(),
            )
        )
    }
    return userList
}

fun List<SessionOrderEntity>.toSessionOrderList(): MutableList<SessionOrder> {
    val sessionOrderList = ArrayList<SessionOrder>()

    forEach { sessionOder ->
        val userList = ArrayList<User>()

        sessionOder.users.forEach { user ->
            userList.add(
                User(
                    id = user.id,
                    name = user.name,
                    cpf = CPF(user.cpf),
                    email = Email(user.email),
                    secret = user.secret,
                    phone = Phone(user.phone),
                    photo = Photo(user.photo ?: ""),
                    profiles = arrayListOf(),
                )
            )
        }
        val order = Order(
            name = sessionOder.order.name,
            description = sessionOder.order.description,
            value = sessionOder.order.value
        )

        sessionOrderList.add(SessionOrder(order, userList))
    }

    return sessionOrderList
}

fun Session.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = id,
        restaurant = restaurant,
        counter = table,
        status = status,
        users = users.toUserEntityList(),
        orders = orders.toSessionOrderEntityList()
    )
}

fun MutableList<User>.toUserEntityList(): List<UserEntity> {
    val userList = ArrayList<UserEntity>()

    forEach { user ->
        userList.add(
            UserEntity(
                id = user.id,
                name = user.name,
                cpf = user.cpf.value,
                email = user.email.value,
                secret = user.secret,
                phone = user.phone.value,
                photo = user.photo?.value ?: "",
                profiles = arrayListOf(),
            )
        )
    }
    return userList
}

fun MutableList<SessionOrder>.toSessionOrderEntityList(): List<SessionOrderEntity> {
    val sessionOrderList = ArrayList<SessionOrderEntity>()

    forEach { sessionOder ->
        val userList = ArrayList<UserEntity>()

        sessionOder.users.forEach { user ->
            userList.add(
                UserEntity(
                    id = user.id,
                    name = user.name,
                    cpf = user.cpf.value,
                    email = user.email.value,
                    secret = user.secret,
                    phone = user.phone.value,
                    photo = user.photo?.value ?: "",
                    profiles = arrayListOf(),
                )
            )
        }
        val order = OrderEntity(
            null,
            name = sessionOder.order.name,
            description = sessionOder.order.description,
            value = sessionOder.order.value
        )

        sessionOrderList.add(SessionOrderEntity(null, order, userList))
    }

    return sessionOrderList
}

fun List<SessionEntity>.toSessionList(): List<Session>{
    val sessionList = ArrayList<Session>()

    forEach { session ->
        sessionList.add(session.toSession())
    }

    return sessionList
}