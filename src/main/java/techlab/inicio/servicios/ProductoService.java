package techlab.inicio.servicios;

import techlab.inicio.productos.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoService {
    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) { productos.add(p); }

    public List<Producto> listar() { return productos; }

    public Optional<Producto> buscarId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Producto> buscarNombre(String nombre) {
        return productos.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    public boolean eliminar(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }
}
