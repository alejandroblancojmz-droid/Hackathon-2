    package models;
    /**
     * Clase abstracta base que define el contrato de cualquier entidad
     * que pueda ser almacenada en la agenda.
     *
     * Demuestra: clase abstracta, métodos abstractos, polimorfismo.
     */
    public abstract class EntidadAgenda {

        /**
         * Devuelve el identificador único legible de la entidad.
         * @return String con el identificador.
         */
        public abstract String getIdentificador();

        /**
         * Muestra por consola la información completa de la entidad.
         */
        public abstract void mostrar();

        /**
         * Determina si esta entidad es igual a otra según las reglas de negocio.
         * @param otra EntidadAgenda a comparar.
         * @return true si se consideran iguales.
         */
        public abstract boolean esIgual(EntidadAgenda otra);
    }


