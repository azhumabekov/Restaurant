package java15.repository;

import java15.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("SELECT m FROM MenuItem m JOIN SubCategory sc ON sc.menuItem.id = m.id JOIN Category c ON c.id = sc.category.id WHERE c.id = :categoryId")
    List<MenuItem> findByCategoryId(@Param("categoryId") Long categoryId);
}
