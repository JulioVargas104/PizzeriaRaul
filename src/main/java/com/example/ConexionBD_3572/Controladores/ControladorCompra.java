/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Controladores;


import com.example.ConexionBD_3572.Clases.Compra;
import com.example.ConexionBD_3572.Clases.CompraDetalle;
import com.example.ConexionBD_3572.Clases.MediosdPago;
import com.example.ConexionBD_3572.Clases.Producto;
import com.example.ConexionBD_3572.Clases.Proveedor;
import com.example.ConexionBD_3572.Clases.TiposComprobante;
import com.example.ConexionBD_3572.Clases.Usuario;
import com.example.ConexionBD_3572.Intefaces.ICompraDetalleService;
import com.example.ConexionBD_3572.Intefaces.ICompraService;
import com.example.ConexionBD_3572.Intefaces.IMediosdPagoService;
import com.example.ConexionBD_3572.Intefaces.IProductoService;
import com.example.ConexionBD_3572.Intefaces.IProveedorService;
import com.example.ConexionBD_3572.Intefaces.ITiposComprobanteService;
import com.example.ConexionBD_3572.Intefaces.IUsuarioService;
import com.example.ConexionBD_3572.Intefaces.IVentaDetalleService;
import com.example.ventas_esca.Clases.Carrito;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/compra")
@Controller
public class ControladorCompra {
    String carpeta="Compra/";
    
    @Autowired
    private ICompraService service;
    
    @Autowired
    private IVentaDetalleService service_vd;
    
    @Autowired
    private ICompraDetalleService service_cd;
    

    @Autowired
    private IProveedorService service_proveedor;
    
    @Autowired
    private ITiposComprobanteService service_tc;
    
    @Autowired
    private IMediosdPagoService service_mp;
    
    @Autowired
    private IProductoService service_producto;
    @Autowired
    private IUsuarioService service_usuario;
    
    
    ArrayList <Carrito> carrito_compra_lista = new ArrayList();
    
    @GetMapping("/nuevo")//localhost
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String Nuevo(Model model) {

        List<Proveedor> proveedores = service_proveedor.Listar();
        List<TiposComprobante> tipocomprobantes= service_tc.Listar();
        List<MediosdPago>mediopagos=service_mp.Listar();
        List<Producto>productos= service_producto.Listar();
        
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("tipocomprobantes", tipocomprobantes);
        model.addAttribute("mediopagos", mediopagos);
        model.addAttribute("productos", productos);
        model.addAttribute("carrito", carrito_compra_lista);
        
        return carpeta+"nuevoCompra";
        
                
    }
    
    @PostMapping("/agregar")//localhost/registrar
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String AgregarCarrito(@RequestParam ("producto_id") int producto_id,
                                    @RequestParam ("cant") int cant,
            Model model)
    {
        Optional<Producto> producto=service_producto.ConsultarId(producto_id);
        String nombre= producto.get().getNombre();
        Double precio=producto.get().getPrecio();
        Double total =cant*precio;
        //int stk = Stock(producto_id);
        //if(stk>=cant){
            Carrito carrito = new Carrito();
            carrito.setId(producto_id);
            carrito.setProducto(nombre);
            carrito.setPrecio(precio);
            carrito.setCantidad(cant);
            carrito.setTotal(total);
            carrito_compra_lista.add(carrito);
        //}
        return Nuevo(model);
    }
    
    @GetMapping ("/eliminarcarrito")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String EliminarCarrito (@RequestParam("cod") int id,
            Model model)
    {
        carrito_compra_lista.remove(id-1);
        return Nuevo(model);
    }
    
    @PostMapping("/buscar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     public String Buscar(@RequestParam("desc") String desc, Model model)
     {
         List<Compra> compras=service.Buscar(desc);
         model.addAttribute("compras", compras);
         return carpeta+"listaCompra";
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
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String RegistrarCompra(
                                   @RequestParam("proveedor_id") Proveedor proveedor_id,
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
        Compra compra=new Compra();
        //c.setId(cod);
        compra.setEstado("Activo");
        compra.setProveedor(proveedor_id);
        compra.setMediopago(mediopago_id);
        compra.setTipocomprobante(tipocomprobante_id);
        compra.setFecha(fecha);
        compra.setUsuario(usuario_id);
        service.Guardar(compra);
        
        int id_compra = service.UltimoIdCompra();
        Compra c = new Compra();
        c.setId(id_compra);
        
        for (int i=0; i<carrito_compra_lista.size(); i++){
            int id_producto = carrito_compra_lista.get(i).getId();
            Producto p = new Producto();
            Optional<Producto> producto=service_producto.ConsultarId(id_producto);
            //p.setId(id_producto);
            p=producto.get();
            int cant= carrito_compra_lista.get(i).getCantidad();
            Double prec =carrito_compra_lista.get(i).getPrecio();
            Double total =carrito_compra_lista.get(i).getTotal();
            
            p.setStock(p.getStock()+cant);
            service_producto.Guardar(p);
            
            CompraDetalle cd = new CompraDetalle();
            cd.setCompra(c);
            cd.setProducto(p);
            cd.setCantidad(cant);
            cd.setPrecio(prec);
            cd.setTotal(total);
            
            service_cd.Guardar(cd);
        }
        carrito_compra_lista.clear();
        return listarCompra(model);
    }
    
    @GetMapping("/")//localhost
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listarCompra(Model model)
    {
        model.addAttribute("compras", service.Listar());
        return carpeta+"listaCompra"; //listaVenta.html
    }
    @GetMapping("/detalle")//localhost
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listarDetalleCompra(@RequestParam("compra_id") int id, Model model)
    {
        double total=service_cd.CantidadTotalPorDetalle(id);
        model.addAttribute("totalpordetalle", total);
        
        List<CompraDetalle> vd = service_cd.BuscarPorIdCompra(id);
        model.addAttribute("detallecompras", vd);
        return carpeta+"listaDetalleCompra"; //listaVenta.html
    }
    
    @GetMapping("/eliminar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String eliminarCompra(@RequestParam("id") int id, Model model){
        List<CompraDetalle> cd = service_cd.BuscarPorIdCompra(id);
        
        for (int i = 0; i < cd.size(); i++) {
            int id_cd=cd.get(i).getId();
            service_cd.Eliminar(id_cd);
        }
        
        service.Eliminar(id);
        return listarCompra(model);
    }
    
    @GetMapping("/anular")//localhost
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String anularVenta(@RequestParam("compra_id") int id, Model model)
    {
        Optional <Compra> compra = service.ConsultarId(id);
        compra.get().setEstado("Anulado");
        service.Guardar(compra.get());
        model.addAttribute("compras", service.Listar());
        return carpeta+"listaCompra"; //listaVenta.html
    }

    
    @PostMapping("/buscarCompra")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String buscarCompra(@RequestParam("descrip") String descrip, Model model){
        List<Compra> compras=service.Buscar(descrip);
        model.addAttribute("compras", compras);
        return carpeta+"listaCompra";

    }
}
