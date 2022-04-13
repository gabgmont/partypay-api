package br.com.fairie.partypay.repositories.menu.db.repository

import br.com.fairie.partypay.entity.CategoryEntity
import br.com.fairie.partypay.entity.MenuEntity
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.repositories.menu.db.jpa.CategoryJpaRepository
import br.com.fairie.partypay.repositories.menu.db.jpa.MenuJpaRepository
import br.com.fairie.partypay.repositories.menu.db.jpa.OrderJpaRepository
import br.com.fairie.partypay.repositories.menu.db.mapper.toEntity
import br.com.fairie.partypay.repositories.menu.db.mapper.toModel
import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuRepositoryImpl : MenuRepository {

    @Autowired
    private lateinit var menuJpaRepository: MenuJpaRepository

    @Autowired
    private lateinit var categoryJpaRepository: CategoryJpaRepository

    @Autowired
    private lateinit var orderJpaRepository: OrderJpaRepository

    override fun saveMenu(menu: Menu) {
        val menuEntity = menu.toEntity()
        menuEntity.categories.forEach { categoryEntity ->
            categoryEntity.orders.forEach { order ->
                orderJpaRepository.save(order)
            }
            categoryJpaRepository.save(categoryEntity)
        }
        menuJpaRepository.save(menuEntity)
    }

    override fun getRestaurants(): List<Menu> {
        try {
            val menus = menuJpaRepository.getRestaurants()
            return menus.map { menu -> menu.toModel() }

        } catch (exception: Exception) {
            throw NotFoundException("No restaurants found.")
        }
    }

    override fun getMenuById(menuId: Long): Menu {
        try {
            val menu = menuJpaRepository.getById(menuId)
            return menu.toModel()

        } catch (exception: Exception) {
            throw NotFoundException("Menu with id $menuId not found.")
        }
    }

    override fun getCategoryById(categoryId: Long): Category {
        try {
            val category = categoryJpaRepository.getById(categoryId)
            return category.toModel()

        } catch (exception: Exception) {
            throw NotFoundException("Category $categoryId not found.")
        }
    }

    override fun getOrderById(orderId: Long): Order {
        try {
            val orderEntity = orderJpaRepository.getById(orderId)
            return orderEntity.toModel()

        } catch (exception: Exception) {
            throw NotFoundException("Order $orderId not found.")
        }
    }

    override fun saveOrder(order: Order) {
        val entity = order.toEntity()
        orderJpaRepository.save(entity)
    }
}

fun MenuEntity.toModel(): Menu =
        Menu(
                id = id,
                name = restaurant,
                image = image,
                categoryList = categories.map { category -> category.toModel() }
        )

fun CategoryEntity.toModel(): Category =
        Category(
                id = id,
                name = name,
                orderList = orders.map { order -> order.toModel() }
        )

fun Menu.toEntity(): MenuEntity =
        MenuEntity(
                id = 0,
                restaurant = name,
                image = image,
                categories = categoryList.map { category -> category.toEntity() }
        )

fun Category.toEntity(): CategoryEntity =
        CategoryEntity(
                id = 0,
                name = name,
                orders = orderList.map { order -> order.toEntity() }
        )
