package java15.repository;

import java15.models.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface ChequeRepository extends JpaRepository<Cheque, Long> {


}
