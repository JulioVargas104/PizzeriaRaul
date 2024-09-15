/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Repositorios;


import com.example.ConexionBD_3572.Clases.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVenta extends CrudRepository<Venta, Integer>{
    
    @Query (value ="SELECT * FROM venta "
    + "ORDER BY id DESC "  
    + "LIMIT 1 ", nativeQuery=true)
    public int ConsultarIdVenta();
    
    @Query (value ="SELECT v.id, "
    + "v.cliente_id, "  
    + "v.mediopago_id, " 
    + "v.tipocomprobante_id," 
    + "v.fecha, "
    + "v.estado, "
    + "v.usuario_id, " 
    + "c.id as id_c, " 
    + "mp.idmp as id_mp, " 
    + "tc.idtc as id_tc, " 
    + "c.nombres, "
    + "c.apellidos, "
    + "mp.nombremp, "        
    + "tc.nombre "        
    + "FROM venta v "        
    + "INNER JOIN cliente c ON v.cliente_id=c.id "        
    + "INNER JOIN mediosdpago mp ON v.mediopago_id = mp.idmp "  
    + "INNER JOIN usuario usu ON v.usuario_id = usu.id " 
    + "INNER JOIN tcomprobante tc ON v.tipocomprobante_id = tc.idtc "     
    + "WHERE c.nombres LIKE %?1% "  
    + "OR c.apellidos LIKE %?1% "  
    + "OR mp.nombremp LIKE %?1% " 
    + "OR v.estado LIKE %?1% " 
    + "OR v.id LIKE %?1% " 
    + "OR usu.nombre LIKE %?1% "
    + "OR tc.nombre LIKE %?1% " , nativeQuery=true) 
    List<Venta> findForAll (String desc);
    
    
    
   
    
}
