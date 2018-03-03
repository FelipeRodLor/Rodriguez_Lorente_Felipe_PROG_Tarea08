/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */
package mvc.controlador;

import java.util.List;
import mvc.modelo.IModeloAlquilerVehiculos;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.IVistaAlquilerVehiculos;

/**
 *
 * @author Felipon
 */
public class ControladorAlquilerVehiculos implements IControladorAlquilerVehiculos {

    IVistaAlquilerVehiculos vista;
    IModeloAlquilerVehiculos modelo;

    public ControladorAlquilerVehiculos(IModeloAlquilerVehiculos modelo, IVistaAlquilerVehiculos vista) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setControlador(this);
    }

    @Override
    public void comenzar() {
        //modelo.añadirDatosPrueba();
        modelo.leerClientes();
        modelo.leerAlquileres();
        modelo.leerVehiculos();
        vista.comenzar();
    }

    @Override
    public void salir() {
        modelo.escribirClientes();
        modelo.escribirVehiculos();
        modelo.escribirAlquileres();
    }

    @Override
    public void anadirVehiculo(Vehiculo vehiculo) {
        modelo.añadirVehiculo(vehiculo);
    }

    @Override
    public void borrarVehiculo(String matricula) {
        modelo.borrarVehiculo(matricula);
    }

    @Override
    public Vehiculo buscarVehiculo(String matricula) {
        return modelo.buscarVehiculo(matricula);
    }

    @Override
    public List<Vehiculo> obtenerVehiculos() {
        return modelo.obtenerVehiculos();
    }

    @Override
    public void anadirCliente(Cliente cliente) {
        modelo.añadirCliente(cliente);
    }

    @Override
    public void borrarCliente(String dni) {
        modelo.borrarCliente(dni);
    }

    @Override
    public Cliente buscarCliente(String dni) {
        return modelo.buscarCliente(dni);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return modelo.obtenerClientes();
    }

    @Override
    public void abrirAlquiler(Cliente cliente, Vehiculo turismo) {
        modelo.abrirAlquiler(cliente, turismo);
    }

    @Override
    public void cerrarAlquiler(Cliente cliente, Vehiculo turismo) {
        modelo.cerrarAlquiler(cliente, turismo);
    }

    @Override
    public List<Alquiler> obtenerAlquileres() {
        return modelo.obtenerAlquileres();
    }

    @Override
    public List<Alquiler> obtenerAlquileresAbiertos() {
        return modelo.obtenerAlquileresAbiertos();
    }

    @Override
    public List<Alquiler> obtenerAlquileresCliente(String dni) {
        return modelo.obtenerAlquileresCliente(dni);
    }

    public List<Alquiler> obtenerAlquileresVehiculo(String matricula) {
        return modelo.obtenerAlquileresVehiculo(matricula);
    }
}
