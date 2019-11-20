package pe.edu.upc.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Existencia;

@Repository
public interface IExistenciaDAO extends JpaRepository<Existencia, Integer>
{
	@Query("from Existencia p where p.producto.nombre like %:nombre%")
	List<Existencia> buscarProducto(@Param("nombre") String nombre);
	
	@Query("from Existencia p where p.codigo like %:codigo%")
	List<Existencia> buscarExistencia(@Param("codigo") String codigo);
	

	
}
