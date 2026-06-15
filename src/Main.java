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


}
