package java15.repository;

import java15.models.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

        Page<Restaurant> findAll(Pageable pageable);

    boolean existsByName(String name);

    boolean existsByLocation(String location);
}
