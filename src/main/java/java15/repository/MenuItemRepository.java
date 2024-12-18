package java15.repository;

import java15.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
