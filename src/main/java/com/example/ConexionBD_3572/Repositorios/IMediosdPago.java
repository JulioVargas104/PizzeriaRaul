package com.example.ConexionBD_3572.Repositorios;

import com.example.ConexionBD_3572.Clases.MediosdPago;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediosdPago extends CrudRepository<MediosdPago,Integer>{
    
        @Query (value="SELECT * FROM mediosdpago "
            + "WHERE descripcionmp LIKE %?1% "
            + "OR nombremp LIKE %?1% ",nativeQuery=true)
    List<MediosdPago> buscarPorTodo(String desc);
}
