package com.example.ConexionBD_3572.Controladores;

import com.example.ConexionBD_3572.Clases.Producto;
import com.example.ConexionBD_3572.Intefaces.IProductoService;
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

@RequestMapping("/producto/") //localhost/producto/
@Controller
public class ProductoControlador {
    String carpeta="Producto/";
    
    @Autowired
    private IProductoService service;

    @GetMapping("/") //localhost/producto/
    public String Mostrar(Model model)
    {
       List<Producto> productos = service.Listar();
       model.addAttribute("productos", productos);
       return carpeta+"listaProductos"; //listaProductos.html
    }
    
    
    @GetMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Nuevo(){
        
        
        return carpeta+"nuevoProducto";
    }
    
    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Registrar(@RequestParam ("cod") String cod,
                            @RequestParam ("nom") String nom,
                            @RequestParam ("pre") Double pre,
                            @RequestParam ("preV") Double preV,
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        Producto p = new Producto();
        
        p.setCodigo(cod);
        p.setNombre(nom);
        p.setPrecio(pre);
        p.setPrecioV(preV);
        p.setStock(0);
        
        service.Guardar(p);
        
        return Mostrar(model);
    }
    
    @GetMapping("/eliminar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Eliminar(@RequestParam ("id") int id,
                            Model model){
        service.Eliminar(id);
        
        return Mostrar(model);
    }
    
    @GetMapping("/editar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Editar(@RequestParam ("id") int id,
                            Model model){
        
        Optional<Producto> producto = service.ConsultarId(id);
        model.addAttribute("producto", producto);
        return carpeta+"editarProducto";
    }
    
    @PostMapping("/actualizar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Actualizar(@RequestParam ("id") int id,
                            @RequestParam ("codigo") String cod,
                            @RequestParam ("nombre") String name,
                            @RequestParam ("precio") Double pre,
                            @RequestParam ("precioV") Double preV,
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        Producto p = new Producto();
        p.setId(id);
        p.setCodigo(cod);
        p.setNombre(name);
        p.setPrecio(pre);
        p.setPrecioV(preV);
        
        service.Guardar(p);//Cuando mandas el id -> Actualiza
        
        return Mostrar(model);
    }
    
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("desc") String desc,
                        Model model)
    {
        List<Producto> productos = service.BuscarALL(desc);
        model.addAttribute("productos", productos);
        return carpeta+"listaProductos";
    }
    
}
