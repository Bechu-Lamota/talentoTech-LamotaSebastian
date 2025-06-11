package techlab.inicio.pedidos;

import techlab.inicio.clientes.Cliente;
import techlab.inicio.productos.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private static int contadorPedidos = 1;

    private int id;
    private Cliente cliente;
    private ArrayList<Producto> productos;

    public Pedido(Cliente cliente) {
        this.id = contadorPedidos++;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    
}
