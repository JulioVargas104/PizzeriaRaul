/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Services;


import com.example.ConexionBD_3572.Clases.Compra;
import com.example.ConexionBD_3572.Intefaces.ICompraService;
import com.example.ConexionBD_3572.Repositorios.ICompra;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompraService implements ICompraService{
    @Autowired
    private ICompra data;

    @Override
    public List<Compra> Listar() {
        return (List<Compra>) data.findAll();
    }

    @Override
    public Optional<Compra> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Compra c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Compra> Buscar(String desc) {
        return data.findForAll(desc);
    }

    @Override
    public int UltimoIdCompra() {
        return data.ConsultarIdCompra();
    }
}
