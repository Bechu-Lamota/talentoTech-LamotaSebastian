package techlab.inicio.servicios;

import techlab.inicio.clientes.Cliente;
import techlab.inicio.excepciones.StockInsuficienteException;
import techlab.inicio.pedidos.LineaPedido;
import techlab.inicio.pedidos.Pedido;
import techlab.inicio.productos.Producto;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final List<Pedido> pedidos = new ArrayList<>();

    //Crear el Pedido, valida stock, descuenta y lo agrega al historial
    public Pedido crearPedido(Cliente cliente, List<LineaPedido> lineas)
            throws StockInsuficienteException {

        //1. Validamos que haya stock primero.
        for (LineaPedido lp : lineas) {
            Producto p = lp.getProducto();
            if (lp.getCantidad() > p.getStock()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente de " + p.getNombre());
            }
        }

        //2. Descontamos el stock (al estar validado)
        for (LineaPedido lp : lineas) {
            Producto p = lp.getProducto();
            p.setStock(p.getStock() - lp.getCantidad());
        }

        //3. Registramos lineas y pedido
        Pedido pedido = new Pedido(cliente);
        lineas.forEach(pedido::agregarLinea);
        pedidos.add(pedido);
        return pedido;
    }

    //Listamos los pedidos registrados
    public List<Pedido> listar() {
        return pedidos;
    }
}
