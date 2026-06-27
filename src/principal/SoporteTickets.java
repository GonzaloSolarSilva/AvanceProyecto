package principal;

import servicios.GestorTickets;
import servicios.GestorCategorias;
import servicios.GestorClientes;
import javax.swing.JOptionPane;

public class SoporteTickets {

    public static void main(String[] args) {
        GestorTickets    gestorTickets    = new GestorTickets();
        GestorCategorias gestorCategorias = new GestorCategorias();
        GestorClientes   gestorClientes   = new GestorClientes();

        String menu =
            "=== SISTEMA DE TICKETS - TechSupport S.A.C. ===\n\n" +
            "[COLA CON PRIORIDAD]\n" +
            "  1.  Registrar nuevo ticket\n" +
            "  2.  Atender siguiente ticket (dequeue)\n" +
            "  3.  Ver ticket al frente (peek)\n" +
            "  4.  Ver todos los tickets en espera\n" +
            "  5.  Buscar ticket por ID\n" +
            "  6.  Reencolar ticket\n\n" +
            "[PILA - HISTORIAL]\n" +
            "  7.  Ver historial de acciones\n" +
            "  8.  Deshacer ultima accion (pop)\n\n" +
            "[PILA - EN PROCESO]\n" +
            "  9.  Marcar ticket como En Proceso\n" +
            "  10. Ver tickets en proceso\n\n" +
            "[REPORTES]\n" +
            "  11. Ver estadisticas\n" +
            "  12. Exportar historial a .txt\n\n" +
            "[ARBOL BINARIO DE BUSQUEDA]\n" +
            "  13. Gestionar categorias\n\n" +
            "[LISTA ENLAZADA]\n" +
            "  14. Gestionar clientes\n\n" +
            "  0.  Salir";

        int opcion = -1;

        do {
            String input = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.PLAIN_MESSAGE);

            if (input == null) break; // presionó Cancelar o cerró la ventana

            try {
                opcion = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcion) {
                case 1:  gestorTickets.registrarTicket();    break;
                case 2:  gestorTickets.atenderTicket();      break;
                case 3:  gestorTickets.verFrente();          break;
                case 4:  gestorTickets.verCola();            break;
                case 5:  gestorTickets.buscarTicket();       break;
                case 6:  gestorTickets.reencolarTicket();    break;
                case 7:  gestorTickets.verHistorial();       break;
                case 8:  gestorTickets.deshacerAccion();     break;
                case 9:  gestorTickets.marcarEnProceso();    break;
                case 10: gestorTickets.verEnProceso();       break;
                case 11: gestorTickets.verEstadisticas();    break;
                case 12: gestorTickets.exportarHistorial();  break;
                case 13: gestorCategorias.menuCategorias();  break;
                case 14: gestorClientes.menuClientes();      break;
                case 0:  JOptionPane.showMessageDialog(null, "Cerrando sistema. Hasta luego!", "Salir", JOptionPane.INFORMATION_MESSAGE); break;
                default: JOptionPane.showMessageDialog(null, "Opcion invalida.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } while (opcion != 0);

        System.exit(0);
    }
}