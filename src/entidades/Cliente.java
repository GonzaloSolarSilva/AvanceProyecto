package entidades;

public class Cliente {
    private static int contadorId = 1;

    private int id;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;

    public Cliente(String nombre, String dni, String telefono, String correo) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "[ID:" + id + "] " + nombre + " - DNI:" + dni
               + " | Tel:" + telefono + " | Correo:" + correo;
    }
}
