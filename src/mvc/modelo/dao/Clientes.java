/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author Felipillo
 */
public class Clientes {

    private final int MAX_CLIENTES = 3;
    private Cliente[] clientes;
    private final String FICHERO_CLIENTES = "datos/clientes.dat";

    public Clientes() {
        clientes = new Cliente[MAX_CLIENTES];
    }

    public Cliente[] getClientes() {
        return clientes.clone();
    }

    public void leerClientes() {
        File fichero = new File(FICHERO_CLIENTES);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                clientes = (Cliente[]) entrada.readObject();
                entrada.close();
                System.out.println("Fichero clientes leído satisfactoriamente.");
                //Cliente.aumentarUltimoIdentificador(calcularUltimoIdentificador());

            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de clientes.");
        }
        
        
    }

    private int calcularUltimoIdentificador() {
        int ultimoIdentificador = 0;
        int i = 0;
        while (clientes[i] != null) {
            if (clientes[i].getIdentificador() > ultimoIdentificador) {
                ultimoIdentificador = clientes[i].getIdentificador();
            }
        }
        return ultimoIdentificador;
    }

    public void escribirClientes() {
        File fichero = new File(FICHERO_CLIENTES);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            salida.writeObject((Cliente[]) clientes);
            salida.close();
            System.out.println("Fichero clientes escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de clientes");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }

    public void añadir(Cliente cliente) {
        int indice = buscarPrimerIndiceLibreComprobandoExistencia(cliente);
        if (indiceNoSuperaTamano(indice)) {
            clientes[indice] = new Cliente(cliente);
        } else {
            throw new ExcepcionAlquilerVehiculos("El array de clientes está lleno.");
        }
    }

    private int buscarPrimerIndiceLibreComprobandoExistencia(Cliente cliente) {
        int indice = 0;
        boolean clienteEncontrado = false;
        while (indiceNoSuperaTamano(indice) && !clienteEncontrado) {
            if (clientes[indice] == null) {
                clienteEncontrado = true;
            } else if (clientes[indice].getDni().equals(cliente.getDni())) {
                throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con ese DNI");
            } else {
                indice++;
            }
        }
        return indice;
    }

    private boolean indiceNoSuperaTamano(int indice) {
        return indice < clientes.length;
    }

    public void borrar(String dni) {

        int indice = buscarIndiceCliente(dni);
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private int buscarIndiceCliente(String dni) {

        int indice = 0;
        boolean existe = false;
        while (indiceNoSuperaTamano(indice) && !existe) {
            if (clientes[indice] != null && clientes[indice].getDni().equals(dni)) {
                existe = true;
            } else {
                indice++;
            }
        }
        if (existe) {
            return indice;
        } else {
            throw new ExcepcionAlquilerVehiculos("El cliente introducido no existe");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int posicion) {

        for (int i = posicion; i < clientes.length - 1 && clientes[i] != null; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[clientes.length - 1] = null;
    }

    public Cliente buscar(String dni) {

        int posicion = buscarIndiceCliente(dni);
        if (indiceNoSuperaTamano(posicion)) {
            return new Cliente(clientes[posicion]);
        } else {
            return null;
        }
    }

}
