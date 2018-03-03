/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author Felipillo
 */
public class Clientes {

    private Map<String, Cliente> clientes;
    private final String FICHERO_CLIENTES = "datos/clientes.dat";

    public Clientes() {
        clientes = new HashMap<String, Cliente>();
    }

    public List<Cliente> getClientes() {
        List<Cliente> clientesOrdenados = new Vector<Cliente>(clientes.values());
        Collections.sort(clientesOrdenados, new Comparator<Cliente>() {
            public int compare(Cliente uno, Cliente otro) {
                return uno.getNombre().compareTo(otro.getNombre());
            }
        });
        return clientesOrdenados;
    }

    public void leerClientes() {
        File fichero = new File(FICHERO_CLIENTES);
        ObjectInputStream entrada;

        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    Cliente cliente = (Cliente) entrada.readObject();
                    clientes.put(cliente.getDni(), cliente);
                }
            } catch (EOFException eo) {
                entrada.close();
                System.out.println("Fichero clientes leído satisfactoriamente.");
                Cliente.aumentarUltimoIdentificador(calcularUltimoIdentificador());
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de entrada.");
        }
    }

    private int calcularUltimoIdentificador() {
        int ultimoIdentificador = 0;
        for (Cliente cliente : clientes.values()) {

            if (cliente.getIdentificador() > ultimoIdentificador) {
                ultimoIdentificador = cliente.getIdentificador();
            }
        }
        return ultimoIdentificador;
    }

    public void escribirClientes() {
        File fichero = new File(FICHERO_CLIENTES);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            for (Cliente cliente : clientes.values()) {
                salida.writeObject(cliente);
            }
            salida.close();
            System.out.println("Fichero clientes escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de clientes");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }

    public void añadir(Cliente cliente) {

        if (clientes.containsKey(cliente.getDni())) {
            throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con el mismo DNI");
        } else {
            clientes.put(cliente.getDni(), cliente);
        }
    }

    public void borrar(String dni) {

        if (clientes.containsKey(dni)) {
            clientes.remove(dni);
        } else {
            throw new ExcepcionAlquilerVehiculos("No existe ningun cliente con el dni introducido");
        }
    }

    public Cliente buscar(String dni) {

        if (clientes.containsKey(dni)) {
            return new Cliente(clientes.get(dni));
        } else {
            throw new ExcepcionAlquilerVehiculos("El cliente introducido no existe");

        }

    }
}
