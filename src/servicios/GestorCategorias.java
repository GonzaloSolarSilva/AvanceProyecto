package servicios;

import estructuras.ArbolBinarioBusqueda;
import estructuras.NodoArbol;
import javax.swing.JOptionPane;

public class GestorCategorias {

    private ArbolBinarioBusqueda arbol;

    public GestorCategorias() {
        this.arbol = new ArbolBinarioBusqueda();
        arbol.insertar(101, "Hardware - Equipos");
        arbol.insertar(102, "Hardware - Perifericos");
        arbol.insertar(201, "Software - Sistema Operativo");
        arbol.insertar(202, "Software - Aplicaciones");
        arbol.insertar(301, "Red - Conectividad");
        arbol.insertar(302, "Red - VPN y Accesos Remotos");
        arbol.insertar(401, "Seguridad - Antivirus");
        arbol.insertar(501, "Otros");
    }

    public void menuCategorias() {
        String[] opciones = {
            "1. Listar categorias (inorder)",
            "2. Buscar categoria por codigo",
            "3. Insertar nueva categoria",
            "4. Eliminar categoria",
            "5. Ver arbol preorder",
            "0. Volver al menu principal"
        };

        while (true) {
            int sel = JOptionPane.showOptionDialog(null, "Modulo de Categorias (ABB):", "Categorias",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (sel == JOptionPane.CLOSED_OPTION || sel == 5) break;

            switch (sel) {
                case 0: {
                    StringBuilder sb = new StringBuilder("Categorias (ordenadas por codigo):\n\n");
                    sb.append(arbol.inorderTexto());
                    JOptionPane.showMessageDialog(null, sb.toString(), "Listar Categorias", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case 1: {
                    String input = JOptionPane.showInputDialog(null, "Ingrese el codigo a buscar:", "Buscar Categoria", JOptionPane.PLAIN_MESSAGE);
                    if (input == null) break;
                    try {
                        int cod = Integer.parseInt(input.trim());
                        NodoArbol encontrado = arbol.buscar(cod);
                        if (encontrado != null) {
                            JOptionPane.showMessageDialog(null, "Encontrado: " + encontrado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontro el codigo " + cod, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El codigo debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 2: {
                    String inputCod = JOptionPane.showInputDialog(null, "Codigo de la nueva categoria:", "Insertar Categoria", JOptionPane.PLAIN_MESSAGE);
                    if (inputCod == null) break;
                    String nombre = JOptionPane.showInputDialog(null, "Nombre de la categoria:", "Insertar Categoria", JOptionPane.PLAIN_MESSAGE);
                    if (nombre == null) break;
                    try {
                        int nc = Integer.parseInt(inputCod.trim());
                        arbol.insertar(nc, nombre.trim());
                        JOptionPane.showMessageDialog(null, "Categoria insertada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El codigo debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 3: {
                    String inputEl = JOptionPane.showInputDialog(null, "Codigo de la categoria a eliminar:", "Eliminar Categoria", JOptionPane.PLAIN_MESSAGE);
                    if (inputEl == null) break;
                    try {
                        int ec = Integer.parseInt(inputEl.trim());
                        arbol.eliminar(ec);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El codigo debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 4: {
                    StringBuilder sb2 = new StringBuilder("Categorias (preorder):\n\n");
                    sb2.append(arbol.preorderTexto());
                    JOptionPane.showMessageDialog(null, sb2.toString(), "Preorder", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }
}