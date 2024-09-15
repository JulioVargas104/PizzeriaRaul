/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas_esca.Clases;

import lombok.Data;

@Data
public class Carrito {
    private int id;
    private String producto;
    private Double precio;
    private int cantidad;
    private Double total;
    
    
}
