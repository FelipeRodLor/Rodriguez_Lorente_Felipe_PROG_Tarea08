/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author Felipon
 */
public enum TipoVehiculo {

    TURISMO("TURISMO") {
        public Turismo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
            return new Turismo(matricula, marca, modelo, datosTecnicos);
        }
    },
    DE_CARGA("DE CARGA") {
        public DeCarga getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
            return new DeCarga(matricula, marca, modelo, datosTecnicos);
        }

    },
    AUTOBUS("AUTOBUS") {
        public Autobus getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
            return new Autobus(matricula, marca, modelo, datosTecnicos);
        }

    };

    private String tipo;

    public abstract Vehiculo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos);

    private TipoVehiculo(String tipoVehiculo) {
        tipo = tipoVehiculo;
    }

    public String toString() {

        return tipo;
    }

    public static TipoVehiculo getTipoVehiculoSegunOridnal(int ordinal) {
        if (esOrdinalValido(ordinal)) {
            return values()[ordinal];
        } else {
            throw new ExcepcionAlquilerVehiculos("Ordinal de la opción no válido");
        }

    }

    public static boolean esOrdinalValido(int ordinal) {
        return (ordinal >= 0 && ordinal <= values().length - 1) ? true : false;
    }

}
