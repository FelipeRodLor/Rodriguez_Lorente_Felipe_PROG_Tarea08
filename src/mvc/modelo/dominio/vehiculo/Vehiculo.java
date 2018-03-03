/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author Felipillo
 *
 */
public abstract class Vehiculo implements Serializable {

    private String matricula;
    private String marca;
    private String modelo;
    private DatosTecnicosVehiculo datosTecnicos;
    private boolean disponible;

    public final double FACTOR_CILINDRADA = 50;
    public final double FACTOR_NUMERO_PLAZAS = 1;
    public final double FACTOR_PMA = 20;

    public Vehiculo(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicosVehiculo) {
        setMatricula(matricula);
        setMarca(marca);
        setModelo(modelo);
        this.datosTecnicos = datosTecnicosVehiculo;
        disponible = true;

    }

    public abstract TipoVehiculo getTipoVehiculo();

    public abstract double getPrecioEspecifico();

    public void setMatricula(String matricula) {

        if (compruebaMatricula(matricula)) {
            this.matricula = matricula;

        } else {
            throw new ExcepcionAlquilerVehiculos("La matricula introducida no es correcta");
        }
    }

    private boolean compruebaMatricula(String matricula) {
        Pattern patron = Pattern.compile("([0-9]{4})([a-zA-Z]{3})");
        Matcher emparejador = patron.matcher(matricula);

        return emparejador.matches();
    }

    private void setMarca(String marca) {

        if (marca != null && !marca.equals("")) {
            this.marca = marca;

        } else {
            throw new ExcepcionAlquilerVehiculos("La marca  introducida no es correcta");
        }
    }

    private void setModelo(String modelo) {

        if (modelo != null && !modelo.equals("")) {
            this.modelo = modelo;

        } else {
            throw new ExcepcionAlquilerVehiculos("El modelo introducido no es correcto");
        }
    }

    public Vehiculo(Vehiculo vehiculo) {
        matricula = vehiculo.getMatricula();
        marca = vehiculo.getMarca();
        modelo = vehiculo.getModelo();
        datosTecnicos = vehiculo.getDatosTecnicos();
        this.disponible = vehiculo.disponible;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public DatosTecnicosVehiculo getDatosTecnicos() {
        return datosTecnicos;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return ("MATRICULA; " + matricula + " MARCA; " + marca + " MODELO; " + modelo + datosTecnicos + " DISPONIBLE; " + disponible);
    }

}
