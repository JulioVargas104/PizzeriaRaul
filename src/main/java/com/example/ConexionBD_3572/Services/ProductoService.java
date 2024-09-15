package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Intefaces.IProductoService;
import com.example.ConexionBD_3572.Clases.Producto;
import com.example.ConexionBD_3572.Repositorios.IProducto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    private IProducto data;
    
    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Producto p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Producto> BuscarALL(String desc) {
        return data.buscarPorTodo(desc);
    }

    @Override
    public List<Producto> StockMenor10() {
       return data.StockMenor10();
    }
}
