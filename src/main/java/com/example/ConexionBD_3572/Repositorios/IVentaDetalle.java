/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Repositorios;





import com.example.ConexionBD_3572.Clases.VentaDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface IVentaDetalle extends CrudRepository<VentaDetalle, Integer>{
    @Query (value ="SELECT * FROM ventadetalle "
    + "WHERE venta_id = ?1 ", nativeQuery=true)
    public List<VentaDetalle> FindByIdVenta (int id);
    
    @Query (value ="SELECT IFNULL (SUM(cantidad),0) as cantidad "
            +"FROM ventadetalle "
            + "WHERE producto_id = ?1 ", nativeQuery=true)
    public int NumberSales(int id);
    
    @Query (value ="SELECT IFNULL (SUM(total),0) as total "
            +"FROM ventadetalle "
            + "WHERE producto_id = ?1 ", nativeQuery=true)
    public Double PriceSales(int id);
    
    @Query (value="SELECT SUM(total) FROM ventadetalle WHERE venta_id=?1",
           nativeQuery=true)
    public Double CantidadTotalPorDetalleV(int id);
    
    @Query (value="SELECT SUM(vd.total) AS total_ventas FROM ventadetalle vd INNER JOIN venta v ON vd.venta_id = v.id WHERE v.estado = 'Activo'",
            nativeQuery=true)
    public Double CantidadTotalDVentas();
    
    @Query (value="SELECT DATE_FORMAT(v.fecha, '%Y-%m-%d %H:%i:%s') as fecha, p.nombre as producto, vd.cantidad, vd.precio, (vd.cantidad * vd.precio) as total "  
           + "FROM venta v INNER JOIN ventadetalle vd ON v.id = vd.venta_id "  
           + "INNER JOIN producto p ON vd.producto_id = p.id " 
           + "WHERE v.estado = 'Activo'", nativeQuery=true) 
    public List ListarVentaEstadoActivo();
    
    @Query (value="SELECT CONCAT(c.apellidos, ' ', c.nombres) AS nombre_completo, SUM(vd.total) AS total_ganancias "  
           + "FROM cliente c JOIN venta v ON c.id = v.cliente_id INNER JOIN ventadetalle vd ON v.id = vd.venta_id "  
           + "WHERE v.estado = 'Activo' GROUP BY nombre_completo " 
           + "ORDER BY total_ganancias DESC LIMIT 5", nativeQuery=true) 
    public List CantidadTotalClientesMasCompras();
    
    @Query (value="SELECT p.id, p.codigo, p.nombre, p.precio, SUM(vd.cantidad) AS total_vendido FROM producto p "  
           + "INNER JOIN ventadetalle vd ON p.id = vd.producto_id JOIN venta v ON vd.venta_id = v.id  "  
           + "WHERE v.estado = 'Activo' GROUP BY p.id, p.codigo, p.nombre, p.precio " 
           + "ORDER BY total_vendido DESC LIMIT 5", nativeQuery=true) 
    public List ProductosMasVendidos();
    
    @Query (value="SELECT mp.idmp, mp.nombremp, COUNT(v.id) AS total_ventas FROM mediosdpago mp "  
           + "INNER JOIN venta v ON mp.idmp = v.mediopago_id WHERE v.estado = 'Activo'  "  
           + "GROUP BY mp.idmp, mp.nombremp ORDER BY total_ventas DESC " 
           + "LIMIT 5", nativeQuery=true) 
    public List MediosdPagoMasUsado();
    
    @Query (value="SELECT p.idp, p.ruc, p.razonsocial, SUM(cd.total) AS total_compras FROM proveedor p  "  
           + "INNER JOIN compra c ON p.idp = c.proveedor_id INNER JOIN compradetalle cd ON c.id = cd.compra_id "  
           + "WHERE c.estado = 'Activo' GROUP BY p.idp, p.ruc, p.razonsocial " 
           + "ORDER BY total_compras DESC LIMIT 5", nativeQuery=true) 
    public List ProveedorMasAbastecimiento();
    

}
