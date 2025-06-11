package techlab.inicio.pedidos;

import techlab.inicio.clientes.Cliente;
import techlab.inicio.excepciones.StockInsuficienteException;
import techlab.inicio.productos.Producto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private static int contadorPedidos = 1;
    private final int id;
    private final Cliente cliente;
    private final LocalDate fecha;
    private final ArrayList<LineaPedido> lineas = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.id = contadorPedidos++;
        this.cliente = cliente;
        this.fecha = LocalDate.now();
    }

    //Añadir un producto y validar el stock en ese momento
    public void agregarProducto(Producto p, int cantidad)
        throws StockInsuficienteException {
        if (cantidad > p.getStock())
            throw new StockInsuficienteException("Stock insuficiente de " + p.getNombre());
        p.setStock(p.getStock() - cantidad);
        lineas.add(new LineaPedido(p, cantidad));
    }

    //Añadimos una linea de pedido ya creada usando PedidoService
    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double getTotal() {
        return lineas.stream().mapToDouble(LineaPedido::subtotal).sum();
    }

    public int  getId()      { return id; }
    public Cliente getCliente() { return cliente; }
    public ArrayList<LineaPedido> getLineas() { return lineas; }

     // Representación textual “desplegable”
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("Pedido #%d | Cliente: %s | Total: $%.2f | %s%n",
                        id, cliente.getNombre(), getTotal(), fecha));
        for (LineaPedido lp : lineas) {
            sb.append("   • ").append(lp).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
