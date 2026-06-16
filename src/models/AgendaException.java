package models;

/**
 * Excepción personalizada para errores de la agenda telefónica.
 *
 * Demuestra: uso de excepciones extendiendo la clase Exception de Java,
 * sin implementarlas desde cero sino reutilizando la jerarquía estándar.
 */
public class AgendaException extends Exception {

    /**
     * Código de error para identificar el tipo de fallo.
     */
    private final int codigoError;

    // Códigos de error estándar de la agenda
    public static final int ERR_CAMPO_VACIO = 1;
    public static final int ERR_DUPLICADO = 2;
    public static final int ERR_AGENDA_LLENA = 3;
    public static final int ERR_NO_ENCONTRADO = 4;
    /**
     * Constructor con mensaje y código de error.
     *
     * @param mensaje     Descripción del error.
     * @param codigoError Código numérico del tipo de error.
     */
    public AgendaException(String mensaje, int codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
    }
    /**
     * Constructor simplificado (overloading) sin código de error explícito.
     *
     * @param mensaje Descripción del error.
     */
    public AgendaException(String mensaje) {
        super(mensaje);
        this.codigoError = 0;
    }

    public int getCodigoError() {
        return codigoError;
    }

    @Override
    public String toString() {
        return String.format("[ERROR %d] %s", codigoError, getMessage());
    }
}