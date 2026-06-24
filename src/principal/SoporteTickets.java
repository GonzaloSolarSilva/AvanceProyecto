package principal;

import servicios.GestorTickets;
import servicios.GestorCategorias;
import servicios.GestorClientes;
import java.util.Scanner;

public class SoporteTickets {

    public static void main(String[] args) {
        GestorTickets    gestorTickets    = new GestorTickets();
        GestorCategorias gestorCategorias = new GestorCategorias();
        GestorClientes   gestorClientes   = new GestorClientes();
        Scanner sc = new Scanner(System.in);
        int opcion;

System.out.println("----------------------------------------");
        System.out.println("   SISTEMA DE GESTION DE TICKETS DE SOPORTE         ");
        System.out.println("   TechSupport S.A.C. - Algoritmos y EDA - UTP    ");
        System.out.println("----------------------------------------");

        do {
            System.out.println("\n-------------- MENU PRINCIPAL --------------");
            System.out.println(" [COLA CON PRIORIDAD]");
            System.out.println("  1.  Registrar nuevo ticket");
            System.out.println("  2.  Atender siguiente ticket (dequeue)");
            System.out.println("  3.  Ver ticket al frente (peek)");
            System.out.println("  4.  Ver todos los tickets en espera");
            System.out.println("  5.  Buscar ticket por ID");
            System.out.println("  6.  Reencolar ticket");
            System.out.println("--------------------------------------------------------");
            System.out.println(" [PILA - HISTORIAL]");
            System.out.println("  7.  Ver historial de acciones");
            System.out.println("  8.  Deshacer ultima accion (pop)");
            System.out.println("--------------------------------------------------------");
            System.out.println(" [PILA - EN PROCESO]");
            System.out.println("  9.  Marcar ticket como En Proceso");
            System.out.println("  10. Ver tickets en proceso");
            System.out.println("--------------------------------------------------------");
            System.out.println(" [REPORTES]");
            System.out.println("  11. Ver estadisticas de la cola");
            System.out.println("  12. Exportar historial a .txt");
            System.out.println("--------------------------------------------------------");
            System.out.println(" [ARBOL BINARIO DE BUSQUEDA]");
            System.out.println("  13. Gestionar categorias de incidencias");
            System.out.println("--------------------------------------------------------");
            System.out.println(" [LISTA ENLAZADA]");
            System.out.println("  14. Gestionar clientes registrados");
            System.out.println("--------------------------------------------------------");
            System.out.println("  0.  Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:  gestorTickets.registrarTicket(sc);  break;
                case 2:  gestorTickets.atenderTicket();      break;
                case 3:  gestorTickets.verFrente();          break;
                case 4:  gestorTickets.verCola();            break;
                case 5:  gestorTickets.buscarTicket(sc);     break;
                case 6:  gestorTickets.reencolarTicket(sc);  break;
                case 7:  gestorTickets.verHistorial();       break;
                case 8:  gestorTickets.deshacerAccion();     break;
                case 9:  gestorTickets.marcarEnProceso();    break;
                case 10: gestorTickets.verEnProceso();       break;
                case 11: gestorTickets.verEstadisticas();    break;
                case 12: gestorTickets.exportarHistorial();  break;
                case 13: gestorCategorias.menuCategorias(sc); break;
                case 14: gestorClientes.menuClientes(sc);     break;
                case 0:  System.out.println("\nCerrando sistema. Hasta luego!"); break;
                default: System.out.println("Opcion invalida. Intente nuevamente.");
            }

        } while (opcion != 0);

        sc.close();
    }
}