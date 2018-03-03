/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author Felipillo
 *
 */
public enum Opcion {

    SALIR("SALIR") {
        public void ejecutar() {
            vista.salir();
        }
    },
    ANADIR_CLIENTE("AÑADIR CLIENTE") {
        public void ejecutar() {
            vista.anadirCliente();
        }
    },
    BORRAR_CLIENTE("BORRAR CLIENTE") {
        public void ejecutar() {
            vista.borrarCliente();
        }
    },
    BUSCAR_CLIENTE("BUSCAR CLIENTE") {
        public void ejecutar() {
            vista.buscarCliente();
        }
    },
    LISTAR_CLIENTES("LISTAR CLIENTES") {
        public void ejecutar() {
            vista.listarClientes();
        }
    },
    ANADIR_VEHICULO("AÑADIR VEHICULO") {
        public void ejecutar() {
            vista.anadirVehiculo();
        }
    },
    BORRAR_VEHICULO("BORRAR VEHICULO") {
        public void ejecutar() {
            vista.borrarVehiculo();
        }
    },
    BUSCAR_VEHICULO("BUSCAR VEHICULO") {
        public void ejecutar() {
            vista.buscarVehiculo();
        }
    },
    LISTAR_VEHICULOS("LISTAR VEHICULOS") {
        public void ejecutar() {
            vista.listarVehiculos();
        }
    },
    ABRIR_ALQUILER("ABRIR ALQUILER") {
        public void ejecutar() {
            vista.abrirAlquiler();
        }
    },
    CERRAR_ALQUILER("CERRAR ALQUILER") {
        public void ejecutar() {
            vista.cerrarAlquiler();
        }
    },
    LISTAR_ALQUILERES("LISTAR ALQUILERES") {
        public void ejecutar() {
            vista.listarAlquileres();
        }
    },
    LISTAR_ALQUILERES_ABIERTOS("LISTAR ALQUILERES ABIERTOS") {
        public void ejecutar() {
            vista.ObtenerAlquileresAbiertos();
        }

    };

    private String mensaje;
    private static IVistaAlquilerVehiculos vista;

    private Opcion(String mensaje) {
        this.mensaje = mensaje;
    }

    public abstract void ejecutar();

    public String getMensaje() {
        return mensaje;

    }

    public static void setVista(IVistaAlquilerVehiculos vista) {
        Opcion.vista = vista;

    }

    public String toString() {

        return String.format("%d.- %s", ordinal(), mensaje);

    }

    public static Opcion getOpcionSegunOridnal(int ordinal) {
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
