package servicios;

import entidades.Ticket;
import estructuras.ColaConPrioridad;
import estructuras.Pila;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class GestorTickets {

    private ColaConPrioridad colaTickets;
    private Pila<String>     historial;
    private Pila<Ticket>     enProceso;

    public GestorTickets() {
        this.colaTickets = new ColaConPrioridad();
        this.historial   = new Pila<>();
        this.enProceso   = new Pila<>();
    }

    // ── 1. Registrar ticket ─────────────────────────────────────────────────
    public void registrarTicket() {
        String cliente = JOptionPane.showInputDialog(null, "Nombre del cliente:", "Registrar Ticket", JOptionPane.PLAIN_MESSAGE);
        if (cliente == null || cliente.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog(null, "Descripcion del problema:", "Registrar Ticket", JOptionPane.PLAIN_MESSAGE);
        if (descripcion == null || descripcion.trim().isEmpty()) return;

        String[] opciones = {"1 - Alta", "2 - Media", "3 - Baja"};
        int sel = JOptionPane.showOptionDialog(null, "Seleccione la prioridad:", "Prioridad",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (sel == JOptionPane.CLOSED_OPTION) return;
        int prioridad = sel + 1;

        Ticket nuevo = new Ticket(cliente.trim(), descripcion.trim(), prioridad);
        colaTickets.enqueue(nuevo);
        historial.push("REGISTRO - " + nuevo);
        JOptionPane.showMessageDialog(null, "Ticket registrado:\n" + nuevo, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── 2. Atender ticket ───────────────────────────────────────────────────
    public void atenderTicket() {
        Ticket t = colaTickets.dequeue();
        if (t != null) {
            t.setEstado("ATENDIDO");
            historial.push("ATENDIDO - " + t);
            JOptionPane.showMessageDialog(null, "Ticket atendido:\n" + t, "Atender Ticket", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay tickets en la cola.", "Cola Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ── 3. Peek ─────────────────────────────────────────────────────────────
    public void verFrente() {
        Ticket t = colaTickets.peek();
        if (t != null) {
            JOptionPane.showMessageDialog(null, "Proximo ticket a atender:\n" + t, "Ver Frente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay tickets en la cola.", "Cola Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ── 4. Ver cola ─────────────────────────────────────────────────────────
    public void verCola() {
        if (colaTickets.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay tickets en espera.", "Cola Vacia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Tickets en espera (por prioridad):\n\n");
        Ticket[] lista = colaTickets.toArray();
        for (int i = 0; i < lista.length; i++) {
            sb.append(i + 1).append(". ").append(lista[i]).append("\n");
        }
        sb.append("\nTotal: ").append(colaTickets.getTamanio());
        JOptionPane.showMessageDialog(null, sb.toString(), "Tickets en Espera", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── 5. Buscar por ID ────────────────────────────────────────────────────
    public void buscarTicket() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el ID del ticket:", "Buscar Ticket", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        try {
            int id = Integer.parseInt(input.trim());
            Ticket t = colaTickets.buscarPorId(id);
            if (t != null) {
                JOptionPane.showMessageDialog(null, "Ticket encontrado:\n" + t, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ningun ticket con ID " + id, "No Encontrado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── 6. Reencolar ────────────────────────────────────────────────────────
    public void reencolarTicket() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el ID del ticket a reencolar:", "Reencolar Ticket", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        try {
            int id = Integer.parseInt(input.trim());
            boolean ok = colaTickets.reencolar(id);
            if (ok) {
                historial.push("REENCOLADO - Ticket ID:" + id);
                JOptionPane.showMessageDialog(null, "Ticket ID:" + id + " reencolado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el ticket con ID " + id, "No Encontrado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── 7. Ver historial ────────────────────────────────────────────────────
    public void verHistorial() {
        if (historial.estaVacia()) {
            JOptionPane.showMessageDialog(null, "El historial esta vacio.", "Historial", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Historial de acciones (reciente primero):\n\n");
        int total = historial.getTamanio();
        String[] entradas = new String[total];
        for (int i = 0; i < total; i++) entradas[i] = historial.pop();
        for (int i = 0; i < total; i++) {
            sb.append(i + 1).append(". ").append(entradas[i]).append("\n");
            historial.push(entradas[total - 1 - i]);
        }
        sb.append("\nTotal: ").append(total);
        JOptionPane.showMessageDialog(null, sb.toString(), "Historial", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── 8. Deshacer ─────────────────────────────────────────────────────────
    public void deshacerAccion() {
        String ultima = historial.pop();
        if (ultima != null) {
            JOptionPane.showMessageDialog(null, "Accion eliminada del historial:\n" + ultima, "Deshacer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "El historial esta vacio.", "Historial Vacio", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ── 9. Marcar en proceso ────────────────────────────────────────────────
    public void marcarEnProceso() {
        Ticket t = colaTickets.dequeue();
        if (t != null) {
            t.setEstado("EN PROCESO");
            enProceso.push(t);
            historial.push("EN PROCESO - " + t);
            JOptionPane.showMessageDialog(null, "Ticket marcado en proceso:\n" + t +
                "\nTotal en proceso: " + enProceso.getTamanio(), "En Proceso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay tickets en la cola.", "Cola Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ── 10. Ver en proceso ──────────────────────────────────────────────────
    public void verEnProceso() {
        if (enProceso.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay tickets en proceso.", "En Proceso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Tickets en proceso:\n\n");
        int total = enProceso.getTamanio();
        Ticket[] arr = new Ticket[total];
        for (int i = 0; i < total; i++) arr[i] = enProceso.pop();
        for (int i = 0; i < total; i++) {
            sb.append(i + 1).append(". ").append(arr[i]).append("\n");
            enProceso.push(arr[total - 1 - i]);
        }
        sb.append("\nTotal: ").append(total);
        JOptionPane.showMessageDialog(null, sb.toString(), "En Proceso", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── 11. Estadísticas ────────────────────────────────────────────────────
    public void verEstadisticas() {
        int[] c = colaTickets.contarPorPrioridad();
        String msg =
            "=== Estadisticas del Sistema ===\n\n" +
            "Tickets en espera     : " + colaTickets.getTamanio() + "\n" +
            "  - Prioridad ALTA    : " + c[0] + "\n" +
            "  - Prioridad MEDIA   : " + c[1] + "\n" +
            "  - Prioridad BAJA    : " + c[2] + "\n\n" +
            "Tickets en proceso    : " + enProceso.getTamanio() + "\n" +
            "Acciones en historial : " + historial.getTamanio();
        JOptionPane.showMessageDialog(null, msg, "Estadisticas", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── 12. Exportar historial ──────────────────────────────────────────────
    public void exportarHistorial() {
        if (historial.estaVacia()) {
            JOptionPane.showMessageDialog(null, "El historial esta vacio.", "Exportar", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String archivo = "historial_tickets.txt";
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write("=== HISTORIAL DE ACCIONES - TECHSUPPORT S.A.C. ===\n\n");
            int total = historial.getTamanio();
            String[] entradas = new String[total];
            for (int i = 0; i < total; i++) entradas[i] = historial.pop();
            for (int i = total - 1; i >= 0; i--) fw.write((total - i) + ". " + entradas[i] + "\n");
            for (int i = total - 1; i >= 0; i--) historial.push(entradas[i]);
            fw.write("\nTotal de acciones: " + historial.getTamanio());
            JOptionPane.showMessageDialog(null, "Historial exportado correctamente:\n" + archivo, "Exportar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}