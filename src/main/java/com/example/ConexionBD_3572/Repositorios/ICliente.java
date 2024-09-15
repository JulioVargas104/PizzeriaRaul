
package com.example.ConexionBD_3572.Repositorios;

import com.example.ConexionBD_3572.Clases.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends CrudRepository<Cliente,Integer> {
    
    //Aqui pueden ir consultas a BD adicionales
    @Query (value="SELECT * FROM cliente "
            + "WHERE nombres LIKE %?1% "
            + "OR apellidos LIKE %?1% "
            + "OR dni LIKE %?1% "
            + "OR celular LIKE %?1% "
            + "OR email LIKE %?1% "
            + "OR direccion LIKE %?1% ",nativeQuery=true)
    List<Cliente> buscarPorTodo(String desc);
    
}
