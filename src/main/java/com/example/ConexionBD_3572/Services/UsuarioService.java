/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Services;


import com.example.ConexionBD_3572.Clases.Usuario;
import com.example.ConexionBD_3572.Intefaces.IUsuarioService;
import com.example.ConexionBD_3572.Repositorios.IUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService,IUsuarioService{
    @Autowired
    private IUsuario data;
    
    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }

    @Override
    public Optional<Usuario> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Usuario u) {
        data.save(u);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Usuario> Buscar(String desc) {
        return data.findForAll(desc);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = data.findByUser(username);
        return usuario.map(UsuarioServiceDetalle::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+username));
    }

    @Override
    public Optional<Usuario> encontrarUsuario(String username) {
        return data.encontrarUsuario(username);
    }
    
}
