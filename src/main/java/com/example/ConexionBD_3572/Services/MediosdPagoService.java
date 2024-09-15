package com.example.ConexionBD_3572.Services;

import com.example.ConexionBD_3572.Intefaces.IMediosdPagoService;
import com.example.ConexionBD_3572.Clases.MediosdPago;
import com.example.ConexionBD_3572.Repositorios.IMediosdPago;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediosdPagoService implements IMediosdPagoService{
    
    @Autowired
    private IMediosdPago data;
    
    @Override
    public List<MediosdPago> Listar() {
        return (List<MediosdPago>) data.findAll();
    }

    @Override
    public Optional<MediosdPago> ConsultarId(int idmp) {
        return data.findById(idmp);
    }

    @Override
    public void Guardar(MediosdPago mp) {
        data.save(mp);
    }

    @Override
    public void Eliminar(int idmp) {
        data.deleteById(idmp);
    }

    @Override
    public List<MediosdPago> BuscarALL(String desc) {
        return data.buscarPorTodo(desc);
    }
}