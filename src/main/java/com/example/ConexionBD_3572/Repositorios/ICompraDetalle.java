/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Repositorios;


import com.example.ConexionBD_3572.Clases.CompraDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ICompraDetalle extends CrudRepository<CompraDetalle, Integer>{
    @Query (value ="SELECT * FROM compradetalle "
    + "WHERE compra_id = ?1 ", nativeQuery=true)
    public List<CompraDetalle> FindByIdCompra (int id);
    
    @Query (value ="SELECT IFNULL (SUM(cantidad),0) as cantidad "
            +"FROM compradetalle "
            + "WHERE producto_id = ?1 ", nativeQuery=true)
    public int NumberSales(int id);
    
    @Query (value ="SELECT IFNULL (SUM(total),0) as total "
            +"FROM compradetalle "
            + "WHERE producto_id = ?1 ", nativeQuery=true)
    public Double PriceSales(int id);
    @Query (value="SELECT SUM(total) FROM compradetalle WHERE compra_id=?1",
            nativeQuery=true)
    public Double CantidadTotalPorDetalle(int id);
    @Query (value="SELECT SUM(cd.total) AS total_gastado FROM compradetalle cd INNER JOIN compra c ON cd.compra_id = c.id WHERE c.estado = 'Activo'",
            nativeQuery=true)
    public Double CantidadTotalDCompras();
    
    @Query (value="SELECT DATE_FORMAT(c.fecha, '%Y-%m-%d %H:%i:%s') AS fecha, p.nombre AS producto, cd.cantidad, cd.precio, (cd.cantidad * cd.precio) AS total "  
           + "FROM compra c JOIN compradetalle cd ON c.id = cd.compra_id JOIN producto p ON cd.producto_id = p.id WHERE c.estado = 'Activo'",nativeQuery=true) 
    public List ListarCompraEstadoActivo();
}
