package com.example.ConexionBD_3572.Controladores;

import com.example.ConexionBD_3572.Clases.Proveedor;
import com.example.ConexionBD_3572.Intefaces.IProveedorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/proveedor/") //localhost/cliente/
@Controller
public class ProveedorControlador {
    
    String carpeta="Proveedor/";
    
    @Autowired
    private IProveedorService service;

    @GetMapping("/") //localhost/cliente/
    public String Mostrar(Model model)
    {
       List<Proveedor> proveedores = service.Listar();
       model.addAttribute("proveedores", proveedores);
       return carpeta+"listaProveedores"; //listaClientes.html
    }
    
    @GetMapping("/nuevoP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Nuevo(){
        
        
        return carpeta+"nuevoProveedor";
    }
    
    @PostMapping("/registrarP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Registrar(@RequestParam ("raz") String raz, //razonsocial
                            @RequestParam ("ruc") String ruc, //ruc
                            @RequestParam ("cel") String cel,//celular
                            
                            @RequestParam ("email") String email,
                            @RequestParam ("dir") String dir,//direccion
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        Proveedor pr = new Proveedor();
        
        //c.setNombres(nom);
        pr.setRazonsocial(raz);
        pr.setRUC(ruc);
        pr.setCelular(cel);
        pr.setEmail(email);
        pr.setDireccion(dir);
        
        
        service.Guardar(pr);
        
        return Mostrar(model);
    }
    
    @GetMapping("/eliminarP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Eliminar(@RequestParam ("idp") int idp,
                            Model model){
        service.Eliminar(idp);
        
        return Mostrar(model);
    }
    
    @GetMapping("/editarP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Editar(@RequestParam ("idp") int idp,
                            Model model){
        
        Optional<Proveedor> proveedor = service.ConsultarId(idp);
       
       model.addAttribute("proveedor", proveedor);
        return carpeta+"editarProveedor";
    }
    
    @PostMapping("/actualizarP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Actualizar(@RequestParam ("idp") int idp,
                            @RequestParam ("razonsocial") String raz,
                            @RequestParam ("RUC") String ruc,
                             
                            @RequestParam ("celular") String cel,
                            @RequestParam ("email") String email,
                            @RequestParam ("direccion") String dir,
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        //Cliente c = new Cliente();
        Proveedor pr= new Proveedor();
        pr.setIdp(idp);
        pr.setRazonsocial(raz);
        pr.setRUC(ruc);
        pr.setCelular(cel);
        pr.setEmail(email);
        pr.setDireccion(dir);
        
        
        service.Guardar(pr);//Cuando mandas el id -> Actualiza
        
        return Mostrar(model);
    }
    @PostMapping("/buscarP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Buscar(@RequestParam("desc") String desc,
                        Model model)
    {
        List<Proveedor> proveedores = service.BuscarALL(desc);
        model.addAttribute("proveedores", proveedores);
        return carpeta+"listaProveedores";
    }
    
}