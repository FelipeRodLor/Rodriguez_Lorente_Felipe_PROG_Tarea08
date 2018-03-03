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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipillo
 *
 */
public class Vehiculos {

    private Map<String, Vehiculo> vehiculos;
    private final String FICHERO_VEHICULOS = "datos/Vehiculos.dat";

    public Vehiculos() {
        vehiculos = new HashMap<String, Vehiculo>();
    }

    public List<Vehiculo> getVehiculo() {
        return new Vector<Vehiculo>(vehiculos.values());
    }

    public void leerVehiculos() {
        File fichero = new File(FICHERO_VEHICULOS);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    Vehiculo vehiculo = (Vehiculo) entrada.readObject();
                    vehiculos.put(vehiculo.getMatricula(), vehiculo);
                }
            } catch (EOFException eo) {
                entrada.close();
                System.out.println("Fichero vehículos leído satisfactoriamente.");
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de vehículos.");
        }
    }

    public void escribirVehiculos() {
        File fichero = new File(FICHERO_VEHICULOS);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            for (Vehiculo vehiculo : vehiculos.values()) {
                salida.writeObject(vehiculo);
            }
            salida.close();
            System.out.println("Fichero vehiculos escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de clientes");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }

    public void añadir(Vehiculo vehiculo) {

        if (vehiculos.containsKey(vehiculo.getMatricula())) {
            throw new ExcepcionAlquilerVehiculos("Ya existe un vehiculo con esa matricula");
        } else {
            vehiculos.put(vehiculo.getMatricula(), vehiculo);
        }
    }

    public void borrar(String matricula) {
        if (vehiculos.containsKey(matricula)) {
            vehiculos.remove(matricula);
        } else {
            throw new ExcepcionAlquilerVehiculos("El vehiculo a eliminar no existe");
        }
    }

    public Vehiculo buscar(String matricula) {
        if (vehiculos.containsKey(matricula)) {
            return vehiculos.get(matricula);
        } else {
            throw new ExcepcionAlquilerVehiculos("El vehiculo introducido no existe");
        }
    }
}
