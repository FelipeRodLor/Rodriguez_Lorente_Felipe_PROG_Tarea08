/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Felipillo
 */
public class DireccionPostal implements Serializable{

    private String calle;
    private String localidad;
    private String codigoPostal;

    public DireccionPostal(String calle, String localidad, String codigoPostal) {

        setCalle(calle);
        setLocalidad(localidad);
        setCodigoPostal(codigoPostal);

    }

    public DireccionPostal(DireccionPostal direccionPostal) {
        this.calle = direccionPostal.getCalle();
        this.codigoPostal = direccionPostal.getCodigoPostal();
        this.localidad = direccionPostal.getLocalidad();
    }

    private void setCalle(String calle) {

        if (calle.length() > 0) {
            this.calle = calle;
        } else {
            throw new ExcepcionAlquilerVehiculos("La calle introducida no es correcta");
        }

    }

    private void setLocalidad(String localidad) {

        if (localidad.length() > 0) {
            this.localidad = localidad;
        } else {
            throw new ExcepcionAlquilerVehiculos("La localidad introducida no es correcta");
        }
    }

    private void setCodigoPostal(String codigoPostal) {

        if (compruebaCodigoPostal(codigoPostal)) {
            this.codigoPostal = codigoPostal;
        } else {
            throw new ExcepcionAlquilerVehiculos("El codigo postal introducido no es correcto");
        }
    }

    private boolean compruebaCodigoPostal(String codigoPostal) {
        boolean comprobacion = false;
        Pattern patron = Pattern.compile("([0-9]){5}");
        Matcher emparejador;
        emparejador = patron.matcher(codigoPostal);
        if (emparejador.matches()) {
            comprobacion = true;
        }
        return comprobacion;
    }

    public String getCalle() {
        return calle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String toString() {
        return (" CALLE; " + calle + " LOCALIDAD; " + localidad + " CODIGO POSTAL; " + codigoPostal);

    }
}
