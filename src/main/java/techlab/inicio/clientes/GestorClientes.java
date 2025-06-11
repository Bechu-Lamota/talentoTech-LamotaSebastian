package techlab.inicio;

import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    private List<Cliente> listaClientes = new ArrayList<>();

    //Este metodo agrega un cliente verificando que no este duplicado.
    public boolean agregarCliente(Cliente nuevoCliente) {
        if (existeEmail(nuevoCliente.getEmail())) {
            System.out.println("Error: Ya existe un cliente con ese email.");
            return false;
        }
        listaClientes.add(nuevoCliente);
        return true;
    }

    // Este metodo verifica si ya existe un cliente con este mail
    public boolean existeEmail(String email) {
        for (Cliente c:listaClientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Este metodo mapea el listado por ID
    public Cliente buscarId(int id) {
        for (Cliente c:listaClientes) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    // Este metodo me brinda la lista completa de clientes
    public List<Cliente> getTodos() {
        return listaClientes;
    }
}
