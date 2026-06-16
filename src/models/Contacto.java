package models;

/**
 * Representa un contacto de la agenda telefónica.
 *
 * Demuestra: herencia (extends EntidadAgenda), constructores sobrecargados,
 * getters/setters con validación, equals/hashCode, toString, métodos abstractos
 * implementados y method overloading.
 */
public class Contacto extends EntidadAgenda {

    private String nombre;
    private String apellido;
    private String telefono;

    // ─────────────────────────────────────────────────────────────────────────
    // Constructores sobrecargados (Constructor Overloading)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Constructor con nombre, apellido y teléfono.
     * @param nombre    Nombre del contacto (no puede estar vacío).
     * @param apellido  Apellido del contacto (no puede estar vacío).
     * @param telefono  Número de teléfono del contacto.
     * @throws AgendaException si nombre o apellido están vacíos.
     */
    public Contacto(String nombre, String apellido, String telefono) throws AgendaException {
        setNombre(nombre);
        setApellido(apellido);
        this.telefono = (telefono != null) ? telefono.trim() : "";
    }

    /**
     * Constructor sin teléfono (overloading).
     * El teléfono queda como "Sin teléfono" por defecto.
     * @param nombre   Nombre del contacto.
     * @param apellido Apellido del contacto.
     * @throws AgendaException si nombre o apellido están vacíos.
     */
    public Contacto(String nombre, String apellido) throws AgendaException {
        this(nombre, apellido, "Sin teléfono");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Getters y Setters con validación
    // ─────────────────────────────────────────────────────────────────────────

    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del contacto validando que no esté vacío.
     * @throws AgendaException si el nombre es nulo o vacío.
     */
    public void setNombre(String nombre) throws AgendaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new AgendaException("El nombre no puede estar vacío.", AgendaException.ERR_CAMPO_VACIO);
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del contacto validando que no esté vacío.
     * @throws AgendaException si el apellido es nulo o vacío.
     */
    public void setApellido(String apellido) throws AgendaException {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new AgendaException("El apellido no puede estar vacío.", AgendaException.ERR_CAMPO_VACIO);
        }
        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = (telefono != null) ? telefono.trim() : "";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Implementación de métodos abstractos de EntidadAgenda
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Retorna "Nombre Apellido" como identificador único.
     */
    @Override
    public String getIdentificador() {
        return nombre + " " + apellido;
    }

    /**
     * Imprime el contacto en formato: Nombre Apellido - Teléfono.
     */
    @Override
    public void mostrar() {
        System.out.println(this);
    }

    /**
     * Dos contactos son iguales si comparten nombre y apellido (sin distinguir mayúsculas).
     */
    @Override
    public boolean esIgual(EntidadAgenda otra) {
        if (!(otra instanceof Contacto)) return false;
        Contacto otroContacto = (Contacto) otra;
        return this.nombre.equalsIgnoreCase(otroContacto.nombre)
                && this.apellido.equalsIgnoreCase(otroContacto.apellido);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // equals / hashCode / toString  (buenas prácticas Java)
    // ─────────────────────────────────────────────────────────────────────────

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contacto)) return false;
        return esIgual((Contacto) obj);
    }

    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + "|" + apellido.toLowerCase()).hashCode();
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Method Overloading adicional: comparar solo por nombre o por nombre+apellido
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Verifica si este contacto coincide con el nombre y apellido proporcionados.
     * @param nombre   Nombre a comparar.
     * @param apellido Apellido a comparar.
     * @return true si coinciden (case-insensitive).
     */
    public boolean coincideCon(String nombre, String apellido) {
        return this.nombre.equalsIgnoreCase(nombre)
                && this.apellido.equalsIgnoreCase(apellido);
    }

    /**
     * Verifica si el nombre completo coincide con la cadena dada.
     * Overloading de coincideCon con un único parámetro.
     * @param nombreCompleto "Nombre Apellido" a buscar.
     * @return true si el identificador coincide (case-insensitive).
     */
    public boolean coincideCon(String nombreCompleto) {
        return this.getIdentificador().equalsIgnoreCase(nombreCompleto.trim());
    }
}

