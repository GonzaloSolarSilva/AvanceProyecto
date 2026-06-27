package estructuras;

import entidades.Ticket;

public class ColaConPrioridad {
    private Nodo<Ticket> frente;
    private Nodo<Ticket> fin;
    private int tamanio;

    public ColaConPrioridad() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public void enqueue(Ticket ticket) {
        Nodo<Ticket> nuevo = new Nodo<>(ticket);
        if (frente == null || ticket.getPrioridad() < frente.dato.getPrioridad()) {
            nuevo.siguiente = frente;
            frente = nuevo;
            if (fin == null) fin = nuevo;
        } else {
            Nodo<Ticket> actual = frente;
            while (actual.siguiente != null && actual.siguiente.dato.getPrioridad() <= ticket.getPrioridad())
                actual = actual.siguiente;
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
            if (nuevo.siguiente == null) fin = nuevo;
        }
        tamanio++;
    }

    public Ticket dequeue() {
        if (estaVacia()) { return null; }
        Ticket dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }

    public Ticket peek() {
        if (estaVacia()) return null;
        return frente.dato;
    }

    public Ticket buscarPorId(int id) {
        Nodo<Ticket> actual = frente;
        while (actual != null) {
            if (actual.dato.getId() == id) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean reencolar(int id) {
        if (estaVacia()) return false;
        Ticket encontrado = null;
        if (frente.dato.getId() == id) {
            encontrado = frente.dato;
            frente = frente.siguiente;
            if (frente == null) fin = null;
            tamanio--;
        } else {
            Nodo<Ticket> anterior = frente;
            while (anterior.siguiente != null && anterior.siguiente.dato.getId() != id)
                anterior = anterior.siguiente;
            if (anterior.siguiente == null) return false;
            encontrado = anterior.siguiente.dato;
            if (anterior.siguiente == fin) fin = anterior;
            anterior.siguiente = anterior.siguiente.siguiente;
            tamanio--;
        }
        enqueue(encontrado);
        return true;
    }

    public int[] contarPorPrioridad() {
        int[] conteo = new int[3];
        Nodo<Ticket> actual = frente;
        while (actual != null) {
            conteo[actual.dato.getPrioridad() - 1]++;
            actual = actual.siguiente;
        }
        return conteo;
    }

    public Ticket[] toArray() {
        Ticket[] arr = new Ticket[tamanio];
        Nodo<Ticket> actual = frente;
        for (int i = 0; i < tamanio; i++) {
            arr[i] = actual.dato;
            actual = actual.siguiente;
        }
        return arr;
    }

    public Nodo<Ticket> getFrente() { return frente; }
    public boolean estaVacia() { return frente == null; }
    public int getTamanio() { return tamanio; }

    public void mostrar() {
        if (estaVacia()) { System.out.println("(No hay tickets en espera)"); return; }
        Nodo<Ticket> actual = frente;
        int pos = 1;
        while (actual != null) {
            System.out.println("  " + pos + ". " + actual.dato);
            actual = actual.siguiente;
            pos++;
        }
    }
}