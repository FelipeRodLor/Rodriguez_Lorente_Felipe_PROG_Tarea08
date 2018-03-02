/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */
package utilidades;

import mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.Opcion;

/**
 *
 *
 *
 * @author Felipillo
 *
 */
public class Consola {

    public static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n", mensaje);
        for (int i = 0; i < mensaje.length(); i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public static int elegirOpcion() {
        int ordinalOpcion;

        do {
            System.out.print("\nElige una opción: ");
            ordinalOpcion = Entrada.entero();
        } while (!Opcion.esOrdinalValido(ordinalOpcion));
        return ordinalOpcion;
    }

    public static String leerDni() {
        System.out.print("Introduce el DNI del cliente: ");
        String dniBorrar = Entrada.cadena();
        return dniBorrar;
    }

    public static String leerMatricula() {
        System.out.print("Introduce la matrícula del vehículo: ");
        String matriculaBorrar = Entrada.cadena();

        return matriculaBorrar;
    }

    public static Cliente leerCliente() {
        Cliente nuevoCliente = null;
        mostrarCabecera("DATOS DEL CLIENTE");
        System.out.print("Nombre; ");
        String nombre = Entrada.cadena();
        System.out.print("Dni; ");
        String dni = Entrada.cadena();
        System.out.print("Calle; ");
        String calle = Entrada.cadena();
        System.out.print("Localidad; ");
        String localidad = Entrada.cadena();
        System.out.print("Codigo Postal; ");
        String codigoPostal = Entrada.cadena();

        try {
            DireccionPostal nuevaDireccionPostal = new DireccionPostal(calle, localidad, codigoPostal);
            nuevoCliente = new Cliente(nombre, dni, nuevaDireccionPostal);

        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
        return nuevoCliente;
    }

    public static Vehiculo leerVehiculo() {
        Vehiculo nuevoVehiculo = null;
        DatosTecnicosVehiculo datosTecnicos;
        int ordinalTipoVehiculo;

        mostrarCabecera("DATOS DEL VEHICULO");

        System.out.print("Matricula; ");
        String matricula = Entrada.cadena();
        System.out.print("Marca; ");
        String marca = Entrada.cadena();
        System.out.print("Modelo; ");
        String modelo = Entrada.cadena();
        System.out.print("Cilindrada; ");
        int cilindrada = Entrada.entero();
        System.out.print("Numero de plazas; ");
        int numeroPlazas = Entrada.entero();
        System.out.print("PMA; ");
        int pma = Entrada.entero();

        try{
        datosTecnicos = new DatosTecnicosVehiculo(cilindrada, numeroPlazas, pma);
        ordinalTipoVehiculo = elegirTipoVehiculo();
        nuevoVehiculo = TipoVehiculo.getTipoVehiculoSegunOridnal(ordinalTipoVehiculo).getInstancia(matricula, marca, modelo, datosTecnicos);
        
        }catch (ExcepcionAlquilerVehiculos e){
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
        return nuevoVehiculo;
    }

    public static void mostrarMenu() {
        mostrarCabecera("ALQUILER VEHÍCULOS");

        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static int elegirTipoVehiculo() {
        int ordinalTipoVehiculo;

        do {
            System.out.println("\n¿Que tipo de vehiculo es? ");
            System.out.println("");
            System.out.println(obtenerTiposVehiculo());

            ordinalTipoVehiculo = Entrada.entero();

        } while (!TipoVehiculo.esOrdinalValido(ordinalTipoVehiculo));

        return ordinalTipoVehiculo;
    }

    private static String obtenerTiposVehiculo() {
        StringBuilder tiposVehiculo = new StringBuilder("");
        for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
            tiposVehiculo.append(tipoVehiculo.ordinal()).append(".- ").append(tipoVehiculo).append(" ");
        }
        return tiposVehiculo.toString();
    }
}
