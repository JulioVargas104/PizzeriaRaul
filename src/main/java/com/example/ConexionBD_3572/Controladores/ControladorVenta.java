/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Controladores;




import com.example.ConexionBD_3572.Clases.Cliente;
import com.example.ConexionBD_3572.Clases.MediosdPago;
import com.example.ConexionBD_3572.Clases.Producto;
import com.example.ConexionBD_3572.Clases.TiposComprobante;
import com.example.ConexionBD_3572.Clases.Usuario;
import com.example.ConexionBD_3572.Clases.Venta;
import com.example.ConexionBD_3572.Clases.VentaDetalle;
import com.example.ConexionBD_3572.Intefaces.IClienteService;
import com.example.ConexionBD_3572.Intefaces.ICompraDetalleService;
import com.example.ConexionBD_3572.Intefaces.IMediosdPagoService;
import com.example.ConexionBD_3572.Intefaces.IProductoService;
import com.example.ConexionBD_3572.Intefaces.ITiposComprobanteService;
import com.example.ConexionBD_3572.Intefaces.IUsuarioService;
import com.example.ConexionBD_3572.Intefaces.IVentaDetalleService;
import com.example.ConexionBD_3572.Intefaces.IVentaService;
import com.example.ventas_esca.Clases.Carrito;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/venta")
@Controller
public class ControladorVenta {
    String carpeta="Venta/";
    
    @Autowired
    private IVentaService service;
    
    @Autowired
    private IVentaDetalleService service_vd;
    
    @Autowired
    private ICompraDetalleService service_cd;
    
    @Autowired
    private IClienteService service_cliente;
    
    @Autowired
    private ITiposComprobanteService service_tc;
    
    @Autowired
    private IMediosdPagoService service_mp;
    
    @Autowired
    private IProductoService service_producto;
    
    @Autowired
    private IUsuarioService service_usuario;
    
    ArrayList <Carrito> carrito_venta_lista = new ArrayList();
    
    @GetMapping("/nuevo")//localhost
    public String Nuevo(Model model) {

        List<Cliente> clientes = service_cliente.Listar();
        List<TiposComprobante> tipocomprobantes= service_tc.Listar();
        List<MediosdPago>mediopagos=service_mp.Listar();
        List<Producto>productos= service_producto.Listar();
        
        
        model.addAttribute("clientes", clientes);
        model.addAttribute("tipocomprobantes", tipocomprobantes);
        model.addAttribute("mediopagos", mediopagos);
        model.addAttribute("productos", productos);
        model.addAttribute("carrito", carrito_venta_lista);
        
        return carpeta+"nuevoVenta";
        
                
    }
    
    @PostMapping("/agregar")//localhost/registrar
    public String AgregarCarrito(@RequestParam ("producto_id") int producto_id,
                                    @RequestParam ("cant") int cant,
            Model model)
    {
        Optional<Producto> producto=service_producto.ConsultarId(producto_id);
        String nombre= producto.get().getNombre();
        Double precio=producto.get().getPrecioV();
        Double total =cant*precio;
        
        if(cant<=producto.get().getStock()){
            Carrito carrito = new Carrito();
            carrito.setId(producto_id);
            carrito.setProducto(nombre);
            carrito.setPrecio(precio);
            carrito.setCantidad(cant);
            carrito.setTotal(total);
            carrito_venta_lista.add(carrito);
        }else{
            model.addAttribute("errorStock", 1);
        }

        return Nuevo(model);
    }
    
    @GetMapping ("/eliminarcarrito")
    public String EliminarCarrito (@RequestParam("cod") int id,
            Model model)
    {
        carrito_venta_lista.remove(id-1);
        return Nuevo(model);
    }
    
    @PostMapping("/buscar")
     public String Buscar(@RequestParam("desc") String desc, Model model)
     {
         List<Venta> ventas=service.Buscar(desc);
         model.addAttribute("ventas", ventas);
         return carpeta+"listaVenta";
     }
     
     public int Stock (int id){
         int ingresos=0;
         int salidas=0;
         int stock=0;
         ingresos = service_cd.CantidadCompraProducto(id);
         salidas=service_vd.CantidadVentasProducto(id);
         stock=ingresos-salidas;
         
         return stock;
     }

