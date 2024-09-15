package com.example.ConexionBD_3572.Controladores;

import com.example.ConexionBD_3572.Clases.MediosdPago;
import com.example.ConexionBD_3572.Intefaces.IMediosdPagoService;
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

@RequestMapping("/mediosdpago/") //localhost/producto/
@Controller
public class MediosdPagoControlador {
    
    String carpeta="MediosdPago/";
    
    @Autowired
    private IMediosdPagoService service;

    @GetMapping("/") //localhost/producto/---->añadir otro
    public String Mostrar(Model model)
    {
       List<MediosdPago> mediosdpago = service.Listar();
       model.addAttribute("mediosdpago", mediosdpago);
       return carpeta+"listaMediosdpago"; //listaMediosdpago.html
    }
    
    
    @GetMapping("/nuevomp")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Nuevo(){
        
        
        return carpeta+"nuevoMediosdpago";
    }
    
    @PostMapping("/registrarmp")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Registrar(
                            @RequestParam ("nombmp") String nommp,
                            @RequestParam ("descrmp") String descrmp,
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        MediosdPago mp = new MediosdPago();
        
       
        mp.setNombremp(nommp);
        mp.setDescripcionmp(descrmp);
       
        
        service.Guardar(mp);
        
        return Mostrar(model);
    }
    
    @GetMapping("/eliminarmp")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Eliminar(@RequestParam ("idmp") int idmp,
                            Model model){
        service.Eliminar(idmp);
        
        return Mostrar(model);
    }
    
    @GetMapping("/editarmp")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Editar(@RequestParam ("idmp") int idmp,
                            Model model){
        
        Optional<MediosdPago> mediosdpago = service.ConsultarId(idmp);
        //model.addAttribute("producto", producto);
      model.addAttribute("mediosdpago", mediosdpago);
        return carpeta+"editarMediosdpago";
    }
    
    @PostMapping("/actualizarmp")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Actualizar(@RequestParam ("idmp") int idmp,
                            @RequestParam ("nombremp") String nommp,
                            @RequestParam ("descripcionmp") String descrmp,
                             
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        MediosdPago mp=new MediosdPago();
         
        mp.setIdmp(idmp);
        mp.setNombremp(nommp);
        mp.setDescripcionmp(descrmp);
        
        
        service.Guardar(mp);//Cuando mandas el id -> Actualiza
        
        return Mostrar(model);
    }
    @PostMapping("/buscar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Buscar(@RequestParam("desc") String desc,
                        Model model)
    {
        List<MediosdPago> mpago = service.BuscarALL(desc);
        model.addAttribute("mediosdpago", mpago);
        return carpeta+"listaMediosdpago";
    }
    
}
