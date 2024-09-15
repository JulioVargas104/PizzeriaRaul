/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Intefaces;



import com.example.ConexionBD_3572.Clases.Venta;
import java.util.List;
import java.util.Optional;

public interface IVentaService {
    
    public List<Venta> Listar();
    public Optional<Venta> ConsultarId(int id);
    public void Guardar(Venta v);
    public void Eliminar (int id);
    public List<Venta> Buscar(String desc);
    public int UltimoIdVenta();
    
}
