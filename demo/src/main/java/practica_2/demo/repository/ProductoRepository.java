package practica_2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practica_2.demo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