    @PostMapping("/registrar")//localhost/registrar
    public String RegistrarVenta(
                                   @RequestParam("cliente_id") Cliente cliente_id,
                                   @RequestParam("tipocomprobante_id") TiposComprobante tipocomprobante_id,
                                   @RequestParam("mediopago_id") MediosdPago mediopago_id,
                                   @RequestParam("fec") String fec,                                 
                                   Model model) throws ParseException {
        //Model permite enviar datos desde el controlador a la vista
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String nombreUsuario = auth.getName();
        
        String[] parts =fec.split("T");
        String part1 = parts[0];
        String part2 = parts[1];
        String fec_ = part1+" "+part2+":00";
        
        SimpleDateFormat formateador_fecha = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date fecha = formateador_fecha.parse(fec_);
        
        
        Usuario usuario_id = service_usuario.encontrarUsuario(nombreUsuario).get();
        
        
        //Aqui será  el proceso para registrar
        Venta venta=new Venta();
        //c.setId(cod);
        venta.setEstado("Activo");
        venta.setCliente(cliente_id);
        venta.setMediopago(mediopago_id);
        venta.setTipocomprobante(tipocomprobante_id);
        venta.setFecha(fecha);
        venta.setUsuario(usuario_id);
        service.Guardar(venta);
        
        int id_venta = service.UltimoIdVenta();
        Venta v = new Venta();
        v.setId(id_venta);
        
        for (int i=0; i<carrito_venta_lista.size(); i++){
            int id_producto = carrito_venta_lista.get(i).getId();
            Producto p = new Producto();
            Optional<Producto> producto=service_producto.ConsultarId(id_producto);
            //p.setId(id_producto);
            p=producto.get();
            int cant= carrito_venta_lista.get(i).getCantidad();
            Double prec =carrito_venta_lista.get(i).getPrecio();
            Double total =carrito_venta_lista.get(i).getTotal();
            
            p.setStock(p.getStock()-cant);
            service_producto.Guardar(p);

            
            VentaDetalle vd = new VentaDetalle();
            vd.setVenta(v);
            vd.setProducto(p);
            vd.setCantidad(cant);
            vd.setPrecio(prec);
            vd.setTotal(total);
            
            service_vd.Guardar(vd);
        }
        carrito_venta_lista.clear();
        model.addAttribute("ventas", service.Listar());
        return carpeta+"listaVenta"; //listaVenta.html
    }
    
    @GetMapping("/")//localhost
    public String listarVenta(Model model)
    {
        /*List<Venta> venta = service.Listar(); 
        ArrayList<Venta> venta2 = new ArrayList();
        
        for (int i = 0; i< venta.size(); i++) {
            if(venta.get(i).getEstado()==1){
                venta2.add(venta.get(i));
            }
        }*/
        //model.addAttribute("ventas", venta2);
        model.addAttribute("ventas", service.Listar());
        return carpeta+"listaVenta"; //listaVenta.html
    }
    
    @GetMapping("/detalle")//localhost
    public String listarDetalleVenta(@RequestParam("venta_id") int id, Model model)
    {
        
        double total=service_vd.CantidadTotalPorDetalleV(id);
        model.addAttribute("totalpordetalleV", total);
        
        
        List<VentaDetalle> vd = service_vd.BuscarPorIdVenta(id);
        model.addAttribute("detalleventas", vd);
        return carpeta+"listaDetalleVenta"; //listaVenta.html
    }
    
    
    @GetMapping("/eliminar")
    public String eliminarVenta(@RequestParam("id") int id, Model model){
        List<VentaDetalle> vd = service_vd.BuscarPorIdVenta(id);
        
        for (int i = 0; i < vd.size(); i++) {
            int id_vd=vd.get(i).getId();
            service_vd.Eliminar(id_vd);
        }
        
        service.Eliminar(id);
        return listarVenta(model);
    }
    @GetMapping("/anular")//localhost
    public String anularVenta(@RequestParam("venta_id") int id, Model model)
    {
        Optional <Venta> venta = service.ConsultarId(id);
        venta.get().setEstado("Anulado");
        service.Guardar(venta.get());
        model.addAttribute("ventas", service.Listar());
        return carpeta+"listaVenta"; //listaVenta.html
    }
    
    @PostMapping("/buscarVenta")
    public String buscarVenta(@RequestParam("descrip") String descrip, Model model){
        List<Venta> ventas=service.Buscar(descrip);
        model.addAttribute("ventas", ventas);
        return carpeta+"listaVenta";

    }
 
    

}
