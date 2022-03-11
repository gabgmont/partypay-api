package br.com.fairie.partypay.repositories.session.mapper

import br.com.fairie.partypay.entity.SessionEntity
import br.com.fairie.partypay.entity.SessionOrderEntity
import br.com.fairie.partypay.entity.UserEntity
import br.com.fairie.partypay.entity.UserEntity.Companion.toEntity
import br.com.fairie.partypay.repositories.menu.db.mapper.toEntity
import br.com.fairie.partypay.repositories.menu.db.mapper.toModel
import br.com.fairie.partypay.usecase.session.model.Session
import br.com.fairie.partypay.usecase.session.model.SessionOrder
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

fun SessionEntity.toModel(): Session = Session(
        id = id,
        restaurant = restaurant,
        menuId = menuId,
        table = counter,
        status = status,
        users = users.map { userEntity -> userEntity.toModel() }.toMutableList(),
        orders = orders.map { sessionOrderEntity -> sessionOrderEntity.toModel() }.toMutableList()
)

fun SessionOrder.toEntity(): SessionOrderEntity = SessionOrderEntity(
        id = id,
        order = order.toEntity(),
        status = status,
        users = users.map { user -> user.toEntity() }

)

fun UserEntity.toModel(): User = User(
        id = id,
        name = name,
        username = usernm,
        email = Email(email),
        secret = secret,
        phone = Phone(phone),
        photo = Photo(photo ?: ""),
        profiles = arrayListOf(),
)

fun SessionOrderEntity.toModel(): SessionOrder = SessionOrder(
        id = id,
        order = order.toModel(),
        status = status,
        users = users.map { user ->
            user.toModel()
        }
)

fun Session.toEntity(): SessionEntity = SessionEntity(
        id = id,
        restaurant = restaurant,
        menuId = menuId,
        counter = table,
        status = status,
        users = users.map { user -> user.toEntity() },
        orders = orders.map { order -> order.toEntity() }.toMutableList()
)

fun List<SessionEntity>.toModelList(): List<Session> = map { sessionEntity -> sessionEntity.toModel() }
