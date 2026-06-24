package estructuras;

import entidades.Cliente;

public class ListaEnlazada {
    private Nodo<Cliente> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    // Inserta un cliente al inicio de la lista
    public void insertarInicio(Cliente cliente) {
        Nodo<Cliente> nuevo = new Nodo<>(cliente);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamanio++;
    }

    // Inserta un cliente al final de la lista
    public void insertarFinal(Cliente cliente) {
        Nodo<Cliente> nuevo = new Nodo<>(cliente);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<Cliente> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    // Inserta un cliente en una posición específica (0 = inicio)
    public boolean insertarEnPosicion(Cliente cliente, int posicion) {
        if (posicion < 0 || posicion > tamanio) return false;
        if (posicion == 0) { insertarInicio(cliente); return true; }
        if (posicion == tamanio) { insertarFinal(cliente); return true; }

        Nodo<Cliente> nuevo = new Nodo<>(cliente);
        Nodo<Cliente> anterior = cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.siguiente;
        }
        nuevo.siguiente = anterior.siguiente;
        anterior.siguiente = nuevo;
        tamanio++;
        return true;
    }

    // Busca un cliente por su ID — recorre la lista enlazada
    public Cliente buscarPorId(int id) {
        Nodo<Cliente> actual = cabeza;
        while (actual != null) {
            if (actual.dato.getId() == id) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    // Busca un cliente por su DNI — recorre la lista enlazada
    public Cliente buscarPorDni(String dni) {
        Nodo<Cliente> actual = cabeza;
        while (actual != null) {
            if (actual.dato.getDni().equalsIgnoreCase(dni)) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    // Elimina un cliente por ID. Retorna true si lo encontró y eliminó.
    public boolean eliminar(int id) {
        if (cabeza == null) return false;

        if (cabeza.dato.getId() == id) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }

        Nodo<Cliente> anterior = cabeza;
        while (anterior.siguiente != null && anterior.siguiente.dato.getId() != id) {
            anterior = anterior.siguiente;
        }
        if (anterior.siguiente == null) return false; // no se encontró

        anterior.siguiente = anterior.siguiente.siguiente;
        tamanio--;
        return true;
    }

    // Actualiza los datos editables de un cliente (nombre, teléfono, correo)
    public boolean actualizar(int id, String nuevoNombre, String nuevoTelefono, String nuevoCorreo) {
        Cliente c = buscarPorId(id);
        if (c == null) return false;
        c.setNombre(nuevoNombre);
        c.setTelefono(nuevoTelefono);
        c.setCorreo(nuevoCorreo);
        return true;
    }

    public boolean estaVacia() { return cabeza == null; }
    public int getTamanio() { return tamanio; }

    // Muestra todos los clientes en orden (inicio → fin)
    public void mostrar() {
        if (estaVacia()) { System.out.println("  (No hay clientes registrados)"); return; }
        Nodo<Cliente> actual = cabeza;
        int pos = 1;
        while (actual != null) {
            System.out.println("  " + pos + ". " + actual.dato);
            actual = actual.siguiente;
            pos++;
        }
    }
}
