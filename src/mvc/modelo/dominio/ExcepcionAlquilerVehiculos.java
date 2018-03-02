/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio;

/**
 *
 * @author Felipillo
 */
public class ExcepcionAlquilerVehiculos extends RuntimeException {
    
    private static final long serialVersionUID=1;

   
    public ExcepcionAlquilerVehiculos (String mensaje){
   
    
    
    super (mensaje);
    }
    
    
}
