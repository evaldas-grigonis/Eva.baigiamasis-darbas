package Eva.Baigiamasis_darbas.repositories;

import Eva.Baigiamasis_darbas.entities.RegistrationFormsOfPersons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RegistrationFormsOfPersonsRepository extends JpaRepository<RegistrationFormsOfPersons, Long> {
}
