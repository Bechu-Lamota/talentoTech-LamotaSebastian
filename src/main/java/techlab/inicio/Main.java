package techlab.inicio;

import techlab.inicio.clientes.Cliente;
import techlab.inicio.excepciones.StockInsuficienteException;
import techlab.inicio.pedidos.LineaPedido;
import techlab.inicio.productos.Producto;
import techlab.inicio.pedidos.Pedido;
import techlab.inicio.servicios.PedidoService;
import techlab.inicio.servicios.ProductoService;
import techlab.inicio.utilidades.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // Dependencias - Capa SERVICIOS
    private static final ProductoService productoService = new ProductoService();
    private static final PedidoService pedidoService = new PedidoService();

    // SCANNER
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
        System.out.println("Sesion finalizada ¡Hasta luego!");
        sc.close();
    }
    // MENÚ PRINCIPAL
    private static void menuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("""
                    ========== MENÚ ==========
                    1. Agregar producto
                    2. Listar productos
                    3. Buscar / Actualizar producto
                    4. Eliminar producto
                    5. Crear pedido
                    6. Listar pedidos
                    0. Salir
                    """);
            switch (Helpers.leerEntero(sc,"Opción: ")) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 0 -> salir = true;
                default -> System.out.println("Opcion inválida");
                }
                System.out.println();
            }

        }

        //Operaciones sobre PEDIDOS
        private static void crearPedido() {
            String nombre = Helpers.leerTexto(sc, "Nombre del cliente: ");
            String email  = Helpers.leerTexto(sc, "Email del cliente: ");
            String telefono = Helpers.leerTexto(sc, "Telefono del cliente.");
            Cliente cliente = new Cliente(nombre, email, telefono);

        //2. Creamos lineas de pedido
            List<LineaPedido> lineas = new ArrayList<>();
            while (true) {
                listarProductos();
                int id = Helpers.leerEntero(sc,"ID producto");
                if (id == 0) break;
                Producto producto = productoService.buscarId(id).orElse(null);
                if (producto == null) {
                    System.out.println("ID incorrecto.");
                    continue;
                }
                int cantidad = Helpers.leerEntero(sc, "Cantidad");
                lineas.add(new LineaPedido(producto, cantidad));
            }
            if (lineas.isEmpty()) {
                System.out.println("Pedido cancelado - No posee productos.");
                return;
            }

            try {
                Pedido pedido = pedidoService.crearPedido(cliente, lineas);
                System.out.println("Pedido creado:\n" + pedido);
            } catch (StockInsuficienteException e) {
                System.out.println("Pedido error: " + e.getMessage());
            }
        }

        private static void listarPedidos() {
        if (pedidoService.listar().isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        pedidoService.listar().forEach(p -> {
            System.out.println(p);
            p.getLineas().forEach(lp -> System.out.println("   • " + lp));
        });
        }


    //Operaciones sobre PRODUCTOS
        private static void agregarProducto() {
            String nombre = Helpers.leerTexto(sc, "Nombre: ");
            double precio = Helpers.leerDouble(sc, "Precio: ");
            int    stock  = Helpers.leerEntero(sc, "Stock: ");
            productoService.agregarProducto(new Producto(nombre, precio, stock));
            System.out.println("Producto agregado.");
        }

    private static void listarProductos() {
        if (productoService.listar().isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }
        System.out.println("ID | Nombre | Precio | Stock");
        productoService.listar().forEach(System.out::println);
    }

    private static void buscarActualizarProducto() {
        System.out.print("Buscar por ID o Nombre: ");
        int unoOtro = Helpers.leerEntero(sc, "");
        Producto p = null;
        if (unoOtro == 1) {
            p = productoService.buscarId(Helpers.leerEntero(sc, "ID")).orElse(null);
        } else if (unoOtro == 2) {
            System.out.print("Nombre: ");
            p = productoService.buscarNombre(sc.nextLine()).orElse(null);
        }
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.println("Encontrado: " + p);
        System.out.print("¿Actualizar? (s/N) ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            p.setPrecio(Helpers.leerDouble(sc,"Nuevo precio"));
            p.setStock(Helpers.leerEntero(sc,"Nuevo stock"));
            System.out.println("Producto actualizado.");
        }
    }

    private static void eliminarProducto() {
        int id = Helpers.leerEntero(sc, "ID a eliminar");
        System.out.print("Confirmar eliminación (s/N): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            if (productoService.eliminar(id))
                System.out.println("Producto eliminado.");
            else
                System.out.println("Producto no encontrado.");
        }
    }
}