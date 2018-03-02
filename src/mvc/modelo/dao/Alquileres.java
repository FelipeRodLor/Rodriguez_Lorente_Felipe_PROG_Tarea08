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
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipillo
 */
public class Alquileres {

    private final int MAX_ALQUILERES = 3;
    private Alquiler[] alquileres;
    private final String FICHERO_ALQUILERES = "datos/Alquileres.dat";

    public Alquileres() {
        alquileres = new Alquiler[MAX_ALQUILERES];
    }

    public Alquiler[] getAlquiler() {
        return alquileres.clone();
    }

    public void leerAlquileres() {
        File fichero = new File(FICHERO_ALQUILERES);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                alquileres = (Alquiler[]) entrada.readObject();
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
    public void escribirAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			salida.writeObject((Alquiler[]) alquileres);
			salida.close();
			System.out.println("Fichero clientes escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de clientes");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
    
    public void abrir(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean disponible = false;

        if (vehiculo.getDisponible()) {
            while (posicion < alquileres.length && !disponible) {
                
                if (alquileres[posicion] == null) {
                    disponible = true;
               
                } else {
                    posicion++;
                }
            }
            
        } else {
            throw new ExcepcionAlquilerVehiculos("El vehiculo no esta disponible");
        }
        
        if (disponible) {
            alquileres[posicion] = new Alquiler(cliente, vehiculo);
            vehiculo.setDisponible(false);

        } else {
            throw new ExcepcionAlquilerVehiculos("El registro de alquileres esta lleno. Se deben eliminar registros");
        }
    }

    public void cerrar(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean existe = false;

        while (posicion < alquileres.length && !existe) {
            if (alquileres[posicion] != null && alquileres[posicion].getCliente().getDni().equals(cliente.getDni()) && alquileres[posicion].getVehiculo().getMatricula().equals(vehiculo.getMatricula()) && alquileres[posicion].getDias() == 0) {
                existe = true;

            } else {
                posicion++;
            }
        }
        if (existe) {

            alquileres[posicion].close();
            vehiculo.setDisponible(true);

        } else {
            throw new ExcepcionAlquilerVehiculos("El alquiler que se desea cerrar no existe");
        }
    }
}