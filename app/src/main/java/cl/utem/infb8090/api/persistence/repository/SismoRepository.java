package cl.utem.infb8090.api.persistence.repository;

import cl.utem.infb8090.api.persistence.model.Sismo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SismoRepository extends JpaRepository<Sismo, Long> {

    Sismo findByFecha(String fecha);
    boolean existsByFecha(String fecha);
    @Override
    List<Sismo> findAll();
}

