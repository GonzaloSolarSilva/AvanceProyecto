package estructuras;

public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public void insertar(int codigo, String nombre) {
        raiz = insertarRec(raiz, codigo, nombre);
    }

    private NodoArbol insertarRec(NodoArbol nodo, int codigo, String nombre) {
        if (nodo == null) return new NodoArbol(codigo, nombre);
        if (codigo < nodo.codigo)
            nodo.izquierdo = insertarRec(nodo.izquierdo, codigo, nombre);
        else if (codigo > nodo.codigo)
            nodo.derecho = insertarRec(nodo.derecho, codigo, nombre);
        else
            System.out.println("Aviso: El codigo " + codigo + " ya existe.");
        return nodo;
    }

    public NodoArbol buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    private NodoArbol buscarRec(NodoArbol nodo, int codigo) {
        if (nodo == null) return null;
        if (codigo == nodo.codigo) return nodo;
        if (codigo < nodo.codigo) return buscarRec(nodo.izquierdo, codigo);
        return buscarRec(nodo.derecho, codigo);
    }

    public void eliminar(int codigo) {
        if (buscar(codigo) == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo " + codigo + " no existe en el arbol.", "No Encontrado", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        raiz = eliminarRec(raiz, codigo);
        javax.swing.JOptionPane.showMessageDialog(null, "Categoria eliminada correctamente.", "Exito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private NodoArbol eliminarRec(NodoArbol nodo, int codigo) {
        if (nodo == null) return null;
        if (codigo < nodo.codigo) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, codigo);
        } else if (codigo > nodo.codigo) {
            nodo.derecho = eliminarRec(nodo.derecho, codigo);
        } else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;
            NodoArbol minDerecho = minimoNodo(nodo.derecho);
            nodo.codigo = minDerecho.codigo;
            nodo.nombreCategoria = minDerecho.nombreCategoria;
            nodo.derecho = eliminarRec(nodo.derecho, minDerecho.codigo);
        }
        return nodo;
    }

    private NodoArbol minimoNodo(NodoArbol nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public void inorder() {
        if (raiz == null) { System.out.println("(El arbol esta vacio)"); return; }
        inorderRec(raiz);
    }

    private void inorderRec(NodoArbol nodo) {
        if (nodo == null) return;
        inorderRec(nodo.izquierdo);
        System.out.println(nodo);
        inorderRec(nodo.derecho);
    }

    public void preorder() {
        if (raiz == null) { System.out.println("(El arbol esta vacio)"); return; }
        preorderRec(raiz);
    }

    private void preorderRec(NodoArbol nodo) {
        if (nodo == null) return;
        System.out.println(nodo);
        preorderRec(nodo.izquierdo);
        preorderRec(nodo.derecho);
    }

    public String inorderTexto() {
        StringBuilder sb = new StringBuilder();
        inorderTextoRec(raiz, sb);
        if (sb.length() == 0) sb.append("(El arbol esta vacio)");
        return sb.toString();
    }

    private void inorderTextoRec(NodoArbol nodo, StringBuilder sb) {
        if (nodo == null) return;
        inorderTextoRec(nodo.izquierdo, sb);
        sb.append(nodo).append("\n");
        inorderTextoRec(nodo.derecho, sb);
    }

    public String preorderTexto() {
        StringBuilder sb = new StringBuilder();
        preorderTextoRec(raiz, sb);
        if (sb.length() == 0) sb.append("(El arbol esta vacio)");
        return sb.toString();
    }

    private void preorderTextoRec(NodoArbol nodo, StringBuilder sb) {
        if (nodo == null) return;
        sb.append(nodo).append("\n");
        preorderTextoRec(nodo.izquierdo, sb);
        preorderTextoRec(nodo.derecho, sb);
    }

    public boolean estaVacio() { return raiz == null; }
}