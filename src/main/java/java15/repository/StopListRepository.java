package java15.repository;

import java15.models.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StopListRepository extends JpaRepository<StopList, Long> {
}
