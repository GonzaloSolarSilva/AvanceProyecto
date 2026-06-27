package servicios;

import entidades.Cliente;
import estructuras.ListaEnlazada;
import javax.swing.JOptionPane;

public class GestorClientes {

    private ListaEnlazada listaClientes;

    public GestorClientes() {
        this.listaClientes = new ListaEnlazada();
        listaClientes.insertarFinal(new Cliente("Ana Torres", "70123456", "987654321", "ana.torres@mail.com"));
        listaClientes.insertarFinal(new Cliente("Luis Perez", "71234567", "987111222", "luis.perez@mail.com"));
    }

    public void menuClientes() {
        String[] opciones = {
            "1. Registrar cliente",
            "2. Listar todos los clientes",
            "3. Buscar cliente por ID",
            "4. Buscar cliente por DNI",
            "5. Actualizar datos de un cliente",
            "6. Eliminar cliente",
            "7. Insertar cliente en posicion especifica",
            "0. Volver al menu principal"
        };

        while (true) {
            int sel = JOptionPane.showOptionDialog(null, "Modulo de Clientes (Lista Enlazada):", "Clientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (sel == JOptionPane.CLOSED_OPTION || sel == 7) break;

            switch (sel) {
                case 0: {
                    String nombre = JOptionPane.showInputDialog(null, "Nombre completo:", "Registrar Cliente", JOptionPane.PLAIN_MESSAGE);
                    if (nombre == null) break;
                    String dni = JOptionPane.showInputDialog(null, "DNI:", "Registrar Cliente", JOptionPane.PLAIN_MESSAGE);
                    if (dni == null) break;
                    String telefono = JOptionPane.showInputDialog(null, "Telefono:", "Registrar Cliente", JOptionPane.PLAIN_MESSAGE);
                    if (telefono == null) break;
                    String correo = JOptionPane.showInputDialog(null, "Correo:", "Registrar Cliente", JOptionPane.PLAIN_MESSAGE);
                    if (correo == null) break;
                    Cliente nuevo = new Cliente(nombre.trim(), dni.trim(), telefono.trim(), correo.trim());
                    listaClientes.insertarFinal(nuevo);
                    JOptionPane.showMessageDialog(null, "Cliente registrado:\n" + nuevo, "Exito", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case 1: {
                    if (listaClientes.estaVacia()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes registrados.", "Lista Vacia", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Clientes registrados:\n\n" + listaClientes.toTexto() +
                        "\nTotal: " + listaClientes.getTamanio(), "Clientes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case 2: {
                    String input = JOptionPane.showInputDialog(null, "Ingrese el ID:", "Buscar por ID", JOptionPane.PLAIN_MESSAGE);
                    if (input == null) break;
                    try {
                        int id = Integer.parseInt(input.trim());
                        Cliente c = listaClientes.buscarPorId(id);
                        if (c != null) JOptionPane.showMessageDialog(null, "Encontrado:\n" + c, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(null, "No se encontro cliente con ID " + id, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 3: {
                    String dni = JOptionPane.showInputDialog(null, "Ingrese el DNI:", "Buscar por DNI", JOptionPane.PLAIN_MESSAGE);
                    if (dni == null) break;
                    Cliente c = listaClientes.buscarPorDni(dni.trim());
                    if (c != null) JOptionPane.showMessageDialog(null, "Encontrado:\n" + c, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null, "No se encontro cliente con DNI " + dni, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                    break;
                }
                case 4: {
                    String inputId = JOptionPane.showInputDialog(null, "ID del cliente a actualizar:", "Actualizar Cliente", JOptionPane.PLAIN_MESSAGE);
                    if (inputId == null) break;
                    try {
                        int id = Integer.parseInt(inputId.trim());
                        if (listaClientes.buscarPorId(id) == null) {
                            JOptionPane.showMessageDialog(null, "No se encontro cliente con ID " + id, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        String nombre = JOptionPane.showInputDialog(null, "Nuevo nombre:", "Actualizar Cliente", JOptionPane.PLAIN_MESSAGE);
                        if (nombre == null) break;
                        String telefono = JOptionPane.showInputDialog(null, "Nuevo telefono:", "Actualizar Cliente", JOptionPane.PLAIN_MESSAGE);
                        if (telefono == null) break;
                        String correo = JOptionPane.showInputDialog(null, "Nuevo correo:", "Actualizar Cliente", JOptionPane.PLAIN_MESSAGE);
                        if (correo == null) break;
                        listaClientes.actualizar(id, nombre.trim(), telefono.trim(), correo.trim());
                        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 5: {
                    String inputId = JOptionPane.showInputDialog(null, "ID del cliente a eliminar:", "Eliminar Cliente", JOptionPane.PLAIN_MESSAGE);
                    if (inputId == null) break;
                    try {
                        int id = Integer.parseInt(inputId.trim());
                        boolean ok = listaClientes.eliminar(id);
                        if (ok) JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(null, "No se encontro cliente con ID " + id, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 6: {
                    String nombre = JOptionPane.showInputDialog(null, "Nombre completo:", "Insertar en Posicion", JOptionPane.PLAIN_MESSAGE);
                    if (nombre == null) break;
                    String dni = JOptionPane.showInputDialog(null, "DNI:", "Insertar en Posicion", JOptionPane.PLAIN_MESSAGE);
                    if (dni == null) break;
                    String telefono = JOptionPane.showInputDialog(null, "Telefono:", "Insertar en Posicion", JOptionPane.PLAIN_MESSAGE);
                    if (telefono == null) break;
                    String correo = JOptionPane.showInputDialog(null, "Correo:", "Insertar en Posicion", JOptionPane.PLAIN_MESSAGE);
                    if (correo == null) break;
                    String inputPos = JOptionPane.showInputDialog(null, "Posicion (0=inicio, " + listaClientes.getTamanio() + "=final):", "Insertar en Posicion", JOptionPane.PLAIN_MESSAGE);
                    if (inputPos == null) break;
                    try {
                        int pos = Integer.parseInt(inputPos.trim());
                        Cliente nuevo = new Cliente(nombre.trim(), dni.trim(), telefono.trim(), correo.trim());
                        boolean ok = listaClientes.insertarEnPosicion(nuevo, pos);
                        if (ok) JOptionPane.showMessageDialog(null, "Cliente insertado en posicion " + pos, "Exito", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(null, "Posicion invalida.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "La posicion debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }
    }
}