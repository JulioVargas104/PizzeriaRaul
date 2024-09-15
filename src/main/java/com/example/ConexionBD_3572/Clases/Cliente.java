package com.example.ConexionBD_3572.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
   private String nombres;
   private String apellidos;
   private String dni;
   private String celular;
   private String email;
   private String direccion;
}
