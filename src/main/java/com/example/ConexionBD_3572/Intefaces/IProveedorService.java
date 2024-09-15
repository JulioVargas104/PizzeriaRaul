package com.example.ConexionBD_3572.Intefaces;

import com.example.ConexionBD_3572.Clases.Proveedor;
import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    public List<Proveedor> Listar();
    public Optional<Proveedor> ConsultarId(int idp);
    public void Guardar(Proveedor pr);
    public void Eliminar(int idp);
    public List<Proveedor> BuscarALL (String desc);
}