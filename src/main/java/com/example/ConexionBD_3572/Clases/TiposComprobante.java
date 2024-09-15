package com.example.ConexionBD_3572.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tcomprobante")
public class TiposComprobante {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="idtc")
   private int id;
   private String nombre;
   private String descripciontc;
   
}