package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Intefaces.IProveedorService;
import com.example.ConexionBD_3572.Clases.Proveedor;
import com.example.ConexionBD_3572.Repositorios.IProveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private IProveedor data;
    
    @Override
    public List<Proveedor> Listar() {
        return (List<Proveedor>) data.findAll();
    }

    @Override
    public Optional<Proveedor> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Proveedor pr) {
        data.save(pr);
    }

    @Override
    public void Eliminar(int idp) {
        data.deleteById(idp);
    }

    @Override
    public List<Proveedor> BuscarALL(String desc) {
        return data.buscarPorTodo(desc);
    }
    
}