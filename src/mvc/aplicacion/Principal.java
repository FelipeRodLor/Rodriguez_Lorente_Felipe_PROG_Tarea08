/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.aplicacion;

import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.IModeloAlquilerVehiculos;
import mvc.modelo.ModeloAlquilerVehiculos;
import mvc.vista.IUTextual;
import mvc.vista.IVistaAlquilerVehiculos;

/**
 *
 * @author Felipon
 */
public class Principal {

    public static void main(String[] args) {
        IVistaAlquilerVehiculos vista = new IUTextual();
        IModeloAlquilerVehiculos modelo = new ModeloAlquilerVehiculos();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);
        controlador.comenzar();
    }
}
