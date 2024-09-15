
package com.example.ConexionBD_3572.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table (name="venta")
public class Venta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public Date fecha;
    
    
    @ManyToOne()
    @JoinColumn (name="cliente_id")
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn (name="tipocomprobante_id")
    private TiposComprobante tipocomprobante;
    
    @ManyToOne()
    @JoinColumn (name="mediopago_id")
    private MediosdPago mediopago;
    
    @ManyToOne()
    @JoinColumn (name="usuario_id")
    private Usuario usuario;
    
    public String estado;
}
