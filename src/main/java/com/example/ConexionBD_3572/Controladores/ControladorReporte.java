/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Controladores;

import com.example.ConexionBD_3572.Clases.CompraDetalle;
import com.example.ConexionBD_3572.Clases.Producto;
import com.example.ConexionBD_3572.Clases.VentaDetalle;
import com.example.ConexionBD_3572.Intefaces.ICompraDetalleService;
import com.example.ConexionBD_3572.Intefaces.IProductoService;
import com.example.ConexionBD_3572.Intefaces.IVentaDetalleService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reporte")
@Controller
public class ControladorReporte {
    String carpeta="Reporte/";
     
    @Autowired
    private IVentaDetalleService service_vd;
    
    @Autowired
    private ICompraDetalleService service_cd;
    
    @Autowired
    private IProductoService service_prod;
    
    @GetMapping("/")//localhost
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listarReporte(Model model)
    {
        
        List<VentaDetalle> ventadetalles = service_vd.Listar();
        List<CompraDetalle> compradetalles= service_cd.Listar();
        List<Producto> stockMenor10= service_prod.StockMenor10();
        
        List<Object[]> clienteMasCompras = service_vd.CantidadTotalClientesMasCompras();
        List<Object[]> productosMasVendidos = service_vd.ProductosMasVendidos();
        List<Object[]> mediosdPagoMasUsado = service_vd.MediosdPagoMasUsado();
        List<Object[]> proveedorMasAbastecimiento = service_vd.ProveedorMasAbastecimiento();
        List<Object[]> listaVentaActivo = service_vd.ListarVentaEstadoActivo();
        List<Object[]> listaCompraActivo = service_cd.ListarCompraEstadoActivo();

        double totalVentas=service_vd.CantidadTotalDVentas();
        model.addAttribute("totaldetalleV", totalVentas);
        
        double totalCompras=service_cd.CantidadTotalDCompras();
        model.addAttribute("totaldetalleC", totalCompras);
        
        double ganancia=totalVentas-totalCompras;
        model.addAttribute("ganancia", ganancia);
        model.addAttribute("ventadetalles", ventadetalles);
        model.addAttribute("compradetalles", compradetalles);
        
        model.addAttribute("clienteMasCompras", clienteMasCompras);
        model.addAttribute("productosMasVendidos", productosMasVendidos);
        model.addAttribute("mediosdPagoMasUsado", mediosdPagoMasUsado);
        model.addAttribute("proveedorMasAbastecimiento", proveedorMasAbastecimiento);
        model.addAttribute("listaVentaActivo", listaVentaActivo);
        model.addAttribute("listaCompraActivo", listaCompraActivo);
        
        model.addAttribute("stockMenor10", stockMenor10);
        
        return carpeta+"listaReporte"; //listaVenta.html
    }
}
