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
public class  Turismo extends Vehiculo{

    public Turismo(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicosVehiculo) {
        super(matricula, marca, modelo, datosTecnicosVehiculo);
    }

    public Turismo(Vehiculo vehiculo) {
        super(vehiculo);
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.TURISMO;
    }

    @Override
    public double getPrecioEspecifico() {
        return getDatosTecnicos().getCilindrada() * FACTOR_CILINDRADA;
    }
    
    
}
