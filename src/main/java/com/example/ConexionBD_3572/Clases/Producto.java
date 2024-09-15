package com.example.ConexionBD_3572.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto {
    @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
   private String codigo;
   private String nombre;
   private Double precio;
   private Double precioV;
   private int stock;
}
