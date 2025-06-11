package techlab.inicio.productos;

import java.time.LocalDate;

// POO -> Herencia de Producto
public class MueblesLimitados extends Producto {
    private LocalDate fechaVencimiento;

    public MueblesLimitados(String nombre, double precio, int stock, LocalDate fechaVencimiento) {
        super(nombre, precio, stock);
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }

    @Override
    public String toString() {
        return super.toString() + " | Vencimiento: " + fechaVencimiento;
    }
}