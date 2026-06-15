package services;

import models.Contacto;
import services.Agenda;

import java.util.Scanner;

    public class Main {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            Agenda agenda = new Agenda(10);

            int opcion;

            do {

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

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

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

                        Contacto nuevoContacto =
                                new Contacto(nombre, apellido, telefono);

                        agenda.añadirContacto(nuevoContacto);

                        break;

                    case 2:

                        System.out.print("Nombre: ");
                        String nombreBuscar = sc.nextLine();

                        System.out.print("Apellido: ");
                        String apellidoBuscar = sc.nextLine();

                        agenda.buscarContacto(nombreBuscar, apellidoBuscar);

                        break;

                    case 3:

                        agenda.listarContactos();

                        break;

                    case 4:

                        System.out.print("Nombre: ");
                        String nombreEliminar = sc.nextLine();

                        System.out.print("Apellido: ");
                        String apellidoEliminar = sc.nextLine();

                        Contacto contactoEliminar =
                                new Contacto(nombreEliminar, apellidoEliminar, "");

                        agenda.eliminarContacto(contactoEliminar);

                        break;

                    case 5:

                        System.out.print("Nombre: ");
                        String nombreModificar = sc.nextLine();

                        System.out.print("Apellido: ");
                        String apellidoModificar = sc.nextLine();

                        System.out.print("Nuevo teléfono: ");
                        String nuevoTelefono = sc.nextLine();

                        agenda.modificarTelefono(
                                nombreModificar,
                                apellidoModificar,
                                nuevoTelefono
                        );

                        break;

                    case 6:

                        System.out.println(
                                "Espacios disponibles: "
                                        + agenda.espaciosLibres()
                        );

                        break;

                    case 7:

                        if (agenda.agendaLlena()) {

                            System.out.println("La agenda está llena.");

                        } else {

                            System.out.println("Aún hay espacio disponible.");
                        }

                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } while (opcion != 0);

            sc.close();
        }
    }



