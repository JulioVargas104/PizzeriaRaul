package com.example.ConexionBD_3572.Controladores;

import com.example.ConexionBD_3572.Clases.Cliente;
import com.example.ConexionBD_3572.Intefaces.IClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/cliente/") //localhost/cliente/
@Controller
public class ClienteControlador {
    
    String carpeta="Clte/";
    
    @Autowired
    private IClienteService service;

    @GetMapping("/") //localhost/cliente/
    public String Mostrar(Model model)
    {
       List<Cliente> clientes = service.Listar();
       model.addAttribute("clientes", clientes);
       return carpeta+"listaClientes"; //listaClientes.html
    }
    
    @GetMapping("/nuevo")
    public String Nuevo(){
        
        
        return carpeta+"nuevoCliente";
    }
    
    @PostMapping("/registrar")
    public String Registrar(@RequestParam ("nom") String nom,
                            @RequestParam ("ape") String ape,
                            @RequestParam ("dni") String dni,
                            @RequestParam ("cel") String cel,
                            @RequestParam ("email") String email,
                            @RequestParam ("dir") String dir,
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        Cliente c = new Cliente();
        
        c.setNombres(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
        service.Guardar(c);
        
        return Mostrar(model);
    }
    
    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam ("id") int id,
                            Model model){
        service.Eliminar(id);
        
        return Mostrar(model);
    }
    
    @GetMapping("/editar")
    public String Editar(@RequestParam ("id") int id,
                            Model model){
        
        Optional<Cliente> cliente = service.ConsultarId(id);
        model.addAttribute("cliente", cliente);
        return carpeta+"editarCliente";
    }
    
    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam ("id") int id,
                            @RequestParam ("nombres") String nom,
                            @RequestParam ("apellidos") String ape,
                            @RequestParam ("dni") String dni,
                            @RequestParam ("celular") String cel,
                            @RequestParam ("email") String email,
                            @RequestParam ("direccion") String dir,
                            Model model){
        //Aqui tiene que ir el proceso de registrar
        Cliente c = new Cliente();
        c.setId(id);
        c.setNombres(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
        service.Guardar(c);//Cuando mandas el id -> Actualiza
        
        return Mostrar(model);
    }
    
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("desc") String desc,
                        Model model)
    {
        List<Cliente> clientes = service.BuscarALL(desc);
        model.addAttribute("clientes", clientes);
        return carpeta+"listaClientes";
    }
}
