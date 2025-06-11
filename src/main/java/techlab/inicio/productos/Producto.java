package techlab.inicio;

public class Producto {

    private static int contador = 1; //Genera IDs únicos automáticamente

    private final int id; // ID único e inmutable
    private String nombre; // Nombre del producto
    private double precio; // Precio del producto
    private int stock; // Stock disponible

    public Producto(String nombre, double precio, int stock) {
        this.id = contador++; //El contador asigna un ID y lo incrementa para el próximo producto
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Setters - Getters
    public int getId() {
        return id;
    } //No hay Setter porque es inmutable = es final.
    public String getNombre() {
        return nombre;
    } //Devuelve el nombre del producto.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    } //Modifica el nombre del producto.
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | $ " + precio + " | Stock: " + stock;
    }
}
