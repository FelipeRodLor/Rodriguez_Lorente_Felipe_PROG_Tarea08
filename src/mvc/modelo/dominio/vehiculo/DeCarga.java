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
public class DeCarga extends Vehiculo {

    public DeCarga(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicosVehiculo) {
        super(matricula, marca, modelo, datosTecnicosVehiculo);
    }

    public DeCarga(Vehiculo vehiculo) {
        super(vehiculo);
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.DE_CARGA;
    }

    @Override
    public double getPrecioEspecifico() {
        return getDatosTecnicos().getPma() / FACTOR_PMA + FACTOR_NUMERO_PLAZAS * getDatosTecnicos().getNumeroPlazas();

    }

}
