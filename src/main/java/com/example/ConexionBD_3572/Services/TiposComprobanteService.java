package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Intefaces.ITiposComprobanteService;
import com.example.ConexionBD_3572.Clases.TiposComprobante;
import com.example.ConexionBD_3572.Repositorios.ITiposComprobante;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiposComprobanteService implements ITiposComprobanteService {

    @Autowired
    private ITiposComprobante data;
    
    @Override
    public List<TiposComprobante> Listar() {
        return (List<TiposComprobante>) data.findAll();
    }

    @Override
    public Optional<TiposComprobante> ConsultarId(int idtc) {
        return data.findById(idtc);
    }

    @Override
    public void Guardar(TiposComprobante tc) {
        data.save(tc);
    }

    @Override
    public void Eliminar(int idtc) {
        data.deleteById(idtc);
    }

    @Override
    public List<TiposComprobante> BuscarALL(String desc) {
        return data.buscarPorTodo(desc);
    }
    
}