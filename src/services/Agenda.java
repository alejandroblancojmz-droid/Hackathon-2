package services;

import models.Contacto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Agenda {

    private ArrayList<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public boolean existeContacto(Contacto c) {

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(c.getNombre())
                    && contacto.getApellido().equalsIgnoreCase(c.getApellido())) {

                return true;
            }
        }

        return false;
    }
    public void añadirContacto(Contacto c) {

        if (c.getNombre().trim().isEmpty()
                || c.getApellido().trim().isEmpty()) {

            System.out.println("El nombre o apellido no pueden estar vacíos.");
            return;
        }

        if (agendaLlena()) {

            System.out.println("La agenda está llena.");
            return;
        }

        if (existeContacto(c)) {

            System.out.println("El contacto ya existe.");
            return;
        }

        contactos.add(c);

        System.out.println("Contacto agregado correctamente.");
    }

    public void buscarContacto(String nombre, String apellido) {

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(nombre)
                    && contacto.getApellido().equalsIgnoreCase(apellido)) {

                System.out.println("Teléfono: " + contacto.getTelefono());
                return;
            }
        }

        System.out.println("Contacto no encontrado.");
    }

    public void eliminarContacto(Contacto c) {

        for (int i = 0; i < contactos.size(); i++) {

            Contacto contacto = contactos.get(i);

            if (contacto.getNombre().equalsIgnoreCase(c.getNombre())
                    && contacto.getApellido().equalsIgnoreCase(c.getApellido())) {

                contactos.remove(i);

                System.out.println("Contacto eliminado correctamente.");
                return;
            }
        }

        System.out.println("El contacto no existe.");
    }

    public void modificarTelefono(String nombre,
                                  String apellido,
                                  String nuevoTelefono) {

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(nombre)
                    && contacto.getApellido().equalsIgnoreCase(apellido)) {

                contacto.setTelefono(nuevoTelefono);

                System.out.println("Teléfono actualizado correctamente.");

                return;
            }
        }

        System.out.println("Contacto no encontrado.");
    }

    public void listarContactos() {

        if (contactos.isEmpty()) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        Collections.sort(contactos,
                Comparator.comparing(Contacto::getNombre)
                        .thenComparing(Contacto::getApellido));

        for (Contacto contacto : contactos) {

            System.out.println(
                    contacto.getNombre() + " "
                            + contacto.getApellido()
                            + " - "
                            + contacto.getTelefono()
            );
        }
    }
}




