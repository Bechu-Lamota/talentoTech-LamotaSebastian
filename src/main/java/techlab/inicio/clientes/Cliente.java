package techlab.inicio;

public class Cliente {

    private static int contador = 1;

    private final int id;
    private String nombre;
    private String email;
    private String telefono;

    public Cliente(String nombre, String email, String telefono) {
        this.id = contador++;
        setNombre(nombre); //Aprovecho la validación
        setEmail(email);
        setTelefono(telefono);
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
            this.nombre = nombre.trim();
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            if (!email.matches("^[\\w.%-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
                throw new IllegalArgumentException("Email inválido.");
            }
            this.email = email;
        }

        public String getTelefono() {
            return telefono;
        }
        public void setTelefono(String telefono) {
            if (!telefono.matches("\\d+")) {
                throw new IllegalArgumentException("Solo se aceptan números.");
            }
                this.telefono = telefono;
            }


            @Override
            public String toString() {
                return "ID:" + id + " | Cliente: " + nombre + " | Email: " + email + " | Teléfono: " + telefono;
            }
        }