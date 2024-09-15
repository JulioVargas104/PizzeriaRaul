/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Intefaces;


import com.example.ConexionBD_3572.Clases.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author UPN
 */
public interface IUsuarioService {
    public List<Usuario> Listar();
    public Optional<Usuario> ConsultarId(int id);
    public void Guardar(Usuario u);
    public void Eliminar (int id);
    public List<Usuario> Buscar(String desc);
    public Optional<Usuario> encontrarUsuario(String username);
}
