package com.example.ConexionBD_3572.Intefaces;

import com.example.ConexionBD_3572.Clases.MediosdPago;
import java.util.List;
import java.util.Optional;

public interface IMediosdPagoService {
    public List<MediosdPago> Listar();
    public Optional<MediosdPago> ConsultarId(int idmp);
    public void Guardar(MediosdPago mp);
    public void Eliminar(int idmp);
    public List<MediosdPago> BuscarALL (String desc);
}