/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.controlador.IControladorAlquilerVehiculos;

/**
 *
 * @author Felipon
 */
public interface IVistaAlquilerVehiculos {

    void abrirAlquiler();

    void anadirCliente();

    void anadirVehiculo();

    void borrarCliente();

    void borrarVehiculo();

    void buscarCliente();

    void buscarVehiculo();

    void cerrarAlquiler();

    void comenzar();

    void listarAlquileres();

    void listarClientes();

    void listarVehiculos();

    void salir();

    void setControlador(IControladorAlquilerVehiculos controlador);

    void obtenerAlquileresAbiertos();

    void obtenerAlquileresCliente();

    void obtenerAlquileresVehiculo();
}
