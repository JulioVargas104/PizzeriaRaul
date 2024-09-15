/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Controladores;


import com.example.ConexionBD_3572.Clases.Usuario;
import com.example.ConexionBD_3572.Intefaces.IUsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/usuario")
@Controller

public class ControladorUsuario {
     ArrayList<Usuario> listaU=new ArrayList();
    String carpeta="Usuario/";
    
    @Autowired
    IUsuarioService service;
    
    @GetMapping("/nuevoUsuario")//localhost
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String nuevoProducto() {

        return carpeta+"nuevoUsuario";//nuevoProveedor.html
    }
    
    @PostMapping("/RegistrarUsuario")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String RegistrarUsuario(
                                   @RequestParam("nom") String nom,
                                   @RequestParam("ape") String ape,
                                   @RequestParam("dni") String dni,
                                   @RequestParam("email") String email,
                                   @RequestParam("celu") String celu,
                                   @RequestParam("direc") String direc,
                                   @RequestParam("pass") String pass,
                                   @RequestParam("user") String user,
                                   @RequestParam("roles") String roles,
                                   Model model) {
        //Model permite enviar datos desde el controlador a la vista
        //Aqui será el proceso para registrar
        BCryptPasswordEncoder bw = new BCryptPasswordEncoder();
        String pass_encriptado =bw.encode(pass);
        Usuario u=new Usuario();
        //p.setCodigo(codigo);
        u.setNombre(nom);
        u.setApellido(ape);
        u.setDni(dni);
        u.setEmail(email);
        u.setCelular(celu);
        u.setDireccion(direc);
        u.setPassword(pass_encriptado);
        u.setUser(user);
        u.setRoles(roles);
        
        service.Guardar(u);
        
        return listarUsuario(model);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listarUsuario(Model model)
    {
        //model.addAttribute("productos", listaP);
        model.addAttribute("usuarios", service.Listar());
        return carpeta+"listaUsuario"; //listaProducto.html
    }
    
    @GetMapping("/eliminar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String eliminarUsuario(@RequestParam("cod") int cod, Model model){
        /*for (int i = 0; i < listaP.size(); i++) {
            int c=listaP.get(i).getCodigo();
            if(c==cod){
                listaP.remove(i);
            }
        }*/
        service.Eliminar(cod);
        return listarUsuario(model);
        //return listarProducto(model);
    }
    @GetMapping("/editar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editarUsuario(@RequestParam("cod") int cod, Model model){
        /*Producto pro=new Producto();
        for (int i = 0; i < listaP.size(); i++) {
            int c=listaP.get(i).getCodigo();
            if(c==cod){
                
                String nom=listaP.get(i).getNombre();
                double precio=listaP.get(i).getPrecio();
                String descr=listaP.get(i).getDescripcion();
  
                
                pro.setCodigo(cod);
                pro.setNombre(nom);
                pro.setPrecio(precio);
                pro.setDescripcion(descr);

            }
        }*/
        Optional <Usuario> pro = service.ConsultarId(cod);
        model.addAttribute("usuario", pro);
        return carpeta+"editarUsuario";//editarProducto.html
    }
    
    @PostMapping("/actualizar")//localhost/registrar
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String actualizarUsuario(@RequestParam("id") int id,
                                   @RequestParam("nombre") String nom,
                                   @RequestParam("apellido") String ape,
                                   @RequestParam("dni") String dni,
                                   @RequestParam("email") String email,
                                   @RequestParam("celular") String celu,
                                   @RequestParam("direccion") String direc,
                                   @RequestParam("password") String pass,
                                   @RequestParam("user") String user,
                                   @RequestParam("roles") String roles,
                                   Model model){
        //Model permite enviar datos desde el controlador a la vista
        //Aqui será el proceso para registrar
        /*for (int i = 0; i < listaP.size(); i++) {
            int c=listaP.get(i).getCodigo();
            if(c==codigo){
                
                listaP.get(i).setNombre(nombre);
                listaP.get(i).setPrecio(precio);
                listaP.get(i).setDescripcion(descripcion);


            }
        }*/
        BCryptPasswordEncoder bw = new BCryptPasswordEncoder();
        String pass_encriptado =bw.encode(pass);
        Usuario u=new Usuario();
        u.setId(id);
        u.setNombre(nom);
        u.setApellido(ape);
        u.setDni(dni);
        u.setEmail(email);
        u.setCelular(celu);
        u.setDireccion(direc);
        u.setPassword(pass_encriptado);
        u.setUser(user);
        u.setRoles(roles);
        service.Guardar(u); //cuando se envia el id -> actualiza
        
        return listarUsuario(model);
    }
    
    @PostMapping("/buscarUsuario")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String buscarProducto(@RequestParam("descrip") String descrip, Model model){
        List<Usuario> usuarios=service.Buscar(descrip);
        model.addAttribute("usuarios", usuarios);
        return carpeta+"listaUsuario";
    }
}
