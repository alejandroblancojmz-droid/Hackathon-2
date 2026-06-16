import java.util.InputMismatchException;
import java.util.Scanner;
import services.Agenda;
import models.Contacto;
import models.AgendaException;


/**
 * Punto de entrada del programa.
 * Provee un menú interactivo por consola para probar todas las funcionalidades.
 *
 * Demuestra: manejo de excepciones con try-catch-finally, Scanner, Switch,
 * y separación de responsabilidades (Main solo orquesta, la lógica está en Agenda).
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       AGENDA TELEFÓNICA - Java OOP       ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        Agenda agenda = crearAgenda();
        ejecutarMenu(agenda);

        scanner.close();
        System.out.println("\n¡Hasta luego!");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Inicialización
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Permite al usuario elegir el tamaño de la agenda al iniciar.
     */
    private static Agenda crearAgenda() {
        System.out.println("¿Cómo deseas crear la agenda?");
        System.out.println("  1. Con tamaño por defecto (10 contactos)");
        System.out.println("  2. Con tamaño personalizado");
        System.out.print("Opción: ");

        int opcion = leerEntero();

        if (opcion == 2) {
            System.out.print("Introduce el tamaño máximo: ");
            int tamano = leerEntero();
            try {
                Agenda agenda = new Agenda(tamano);
                System.out.println("✓ Agenda creada con tamaño máximo de " + tamano + " contactos.\n");
                return agenda;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ Tamaño inválido. Se usará el tamaño por defecto (10).\n");
            }
        }
        System.out.println("✓ Agenda creada con tamaño por defecto (10 contactos).\n");
        return new Agenda();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Menú principal
    // ─────────────────────────────────────────────────────────────────────────

    private static void ejecutarMenu(Agenda agenda) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero();

            switch (opcion) {
                case 1  -> menuAnadirContacto(agenda);
                case 2  -> menuEliminarContacto(agenda);
                case 3  -> menuBuscarContacto(agenda);
                case 4  -> menuModificarTelefono(agenda);
                case 5  -> agenda.listarContactos();
                case 6  -> menuExisteContacto(agenda);
                case 7  -> agenda.espaciosLibres();
                case 8  -> mostrarEstadoAgenda(agenda);

                case 9  -> continuar = false;
                default -> System.out.println("⚠ Opción no válida. Elige entre 0 y 9.");
            }
        }
    }

    private static void mostrarMenu() {

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           MENÚ PRINCIPAL             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Añadir contacto                  ║");
        System.out.println("║  2. Eliminar contacto                ║");
        System.out.println("║  3. Buscar contacto                  ║");
        System.out.println("║  4. Modificar teléfono               ║");
        System.out.println("║  5. Listar todos los contactos       ║");
        System.out.println("║  6. Comprobar si existe contacto     ║");
        System.out.println("║  7. Ver espacios libres              ║");
        System.out.println("║  8. Estado de la agenda              ║");
        System.out.println("║  9. Salir                            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Elige una opción: ");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Opciones del menú
    // ─────────────────────────────────────────────────────────────────────────

    private static void menuAnadirContacto(Agenda agenda) {
        System.out.println("\n── Añadir contacto ──");
        try {
            String nombre   = leerCampo("Nombre:   ");
            String apellido = leerCampo("Apellido: ");
            String telefono = leerCampo("Teléfono: ");

            Contacto nuevo = new Contacto(nombre, apellido, telefono);
            agenda.anadirContacto(nuevo);

        } catch (AgendaException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
    private static void menuEliminarContacto(Agenda agenda) {
        System.out.println("\n── Eliminar contacto ──");
        try {
            String nombre   = leerCampo("Nombre:   ");
            String apellido = leerCampo("Apellido: ");

            Contacto aEliminar = new Contacto(nombre, apellido);
            agenda.eliminarContacto(aEliminar);

        } catch (AgendaException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void menuBuscarContacto(Agenda agenda) {
        System.out.println("\n── Buscar contacto ──");
        System.out.println("  1. Buscar por nombre y apellido por separado");
        System.out.println("  2. Buscar por nombre completo (Nombre Apellido)");
        System.out.print("Opción: ");

        int modo = leerEntero();

        try {
            if (modo == 2) {
                String nombreCompleto = leerCampo("Nombre completo: ");
                agenda.buscaContacto(nombreCompleto);
            } else {
                String nombre   = leerCampo("Nombre:   ");
                String apellido = leerCampo("Apellido: ");
                agenda.buscaContacto(nombre, apellido);
            }
        } catch (AgendaException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void menuModificarTelefono(Agenda agenda) {
        System.out.println("\n── Modificar teléfono ──");
        try {
            String nombre        = leerCampo("Nombre:          ");
            String apellido      = leerCampo("Apellido:        ");
            String nuevoTelefono = leerCampo("Nuevo teléfono:  ");

            agenda.modificarTelefono(nombre, apellido, nuevoTelefono);

        } catch (AgendaException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void menuExisteContacto(Agenda agenda) {
        System.out.println("\n── Comprobar existencia ──");
        try {
            String nombre   = leerCampo("Nombre:   ");
            String apellido = leerCampo("Apellido: ");

            Contacto consulta = new Contacto(nombre, apellido);
            boolean existe = agenda.existeContacto(consulta);

            if (existe) {
                System.out.println("✓ El contacto '" + consulta.getIdentificador() + "' SÍ existe en la agenda.");
            } else {
                System.out.println("✗ El contacto '" + consulta.getIdentificador() + "' NO existe en la agenda.");
            }
        } catch (AgendaException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void mostrarEstadoAgenda(Agenda agenda) {
        System.out.println("\n── Estado de la agenda ──");
        System.out.println("  Tamaño máximo : " + agenda.getTamanoMaximo());
        System.out.println("  Contactos     : " + agenda.getCantidadContactos());
        System.out.println("  Espacios libres: " + (agenda.getTamanoMaximo() - agenda.getCantidadContactos()));
        System.out.println("  ¿Agenda llena? : " + (agenda.agendaLlena() ? "SÍ ✗" : "NO ✓"));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Utilidades de lectura por consola
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Lee un número entero del usuario con manejo de excepciones.
     * @return Entero leído, o -1 si la entrada es inválida.
     */
    private static int leerEntero() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar entrada inválida
            System.out.println("⚠ Entrada inválida. Se esperaba un número.");
            return -1;
        }
    }

    /**
     * Lee una línea de texto no vacía del usuario.
     * @param etiqueta Texto que se muestra al usuario como prompt.
     * @return String con la entrada del usuario (sin espacios iniciales/finales).
     */
    private static String leerCampo(String etiqueta) {
        System.out.print(etiqueta);
        return scanner.nextLine().trim();
    }
}
