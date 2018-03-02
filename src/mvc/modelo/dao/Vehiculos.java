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
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipillo
 *
 */
public class Vehiculos {

    private final int MAX_VEHICULOS = 10;
    private Vehiculo[] vehiculos;
    private final String FICHERO_VEHICULOS = "datos/Vehiculos.dat";

    public Vehiculos() {
        vehiculos = new Vehiculo[MAX_VEHICULOS];
    }

    public Vehiculo[] getVehiculo() {

        return vehiculos.clone();
    }

    public void leerVehiculos() {
        File fichero = new File(FICHERO_VEHICULOS);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                vehiculos = (Vehiculo[]) entrada.readObject();
                entrada.close();
                System.out.println("Fichero clientes leído satisfactoriamente.");
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de clientes.");
        }
    }
    public void escribirVehiculos() {
		File fichero = new File(FICHERO_VEHICULOS);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			salida.writeObject((Vehiculo[]) vehiculos);
			salida.close();
			System.out.println("Fichero clientes escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de clientes");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

    public void añadir(Vehiculo vehiculo) {
        int indice = buscarPrimerIndiceLibreComprobandoExistencia(vehiculo);

        if (indiceNoSuperaTamaño(indice)) {
            vehiculos[indice] = vehiculo;

        } else {
            throw new ExcepcionAlquilerVehiculos("El array de vehiculos está lleno.");
        }
    }

    private int buscarPrimerIndiceLibreComprobandoExistencia(Vehiculo vehiculo) {
        int indice = 0;
        boolean clienteEncontrado = false;

        while (indiceNoSuperaTamaño(indice) && !clienteEncontrado) {
            if (vehiculos[indice] == null) {
                clienteEncontrado = true;
            } else if (vehiculos[indice].getMatricula().equals(vehiculo.getMatricula())) {
                throw new ExcepcionAlquilerVehiculos("Ya existe un vehiculo con esa matricula");

            } else {
                indice++;
            }
        }
        return indice;
    }

    private boolean indiceNoSuperaTamaño(int indice) {
        return indice < vehiculos.length;
    }

    public void borrar(String matricula) {
        int indice = buscarIndiceVehiculo(matricula);
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private int buscarIndiceVehiculo(String matricula) {
        int indice = 0;
        boolean existe = false;

        while (indiceNoSuperaTamaño(indice) && !existe) {
            if (vehiculos[indice] != null && vehiculos[indice].getMatricula().equals(matricula)) {
                existe = true;

            } else {
                indice++;
            }
        }
        if (existe) {
            return indice;

        } else {
            throw new ExcepcionAlquilerVehiculos("El vehiculo introducido no existe");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int posicion) {

        for (int i = posicion; i < vehiculos.length - 1; i++) {
            vehiculos[i] = vehiculos[i + 1];
        }
        vehiculos[vehiculos.length - 1] = null;
    }

    public Vehiculo buscar(String matricula) {

        return vehiculos[buscarIndiceVehiculo(matricula)];
    }
}
