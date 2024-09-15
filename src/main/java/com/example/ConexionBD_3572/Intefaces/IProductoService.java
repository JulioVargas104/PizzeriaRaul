package com.example.ConexionBD_3572.Intefaces;

import com.example.ConexionBD_3572.Clases.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    public List<Producto> Listar();
    public Optional<Producto> ConsultarId(int id);
    public void Guardar(Producto p);
    public void Eliminar(int id);
    public List<Producto> BuscarALL (String desc);
    public List<Producto> StockMenor10();
}
