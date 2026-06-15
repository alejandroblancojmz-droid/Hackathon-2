// Declaración del paquete al que pertenece la clase
package services;

// Importaciones de otras clases del proyecto
import models.Contacto;
import services.Agenda;

// Importación de librerías nativas de Java
import java.util.Scanner;

// Definición de la clase principal
public class Main {

    // Metodo principal (punto de entrada del programa)
    public static void main(String[] args) {

        // Inicialización del lector de entrada por consola
        Scanner sc = new Scanner(System.in);

        // Instanciación del objeto Agenda con capacidad para 10 contactos
        Agenda agenda = new Agenda(10);

        // Variable para almacenar la opción del menú seleccionada por el usuario
        int opcion;

        // Bucle de control para mantener el menú activo hasta que se decida salir
        do {

            // Impresión del menú de opciones en la consola
            System.out.println("\n===== AGENDA TELEFÓNICA =====");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Espacios libres");
            System.out.println("7. Agenda llena");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opción: ");

            // Captura de la opción numérica
            opcion = sc.nextInt();
            sc.nextLine(); // Limpieza del buffer del Scanner

            // Estructura de control condicional según la opción elegida
            switch (opcion) {

                // CASO 1: Agregar un nuevo contacto
                case 1:

                    String nombre;
                    String apellido;
                    String telefono;

                    System.out.print("Nombre: ");
                    nombre = sc.nextLine();

                    System.out.print("Apellido: ");
                    apellido = sc.nextLine();

                    System.out.print("Teléfono: ");
                    telefono = sc.nextLine();

                    // Creación del objeto Contacto con los datos ingresados
                    Contacto nuevoContacto =
                            new Contacto(nombre, apellido, telefono);

                    // Llamada al metodo para agregar el contacto a la agenda
                    agenda.añadirContacto(nuevoContacto);

                    break;

                // CASO 2: Buscar un contacto existente
                case 2:

                    System.out.print("Nombre: ");
                    String nombreBuscar = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellidoBuscar = sc.nextLine();

                    // Llamada al metodo de búsqueda
                    agenda.buscarContacto(nombreBuscar, apellidoBuscar);

                    break;

                // CASO 3: Mostrar todos los contactos
                case 3:

                    // Llamada al metodo de listado
                    agenda.listarContactos();

                    break;

                // CASO 4: Eliminar un contacto
                case 4:

                    System.out.print("Nombre: ");
                    String nombreEliminar = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellidoEliminar = sc.nextLine();

                    // Creación de un objeto temporal para referenciar el contacto a borrar
                    Contacto contactoEliminar =
                            new Contacto(nombreEliminar, apellidoEliminar, "");

                    // Llamada al metodo de eliminación
                    agenda.eliminarContacto(contactoEliminar);

                    break;

                // CASO 5: Modificar el teléfono de un contacto
                case 5:

                    System.out.print("Nombre: ");
                    String nombreModificar = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellidoModificar = sc.nextLine();

                    System.out.print("Nuevo teléfono: ");
                    String nuevoTelefono = sc.nextLine();

                    // Llamada al metodo de actualización
                    agenda.modificarTelefono(
                            nombreModificar,
                            apellidoModificar,
                            nuevoTelefono
                    );

                    break;

                // CASO 6: Consultar cuántos espacios quedan vacíos
                case 6:

                    System.out.println(
                            "Espacios disponibles: "
                                    + agenda.espaciosLibres()
                    );

                    break;

                // CASO 7: Verificar si la agenda llegó a su límite
                case 7:

                    // Evaluación del estado de la agenda (llena o con espacio)
                    if (agenda.agendaLlena()) {

                        System.out.println("La agenda está llena.");

                    } else {

                        System.out.println("Aún hay espacio disponible.");
                    }

                    break;

                // CASO 0: Finalizar la ejecución
                case 0:
                    System.out.println("Saliendo...");
                    break;

                // CASO POR DEFECTO: Manejo de opciones no válidas en el menú
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0); // El bucle se repite mientras la opción no sea 0

        // Cierre del recurso Scanner para liberar memoria
        sc.close();
    }
}
