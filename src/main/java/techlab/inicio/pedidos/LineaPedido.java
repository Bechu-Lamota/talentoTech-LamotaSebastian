package techlab.inicio.pedidos;

import techlab.inicio.productos.Producto;

public class LineaPedido {
    private final Producto producto;
    private final int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double subtotal() {
        return producto.getPrecio() * cantidad;
    }

    public Producto getProducto() { return  producto; }
    public int getCantidad() { return  cantidad; }

    @Override
    public String toString() {
        return producto.getNombre() + " x " + cantidad + " = $" + String.format("%.2f", subtotal());
        // Imprimimos 2 decimales
    }
}
