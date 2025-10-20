package practica_2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica_2.demo.model.Producto;
import practica_2.demo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    @Autowired
    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // Listar todos los productos
    public List<Producto> listar() {
        return repository.findAll();
    }

    // Buscar producto por ID
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Guardar un nuevo producto
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    // Actualizar un producto existente
    public Optional<Producto> actualizar(Long id, Producto nuevo) {
        return repository.findById(id).map(p -> {
            p.setNombre(nuevo.getNombre());
            p.setPrecio(nuevo.getPrecio());
            p.setCantidad(nuevo.getCantidad());
            return repository.save(p);
        });
    }

    // Eliminar un producto
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
