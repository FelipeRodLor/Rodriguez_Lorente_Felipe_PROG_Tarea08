/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvc.modelo.dominio;

import java.io.Serializable;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Felipillo
 *
 */

public class Alquiler implements Serializable{
    private Date fecha;
    private int dias;
    private final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy HH;mm");
    private final int MS_DIA = 1000 * 60 * 60 * 24;
    private final double PRECIO_DIA = 30;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Alquiler(Cliente cliente, Vehiculo turismo) {
        this.cliente = cliente;
        this.vehiculo = turismo;
        fecha = new Date();
        dias = 0;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getDias() {
        return dias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void close() {
        Date ahora = new Date();
        dias = difDias(ahora, fecha);
        vehiculo.setDisponible(true);
    }

    private int difDias(Date fechaFin, Date fechaInicio) {
        long milisegundos = fechaFin.getTime() - fechaInicio.getTime();
        long diasAlq = milisegundos / MS_DIA;

        return (int) diasAlq + 1;
    }

    public double getPrecio() {
        return PRECIO_DIA * dias + vehiculo.getDatosTecnicos().getCilindrada()/ 100;
    }

    public String toString() {
        return ("FECHA DE ALQUILER; " + FORMATO_FECHA.format(fecha) + " CLIENTE; " + cliente + " VEHICULO ALQUILADO; " + vehiculo);
    }
}
