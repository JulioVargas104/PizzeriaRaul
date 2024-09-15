package com.example.ConexionBD_3572.Repositorios;

import com.example.ConexionBD_3572.Clases.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends CrudRepository<Producto,Integer>{
    
        @Query (value="SELECT * FROM producto "
            + "WHERE codigo LIKE %?1% "
            + "OR nombre LIKE %?1% "
            + "OR precio LIKE %?1% "
            + "OR stock LIKE %?1% "
            + "OR precioV LIKE %?1% ",nativeQuery=true)
    List<Producto> buscarPorTodo(String desc);
    
    @Query (value="SELECT * FROM producto WHERE STOCK<10",nativeQuery=true)
    List<Producto> StockMenor10();
}
