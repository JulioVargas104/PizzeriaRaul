package com.example.ConexionBD_3572.Clases;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="mediosdpago")
public class MediosdPago {
    @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int idmp;
   private String nombremp;
   private String descripcionmp;
    
}