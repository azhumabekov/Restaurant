package java15.repository;

import java15.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
