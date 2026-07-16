# AvanceProyecto

--------------------------------------------

CABRERA VALENCIA ESIEN GABRIEL

LISTAS ENLAZADA 1.0


Lo que agregue fueron

-entidades/Cliente.java = que es para guardar información

-estructuras/ListaEnlazada.java= es la misma estructura de datos q hay y tmb usa la clase Nodo

-servicios/GestorClientes.java= la misma lógica que y patron de GestorCategorias solo que dirigida a cliente


Y modifique la clase main agregándole el case 14 en Switch que llama al gestor clientes

tmb movi algunas cosas del main como los separadores pq al ejecutar estaba todo en ??????? también dejando algunas palabras sin tildes para evitar eso

------------------------------------------- 
SOLAR SILVA GONZALO — Refactor JOptionPane 1.1
- Reemplace  el menú de consola por ventanas emergentes con JOptionPane
- Corregí bug de crash al ingresar letras en el menú principal
- Corregí bug del historial invertido al exportar (opción 12)
- Corregí bug de mensaje de error falso al eliminar categorías en el árbol binario
- Agregue métodos auxiliares: `toArray()` en ColaConPrioridad, `toTexto()` en ListaEnlazada, `inorderTexto()` y `preorderTexto()` en ArbolBinarioBusqueda

# PROYECTO FINAL

## Sistema de Gestión de Tickets de Soporte Técnico
### Telefónica del Perú S.A.A. — Mesa de Ayuda TI

---

## Integrantes
- Cabrera Valencia, Esien Gabriel — U23200241
- Garay Llaja, Leonardo Luis — U23324618
- Gonzalo León, Paolo Alessandro — U23246812
- Solar Silva, Gonzalo — U23214609

**Docente:** Anselmo Aniceto Valenzuela Zegarra  
**Curso:** Algoritmos y Estructuras de Datos — UTP 2026

---

## Descripción
Aplicativo de escritorio desarrollado en Java que automatiza la gestión de tickets de soporte técnico para Telefónica del Perú, implementando cuatro estructuras de datos desde cero.

---

## Estructuras de Datos Implementadas
- **Cola con Prioridad** — gestión de tickets por criticidad (Alta, Media, Baja)
- **Pila** — historial de acciones y tickets en proceso
- **Árbol Binario de Búsqueda** — categorías de incidencias
- **Lista Enlazada** — registro de clientes

---

## Cambios por integrante

### CABRERA VALENCIA ESIEN GABRIEL — Lista Enlazada 1.0
- Agregó `entidades/Cliente.java` para guardar información de clientes
- Agregó `estructuras/ListaEnlazada.java` usando la clase Nodo
- Agregó `servicios/GestorClientes.java` con lógica CRUD de clientes
- Modificó la clase main agregando el case 14 en el Switch
- Ajustó separadores del menú y eliminó tildes para evitar caracteres especiales

### SOLAR SILVA GONZALO — Refactor JOptionPane + Interfaz Swing
- Reemplazó el menú de consola por ventanas emergentes con JOptionPane
- Corrigió bug de crash al ingresar letras en el menú principal
- Corrigió bug del historial invertido al exportar (opción 12)
- Corrigió bug de mensaje de error falso al eliminar en el árbol binario
- Agregó métodos auxiliares: `toArray()`, `toTexto()`, `inorderTexto()`, `preorderTexto()`
- Rediseñó la interfaz con Swing: ventana principal con diseño corporativo Telefónica del Perú, header con logo, 6 tarjetas por módulo con colores, botones animados con hover y footer con confirmación de salida

---

## Funcionalidades
| Opción | Función | Estructura |
|--------|---------|------------|
| 1 | Registrar nuevo ticket | Cola con Prioridad |
| 2 | Atender siguiente ticket | Cola con Prioridad |
| 3 | Ver ticket al frente (peek) | Cola con Prioridad |
| 4 | Ver todos los tickets en espera | Cola con Prioridad |
| 5 | Buscar ticket por ID | Cola con Prioridad |
| 6 | Reencolar ticket | Cola con Prioridad |
| 7 | Ver historial de acciones | Pila |
| 8 | Deshacer última acción | Pila |
| 9 | Marcar ticket en proceso | Pila |
| 10 | Ver tickets en proceso | Pila |
| 11 | Ver estadísticas | Reportes |
| 12 | Exportar historial a .txt | Reportes |
| 13 | Gestionar categorías | Árbol Binario |
| 14 | Gestionar clientes | Lista Enlazada |

---

## Repositorio
🔗 https://github.com/GonzaloSolarSilva/AvanceProyecto