/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ConexionBD_3572.Repositorios;

import com.example.ConexionBD_3572.Clases.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author UPN
 */
public interface IUsuario extends CrudRepository<Usuario, Integer>{
    @Query (value ="SELECT * FROM usuario "
    + "WHERE nombre LIKE %?1% "
    + "OR apellido LIKE %?1% "
    + "OR dni LIKE %?1% "
    + "OR celular LIKE %?1% "
    + "OR email LIKE %?1% "
    + "OR direccion LIKE %?1% "
    + "OR password LIKE %?1% "
    + "OR user LIKE %?1% ", nativeQuery=true)
    List<Usuario> findForAll (String desc);
    
    public Optional <Usuario> findByUser(String username);
    
    
    @Query (value ="SELECT * FROM usuario "
    + "WHERE user= ?1 ", nativeQuery=true)
     public Optional <Usuario>  encontrarUsuario (String username);

}
