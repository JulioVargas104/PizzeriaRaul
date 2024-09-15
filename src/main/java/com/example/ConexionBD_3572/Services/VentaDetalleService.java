
package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Clases.VentaDetalle;
import com.example.ConexionBD_3572.Intefaces.IVentaDetalleService;
import com.example.ConexionBD_3572.Repositorios.IVentaDetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDetalleService implements IVentaDetalleService{
    @Autowired
    private IVentaDetalle data;
    
    @Override
    public List<VentaDetalle> Listar() {
        return (List<VentaDetalle>) data.findAll();
    }

    @Override
    public Optional<VentaDetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(VentaDetalle vd) {
        data.save(vd);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }


    @Override
    public int CantidadVentasProducto(int id) {
        return data.NumberSales(id);
    }

    @Override
    public List<VentaDetalle> BuscarPorIdVenta(int id) {
        return data.FindByIdVenta(id);
    }

    @Override
    public double CantidadTotalPorDetalleV(int id) {
        return data.CantidadTotalPorDetalleV(id);
    }

    @Override
    public double CantidadTotalDVentas() {
        return data.CantidadTotalDVentas();
    }

    @Override
    public List CantidadTotalClientesMasCompras() {
       return data.CantidadTotalClientesMasCompras();
    }

    @Override
    public List ProductosMasVendidos() {
        return data.ProductosMasVendidos();
    }

    @Override
    public List MediosdPagoMasUsado() {
        return data.MediosdPagoMasUsado();
    }

    @Override
    public List ProveedorMasAbastecimiento() {
        return data.ProveedorMasAbastecimiento();
    }

    @Override
    public List ListarVentaEstadoActivo() {
        return data.ListarVentaEstadoActivo();
    }
    
}
