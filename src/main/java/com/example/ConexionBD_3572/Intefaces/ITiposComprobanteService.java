package com.example.ConexionBD_3572.Intefaces;

import com.example.ConexionBD_3572.Clases.TiposComprobante;
import java.util.List;
import java.util.Optional;

public interface ITiposComprobanteService {
    public List<TiposComprobante> Listar();
    public Optional<TiposComprobante> ConsultarId(int idtc);
    public void Guardar(TiposComprobante tc);
    public void Eliminar(int idtc);
    public List<TiposComprobante> BuscarALL (String desc);
}