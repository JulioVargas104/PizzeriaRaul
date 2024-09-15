
package com.example.ConexionBD_3572.Repositorios;

import com.example.ConexionBD_3572.Clases.TiposComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiposComprobante extends CrudRepository<TiposComprobante,Integer> {
    
    @Query (value="SELECT * FROM tcomprobante "
            + "WHERE descripciontc LIKE %?1% "
            + "OR nombre LIKE %?1% ",nativeQuery=true)
    List<TiposComprobante> buscarPorTodo(String desc);
}
