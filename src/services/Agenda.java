package services;
import models.AgendaException;
import models.Contacto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Agenda {


    /**
     * Gestiona una colección de contactos con tamaño máximo configurable.
     *
     * Demuestra: colecciones (ArrayList, List), constructores sobrecargados,
     * uso de excepciones, streams, Comparator, Optional y buenas prácticas OOP.
     */


    // ─────────────────────────────────────────────────────────────────────────
    // Constantes y atributos
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Tamaño por defecto de la agenda si no se especifica otro.
     */
    private static final int TAMANO_POR_DEFECTO = 10;

    private final int tamanoMaximo;
    private final List<Contacto> contactos;

    // ─────────────────────────────────────────────────────────────────────────
    // Constructores sobrecargados (Constructor Overloading)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Crea una agenda con tamaño máximo personalizado.
     *
     * @param tamanoMaximo Número máximo de contactos permitidos (debe ser > 0).
     * @throws IllegalArgumentException si el tamaño es menor o igual a 0.
     */
    public Agenda(int tamanoMaximo) {
        if (tamanoMaximo <= 0) {
            throw new IllegalArgumentException("El tamaño de la agenda debe ser mayor a 0.");
        }
        this.tamanoMaximo = tamanoMaximo;
        this.contactos = new ArrayList<>();
    }

    /**
     * Crea una agenda con el tamaño por defecto (10 contactos).
     * Overloading del constructor sin parámetros.
     */
    public Agenda() {
        this(TAMANO_POR_DEFECTO);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Métodos principales de la agenda
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Añade un contacto a la agenda.
     * Valida: agenda no llena, nombre/apellido no vacíos, no duplicados.
     *
     * @param contacto Contacto a añadir.
     * @throws AgendaException ERR_AGENDA_LLENA si no hay espacio.
     * @throws AgendaException ERR_DUPLICADO si el contacto ya existe.
     */
    public void añadirContacto(Contacto contacto) throws AgendaException {
        if (agendaLlena()) {
            throw new AgendaException(
                    "La agenda está llena. No se pueden añadir más contactos (máximo: " + tamanoMaximo + ").",
                    AgendaException.ERR_AGENDA_LLENA
            );
        }
        if (existeContacto(contacto)) {
            throw new AgendaException(
                    "El contacto '" + contacto.getIdentificador() + "' ya existe en la agenda.",
                    AgendaException.ERR_DUPLICADO
            );
        }
        contactos.add(contacto);
        System.out.println("✓ Contacto añadido: " + contacto.getIdentificador());
    }

    /**
     * Verifica si un contacto ya existe en la agenda.
     * La comparación es por nombre y apellido, ignorando el teléfono y las mayúsculas.
     *
     * @param contacto Contacto a verificar.
     * @return true si existe, false en caso contrario.
     */
    public boolean existeContacto(Contacto contacto) {
        return contactos.stream().anyMatch(c -> c.esIgual(contacto));
    }

    /**
     * Lista todos los contactos de la agenda ordenados alfabéticamente
     * por nombre y apellido.
     */
    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("  AGENDA TELEFÓNICA (" + contactos.size() + "/" + tamanoMaximo + " contactos)");
        System.out.println("══════════════════════════════════════");

        contactos.stream()
                .sorted(Comparator
                        .comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER))
                .forEach(Contacto::mostrar);

        System.out.println("══════════════════════════════════════\n");
    }

    /**
     * Busca un contacto por nombre completo ("Nombre Apellido").
     * Overloading: acepta el nombre completo como un único String.
     *
     * @param nombreCompleto Nombre y apellido en formato "Nombre Apellido".
     * @throws AgendaException ERR_NO_ENCONTRADO si no existe.
     */
    public void buscaContacto(String nombreCompleto) throws AgendaException {
        String[] partes = nombreCompleto.trim().split("\\s+", 2);
        if (partes.length < 2) {
            throw new AgendaException(
                    "Formato incorrecto. Usa: 'Nombre Apellido'.",
                    AgendaException.ERR_CAMPO_VACIO
            );
        }
        buscaContacto(partes[0], partes[1]);
    }

    /**
     * Busca un contacto por nombre y apellido por separado.
     * Overloading de buscaContacto con dos parámetros.
     *
     * @param nombre   Nombre del contacto.
     * @param apellido Apellido del contacto.
     * @throws AgendaException ERR_NO_ENCONTRADO si no existe.
     */
    public void buscaContacto(String nombre, String apellido) throws AgendaException {
        Optional<Contacto> resultado = contactos.stream()
                .filter(c -> c.coincideCon(nombre, apellido))
                .findFirst();

        if (resultado.isPresent()) {
            Contacto encontrado = resultado.get();
            System.out.println("✓ Contacto encontrado:");
            encontrado.mostrar();
        } else {
            throw new AgendaException(
                    "No se encontró ningún contacto con nombre '" + nombre + " " + apellido + "'.",
                    AgendaException.ERR_NO_ENCONTRADO
            );
        }
    }

    /**
     * Elimina un contacto de la agenda.
     *
     * @param contacto Contacto a eliminar (se busca por nombre y apellido).
     * @throws AgendaException ERR_NO_ENCONTRADO si el contacto no existe.
     */
    public void eliminarContacto(Contacto contacto) throws AgendaException {
        boolean eliminado = contactos.removeIf(c -> c.esIgual(contacto));

        if (eliminado) {
            System.out.println("✓ Contacto '" + contacto.getIdentificador() + "' eliminado correctamente.");
        } else {
            throw new AgendaException(
                    "No se pudo eliminar: el contacto '" + contacto.getIdentificador() + "' no existe en la agenda.",
                    AgendaException.ERR_NO_ENCONTRADO
            );
        }
    }

    /**
     * Modifica el teléfono de un contacto existente.
     *
     * @param nombre        Nombre del contacto.
     * @param apellido      Apellido del contacto.
     * @param nuevoTelefono Nuevo número de teléfono.
     * @throws AgendaException ERR_NO_ENCONTRADO si el contacto no existe.
     */
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) throws AgendaException {
        Optional<Contacto> resultado = contactos.stream()
                .filter(c -> c.coincideCon(nombre, apellido))
                .findFirst();

        if (resultado.isPresent()) {
            String telefonoAnterior = resultado.get().getTelefono();
            resultado.get().setTelefono(nuevoTelefono);
            System.out.println("✓ Teléfono de '" + nombre + " " + apellido + "' actualizado: "
                    + telefonoAnterior + " → " + nuevoTelefono);
        } else {
            throw new AgendaException(
                    "No se encontró el contacto '" + nombre + " " + apellido + "' para modificar.",
                    AgendaException.ERR_NO_ENCONTRADO
            );
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Métodos de estado de la agenda
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Indica si la agenda está llena.
     *
     * @return true si el número de contactos alcanzó el máximo.
     */
    public boolean agendaLlena() {
        return contactos.size() >= tamanoMaximo;
    }

    /**
     * Muestra cuántos espacios libres quedan en la agenda.
     */
    public void espaciosLibres() {
        int libres = tamanoMaximo - contactos.size();
        if (libres == 0) {
            System.out.println("✗ No hay espacio disponible. La agenda está llena.");
        } else {
            System.out.println("Espacios libres en la agenda: " + libres + "/" + tamanoMaximo);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Getters de información general
    // ─────────────────────────────────────────────────────────────────────────

    public int getTamanoMaximo() {
        return tamanoMaximo;
    }

    public int getCantidadContactos() {
        return contactos.size();
    }

    /**
     * Devuelve una copia inmutable de la lista de contactos.
     */
    public List<Contacto> getContactos() {
        return List.copyOf(contactos);
    }
}