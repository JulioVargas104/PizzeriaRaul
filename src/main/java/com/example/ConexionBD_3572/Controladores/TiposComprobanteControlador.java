package com.example.ConexionBD_3572.Controladores;

import com.example.ConexionBD_3572.Clases.TiposComprobante;
import com.example.ConexionBD_3572.Intefaces.ITiposComprobanteService;
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

@RequestMapping("/tiposcomprobante/") //localhost/cliente/
@Controller
public class TiposComprobanteControlador {
    
    String carpeta="TiposComprobantes/";
    
    @Autowired
    private ITiposComprobanteService service;

    @GetMapping("/") //localhost/cliente/
    public String Mostrar(Model model)
    {
       List<TiposComprobante> tiposcomprobante = service.Listar();
       model.addAttribute("tiposcomprobante", tiposcomprobante);
       return carpeta+"listaTiposcomprobante"; //listaClientes.html
    }
    
    @GetMapping("/nuevoTP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Nuevo(){
        
        
        return carpeta+"nuevoTiposcomprobante";
    }
    
    @PostMapping("/registrarTP")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Registrar(@RequestParam ("nom") String nom, //razonsocial
                            @RequestParam ("descripciontc") String descrtc, //ruc
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        TiposComprobante tc = new TiposComprobante();
        
        tc.setDescripciontc(descrtc);
        tc.setNombre(nom);
        
        service.Guardar(tc);
        
        return Mostrar(model);
    }
    
    @GetMapping("/eliminarTC")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Eliminar(@RequestParam ("id") int id,
                            Model model){
        service.Eliminar(id);
        
        return Mostrar(model);
    }
    
    @GetMapping("/editarTC")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Editar(@RequestParam("id") int id,
                            Model model){
        
        Optional<TiposComprobante> tiposcomprobante = service.ConsultarId(id);
       
       model.addAttribute("tiposcomprobante", tiposcomprobante);
        return carpeta+"editarTiposcomprobante";
    }
    
    @PostMapping("/actualizarTC")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Actualizar(@RequestParam ("id") int id,
                            @RequestParam ("nombre") String nom,
                            @RequestParam ("descripciontc") String descrtc,
                             
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        //Cliente c = new Cliente();
        TiposComprobante tc= new TiposComprobante();
        tc.setId(id);
        tc.setDescripciontc(descrtc);
        tc.setNombre(nom);
       
        service.Guardar(tc);//Cuando mandas el id -> Actualiza
        
        return Mostrar(model);
    }
    @PostMapping("/buscar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Buscar(@RequestParam("desc") String desc,
                        Model model)
    {
        List<TiposComprobante> tiposcomprobante = service.BuscarALL(desc);
        model.addAttribute("tiposcomprobante", tiposcomprobante);
        return carpeta+"listaTiposcomprobante";
    }
    
}