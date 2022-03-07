package br.com.fairie.partypay.repositories.menu.db.jpa

import br.com.fairie.partypay.entity.MenuEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuJpaRepository : JpaRepository<MenuEntity, Long> {

    @Query("SELECT m FROM menu_tbl m JOIN FETCH m.categories WHERE m.id = (:menuId)")
    override fun getById(menuId: Long): MenuEntity

    @Query("SELECT NEW br.com.fairie.partypay.entity.MenuEntity(m.id, m.restaurant) FROM menu_tbl m")
    fun getRestaurants(): List<MenuEntity>
}
