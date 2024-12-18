package java15.repository;

import java15.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
