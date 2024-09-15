package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Intefaces.IClienteService;
import com.example.ConexionBD_3572.Clases.Cliente;
import com.example.ConexionBD_3572.Repositorios.ICliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ICliente data;
    
    @Override
    public List<Cliente> Listar() {
        return (List<Cliente>) data.findAll();
    }

    @Override
    public Optional<Cliente> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Cliente c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Cliente> BuscarALL(String desc) {
        return data.buscarPorTodo(desc);
    }
    
}
