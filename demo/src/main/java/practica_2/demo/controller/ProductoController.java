package practica_2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import practica_2.demo.model.Producto;
import practica_2.demo.service.ProductoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    @Autowired
    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // ✅ Listar todos los productos
    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public Optional<Producto> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // ✅ Crear producto
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.guardar(producto);
    }

    // ✅ Actualizar producto
    @PutMapping("/{id}")
    public Optional<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return service.actualizar(id, producto);
    }

    // ✅ Eliminar producto
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        return service.eliminar(id)
                ? "Producto eliminado correctamente"
                : "No se encontró el producto con ID " + id;
    }
}
