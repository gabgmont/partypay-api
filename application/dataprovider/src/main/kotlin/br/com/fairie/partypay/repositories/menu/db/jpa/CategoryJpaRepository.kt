package br.com.fairie.partypay.repositories.menu.db.jpa

import br.com.fairie.partypay.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository: JpaRepository<CategoryEntity, Long> {
}
