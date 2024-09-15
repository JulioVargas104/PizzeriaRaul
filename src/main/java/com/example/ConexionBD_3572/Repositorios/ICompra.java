/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Repositorios;



import com.example.ConexionBD_3572.Clases.Compra;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompra extends CrudRepository<Compra, Integer>{
    @Query (value ="SELECT * FROM compra "
    + "ORDER BY id DESC "  
    + "LIMIT 1 ", nativeQuery=true)
    public int ConsultarIdCompra();
    
    
    @Query (value ="SELECT comp.id as id, "
    + "comp.proveedor_id, "  
    + "comp.mediopago_id, " 
    + "comp.tipocomprobante_id, " 
    + "comp.fecha, " 
    + "comp.estado, " 
    + "comp.usuario_id, " 
    + "usu.nombre, " 
    + "pr.idp as id_c, " 
    + "mp.idmp as id_mp, " 
    + "tc.idtc as id_tc, " 
    + "pr.razonsocial, "   
    + "mp.nombremp, "        
    + "tc.nombre "
    + "FROM compra comp "        
    + "INNER JOIN proveedor pr ON comp.proveedor_id = pr.idp "        
    + "INNER JOIN mediosdpago mp ON comp.mediopago_id = mp.idmp "  
    + "INNER JOIN usuario usu ON comp.usuario_id = usu.id " 
    + "INNER JOIN tcomprobante tc ON comp.tipocomprobante_id = tc.idtc "     
    + "WHERE pr.razonsocial LIKE %?1% "     
    + "OR mp.nombremp LIKE %?1% "
    + "OR usu.nombre LIKE %?1% "
    + "OR comp.estado LIKE %?1% "
    + "OR tc.nombre LIKE %?1% " , nativeQuery=true) 
    List<Compra> findForAll (String desc);
}
