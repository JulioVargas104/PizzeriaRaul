/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Intefaces;


import com.example.ConexionBD_3572.Clases.VentaDetalle;
import java.util.List;
import java.util.Optional;


public interface IVentaDetalleService {
    public List<VentaDetalle> Listar();
    public Optional<VentaDetalle> ConsultarId(int id);
    public void Guardar(VentaDetalle vd);
    public void Eliminar (int id);
    public List<VentaDetalle> BuscarPorIdVenta(int id);
    public int CantidadVentasProducto (int id);
    public double CantidadTotalPorDetalleV(int id);
    public double CantidadTotalDVentas();
    public List CantidadTotalClientesMasCompras();
    public List ProductosMasVendidos();
    public List MediosdPagoMasUsado();
    public List ProveedorMasAbastecimiento();
    public List ListarVentaEstadoActivo();
}
