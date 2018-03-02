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
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String dni;
    private int identificador;
    public DireccionPostal direccionPostal;
    private static int ultimoIdentificador;

    public Cliente(String nombre, String dni, DireccionPostal direccionPostal) {

        setNombre(nombre);
        setDni(dni);
        asignarNuevoIdentificador();
        this.direccionPostal = direccionPostal;

    }

    public Cliente(Cliente cliente) {
        nombre = cliente.getNombre();
        dni = cliente.getDni();
        identificador = cliente.getIdentificador();
        direccionPostal = cliente.getDireccionPostal();

    }
    
   private void asignarNuevoIdentificador() {

        ultimoIdentificador++;
        identificador = ultimoIdentificador;

    }
   
   public static void aumentarUltimoIdentificador(int cantidad) {
		if (cantidad >= 0)
			ultimoIdentificador += cantidad;
		else
			throw new ExcepcionAlquilerVehiculos("Sólo puedo aumentar el último identificador");
	}
   
    private void setNombre(String nombre) {
        if (nombre != null && !nombre.equals("")) {
            this.nombre = nombre;
        } else {
            throw new ExcepcionAlquilerVehiculos("El nombre introducido no es correcto");
        }
    }

    private void setDni(String dni) {
        if (compruebaDni(dni)) {
            this.dni = dni;
        } else {
            throw new ExcepcionAlquilerVehiculos("El DNI introducido no es correcto");
        }
    }

 

    private boolean compruebaDni(String dni) {
        Pattern patron = Pattern.compile("([0-9]){8}([a-zA-Z]){1}");
        Matcher emparejador;
        emparejador = patron.matcher(dni);

        return emparejador.matches();

    }

    public void setDireccionPostal(DireccionPostal direccionPostal) {
        this.direccionPostal = new DireccionPostal(direccionPostal);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public DireccionPostal getDireccionPostal() {
        return new DireccionPostal(direccionPostal);
    }

    public int getIdentificador() {
        return identificador;

    }

    public String toString() {

        return ("DNI; " + dni + " NOMBRE; " + nombre + direccionPostal.toString() + " ID; " + identificador);
    }

}