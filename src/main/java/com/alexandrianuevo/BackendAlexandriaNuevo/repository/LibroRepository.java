package  com.alexandrianuevo.BackendAlexandriaNuevo.repository;


import java.util.List;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(String titulo, String autor);

}
