/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipon
 */
public interface IControladorAlquilerVehiculos {

    void abrirAlquiler(Cliente cliente, Vehiculo turismo);

    void anadirCliente(Cliente cliente);

    void anadirVehiculo(Vehiculo vehiculo);

    void borrarCliente(String dni);

    void borrarVehiculo(String matricula);

    Cliente buscarCliente(String dni);

    Vehiculo buscarVehiculo(String matricula);

    void cerrarAlquiler(Cliente cliente, Vehiculo turismo);

    void comenzar();
     
    void salir();

    Alquiler[] obtenerAlquileres();

    Cliente[] obtenerClientes();

    Vehiculo[] obtenerVehiculos();
    
}
