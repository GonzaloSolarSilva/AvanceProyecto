package servicios;

import entidades.Cliente;
import estructuras.ListaEnlazada;
import java.util.Scanner;

public class GestorClientes {

    private ListaEnlazada listaClientes;

    public GestorClientes() {
        this.listaClientes = new ListaEnlazada();
        // Clientes precargados (opcional, para probar el sistema)
        listaClientes.insertarFinal(new Cliente("Ana Torres", "70123456", "987654321", "ana.torres@mail.com"));
        listaClientes.insertarFinal(new Cliente("Luis Pérez", "71234567", "987111222", "luis.perez@mail.com"));
    }

    public void menuClientes(Scanner sc) {
        int op;
        do {
            System.out.println("\n  --- Módulo de Clientes (Lista Enlazada) ---");
            System.out.println("  1. Registrar cliente (al final)");
            System.out.println("  2. Listar todos los clientes");
            System.out.println("  3. Buscar cliente por ID");
            System.out.println("  4. Buscar cliente por DNI");
            System.out.println("  5. Actualizar datos de un cliente");
            System.out.println("  6. Eliminar cliente");
            System.out.println("  7. Insertar cliente en una posición específica");
            System.out.println("  0. Volver al menú principal");
            System.out.print("  Opción: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1: {
                    System.out.print("  Nombre completo: ");
                    String nombre = sc.nextLine();
                    System.out.print("  DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("  Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("  Correo: ");
                    String correo = sc.nextLine();
                    Cliente nuevo = new Cliente(nombre, dni, telefono, correo);
                    listaClientes.insertarFinal(nuevo);
                    System.out.println("  Cliente registrado: " + nuevo);
                    break;
                }
                case 2:
                    System.out.println("\n  Clientes registrados:");
                    listaClientes.mostrar();
                    System.out.println("  Total: " + listaClientes.getTamanio());
                    break;
                case 3: {
                    System.out.print("  Ingrese el ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Cliente encontrado = listaClientes.buscarPorId(id);
                    if (encontrado != null) System.out.println("  Encontrado: " + encontrado);
                    else System.out.println("  No se encontró ningún cliente con ID " + id + ".");
                    break;
                }
                case 4: {
                    System.out.print("  Ingrese el DNI: ");
                    String dni = sc.nextLine();
                    Cliente encontrado = listaClientes.buscarPorDni(dni);
                    if (encontrado != null) System.out.println("  Encontrado: " + encontrado);
                    else System.out.println("  No se encontró ningún cliente con DNI " + dni + ".");
                    break;
                }
                case 5: {
                    System.out.print("  ID del cliente a actualizar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    if (listaClientes.buscarPorId(id) == null) {
                        System.out.println("  No se encontró ningún cliente con ID " + id + ".");
                        break;
                    }
                    System.out.print("  Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("  Nuevo teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("  Nuevo correo: ");
                    String correo = sc.nextLine();
                    listaClientes.actualizar(id, nombre, telefono, correo);
                    System.out.println("  Cliente actualizado correctamente.");
                    break;
                }
                case 6: {
                    System.out.print("  ID del cliente a eliminar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    boolean ok = listaClientes.eliminar(id);
                    if (ok) System.out.println("  Cliente eliminado correctamente.");
                    else System.out.println("  No se encontró ningún cliente con ID " + id + ".");
                    break;
                }
                case 7: {
                    System.out.print("  Nombre completo: ");
                    String nombre = sc.nextLine();
                    System.out.print("  DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("  Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("  Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("  Posición (0=inicio, " + listaClientes.getTamanio() + "=final): ");
                    int pos = sc.nextInt(); sc.nextLine();
                    Cliente nuevo = new Cliente(nombre, dni, telefono, correo);
                    boolean ok = listaClientes.insertarEnPosicion(nuevo, pos);
                    if (ok) System.out.println("  Cliente insertado en la posición " + pos + ".");
                    else System.out.println("  Posición inválida.");
                    break;
                }
                case 0:
                    System.out.println("  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("  Opción inválida.");
            }
        } while (op != 0);
    }
}
