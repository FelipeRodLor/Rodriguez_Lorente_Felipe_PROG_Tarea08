/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.util.List;
import mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import mvc.modelo.dao.Alquileres;
import mvc.modelo.dao.Clientes;
import mvc.modelo.dao.Vehiculos;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;

/**
 *
 * @author Felipillo
 */
public class ModeloAlquilerVehiculos implements IModeloAlquilerVehiculos {

    private Clientes clientes;
    private Alquileres alquileres;
    private Vehiculos vehiculos;

    public ModeloAlquilerVehiculos() {
        clientes = new Clientes();
        alquileres = new Alquileres();
        vehiculos = new Vehiculos();
    }

    @Override
    public List<Alquiler> obtenerAlquileres() {
        return alquileres.getAlquiler();
    }

    @Override
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculos.getVehiculo();
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clientes.getClientes();
    }

    @Override
    public Cliente buscarCliente(String dni) {
        return clientes.buscar(dni);
    }

    @Override
    public void añadirCliente(Cliente cliente) {
        clientes.añadir(cliente);
    }

    @Override
    public void borrarCliente(String dni) {
        clientes.borrar(dni);
    }

    @Override
    public void leerClientes() {
        clientes.leerClientes();
    }

    @Override
    public void escribirClientes() {
        clientes.escribirClientes();
    }

    @Override
    public void añadirVehiculo(Vehiculo vehiculo) {
        vehiculos.añadir(vehiculo);
    }

    @Override
    public void borrarVehiculo(String matricula) {
        vehiculos.borrar(matricula);
    }

    @Override
    public Vehiculo buscarVehiculo(String matricula) {
        return vehiculos.buscar(matricula);
    }

    @Override
    public void leerVehiculos() {
        vehiculos.leerVehiculos();
    }

    @Override
    public void escribirVehiculos() {
        vehiculos.escribirVehiculos();
    }

    @Override
    public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
        alquileres.abrir(cliente, vehiculo);
    }

    @Override
    public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
        alquileres.cerrar(cliente, vehiculo);
    }

    @Override
    public List<Alquiler> obtenerAlquileresAbiertos() {
        return alquileres.obtenerAlquileresAbiertos();
    }

    @Override
    public List<Alquiler> obtenerAlquileresCliente(String dni) {
        return alquileres.obtenerAlquileresCliente(dni);
    }

    @Override
    public List<Alquiler> obtenerAlquileresVehiculo(String matricula) {
        return alquileres.obtenerAlquileresVehiculo(matricula);
    }

    @Override
    public void leerAlquileres() {
        alquileres.leerAlquileres();
    }

    @Override
    public void escribirAlquileres() {
        alquileres.escribirAlquileres();
    }

    @Override
    public void añadirDatosPrueba() {

        DireccionPostal direccionPostal1 = new DireccionPostal("aaa", "aaaa", "04009");
        DireccionPostal direccionPostal2 = new DireccionPostal("bbb", "bbbb", "04008");
        Cliente cliente1 = new Cliente("aa", "11111111A", direccionPostal1);
        Cliente cliente2 = new Cliente("bb", "22222222B", direccionPostal2);
        añadirCliente(cliente1);
        añadirCliente(cliente2);
        DatosTecnicosVehiculo datosTecnicosVehiculo1 = new DatosTecnicosVehiculo(2000, 4, 1000);
        DatosTecnicosVehiculo datosTecnicosVehiculo2 = new DatosTecnicosVehiculo(1000, 4, 1000);
        TipoVehiculo tipoVehiculo1 = TipoVehiculo.TURISMO;
        Vehiculo vehiculo1 = tipoVehiculo1.getInstancia("1111AAA", "Citroen", "C4", datosTecnicosVehiculo2);
        añadirVehiculo(vehiculo1);
        TipoVehiculo tipoVehiculo2 = TipoVehiculo.DE_CARGA;
        Vehiculo vehiculo2 = tipoVehiculo2.getInstancia("1111SSS", "Renault", "Expres", datosTecnicosVehiculo2);
        añadirVehiculo(vehiculo2);
        TipoVehiculo tipoVehiculo3 = TipoVehiculo.AUTOBUS;
        Vehiculo vehiculo3 = tipoVehiculo3.getInstancia("2222AAA", "Volvo", "V8", datosTecnicosVehiculo2);
        añadirVehiculo(vehiculo3);
        abrirAlquiler(cliente1, buscarVehiculo("2222AAA"));

    }
}
