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
import java.util.List;
import java.util.Vector;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipillo
 */
public class Alquileres {

    private List<Alquiler> alquileres;
    private final String FICHERO_ALQUILERES = "datos/Alquileres.dat";

    public Alquileres() {
        alquileres = new Vector<Alquiler>();
    }

    public List<Alquiler> getAlquiler() {
        return new Vector(alquileres);
    }

    public void leerAlquileres() {
        File fichero = new File(FICHERO_ALQUILERES);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    Alquiler alquiler = (Alquiler) entrada.readObject();
                    alquileres.add(alquiler);
                }
            } catch (EOFException eo) {
                entrada.close();
                System.out.println("Fichero alquileres leído satisfactoriamente.");
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de trabajos.");
        }
    }

    public void escribirAlquileres() {
        File fichero = new File(FICHERO_ALQUILERES);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            for (Alquiler alquiler : alquileres) {
                salida.writeObject(alquiler);
            }
            salida.close();
            System.out.println("Fichero alquileres escrito satisfactoriamente.");
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
            alquileres.add(new Alquiler(cliente, vehiculo));
            vehiculo.setDisponible(false);
        } else {
            throw new ExcepcionAlquilerVehiculos("El vehiculo no esta disponible");
        }

    }

    public void cerrar(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean existe = false;

        while (posicion < alquileres.size() && !existe) {
            if (alquileres.get(posicion) != null && alquileres.get(posicion).getCliente().getDni().equals(cliente.getDni()) && alquileres.get(posicion).getVehiculo().getMatricula().equals(vehiculo.getMatricula()) && alquileres.get(posicion).getDias() == 0) {
                existe = true;

            } else {
                posicion++;
            }
        }
        if (existe) {

            alquileres.get(posicion).close();
            vehiculo.setDisponible(true);

        } else {
            throw new ExcepcionAlquilerVehiculos("El alquiler que se desea cerrar no existe");
        }
    }

    public List<Alquiler> obtenerAlquileresAbiertos() {
        int posicion = 0;
        List<Alquiler> alquileresAbiertos = new Vector<Alquiler>();

        while (posicion < alquileres.size()) {
            if (alquileres.get(posicion).getDias() == 0) {
                alquileresAbiertos.add(alquileres.get(posicion));
            }
            posicion++;
        }
        return alquileresAbiertos;

    }

    public List<Alquiler> obtenerAlquileresCliente(String dni) {
        int posicion = 0;
        List<Alquiler> alquileresCliente = new Vector<Alquiler>();

        while (posicion < alquileres.size()) {
            if (alquileres.get(posicion).getCliente().getDni().equals(dni)) {
                alquileresCliente.add(alquileres.get(posicion));
            }
            posicion++;
        }
        return alquileresCliente;

    }
}
