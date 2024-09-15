/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Clases.CompraDetalle;
import com.example.ConexionBD_3572.Intefaces.ICompraDetalleService;
import com.example.ConexionBD_3572.Repositorios.ICompraDetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraDetalleService implements ICompraDetalleService{

    @Autowired
    private ICompraDetalle data;
    @Override
    public List<CompraDetalle> Listar() {
        return (List<CompraDetalle>) data.findAll();
    }

    @Override
    public Optional<CompraDetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(CompraDetalle cd) {
        data.save(cd);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<CompraDetalle> BuscarPorIdCompra(int id) {
        return data.FindByIdCompra(id);
    }

    @Override
    public int CantidadCompraProducto(int id) {
        return data.NumberSales(id);
    }

    @Override
    public double CantidadTotalPorDetalle(int id) {
        return data.CantidadTotalPorDetalle(id);
    }

    @Override
    public double CantidadTotalDCompras() {
        return data.CantidadTotalDCompras();
    }

    @Override
    public List ListarCompraEstadoActivo() {
        return data.ListarCompraEstadoActivo();
    }
    
}
