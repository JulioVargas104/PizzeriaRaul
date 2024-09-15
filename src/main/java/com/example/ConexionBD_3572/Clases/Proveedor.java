package com.example.ConexionBD_3572.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="proveedor")
public class Proveedor {
    @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int idp;
   private String razonsocial;
   private String RUC;
   private String celular;
   private String email;
   private String direccion;
}
