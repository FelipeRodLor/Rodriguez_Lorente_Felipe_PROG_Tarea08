/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

/**
 *
 * @author Felipon
 */
public class Autobus extends Vehiculo {

    public Autobus(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicosVehiculo) {
        super(matricula, marca, modelo, datosTecnicosVehiculo);
    }

    public Autobus(Vehiculo vehiculo) {
        super(vehiculo);
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.AUTOBUS;
    }

    @Override
    public double getPrecioEspecifico() {
        return getDatosTecnicos().getCilindrada()/ FACTOR_CILINDRADA + FACTOR_NUMERO_PLAZAS * getDatosTecnicos().getNumeroPlazas(); 

    }

}
