/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Intefaces;


import com.example.ConexionBD_3572.Clases.CompraDetalle;
import java.util.List;
import java.util.Optional;


public interface ICompraDetalleService {
    public List<CompraDetalle> Listar();
    public Optional<CompraDetalle> ConsultarId(int id);
    public void Guardar(CompraDetalle cd);
    public void Eliminar (int id);
    public List<CompraDetalle> BuscarPorIdCompra(int id);
    public int CantidadCompraProducto (int id);
    public double CantidadTotalPorDetalle(int id);
    public double CantidadTotalDCompras();
    public List ListarCompraEstadoActivo();
}
