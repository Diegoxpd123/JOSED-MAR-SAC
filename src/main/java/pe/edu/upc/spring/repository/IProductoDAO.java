package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Producto;

@Repository
public interface IProductoDAO extends JpaRepository<Producto, Integer>
{
	@Query("from Producto r where r.nombre like %:nombre%")
	List<Producto> buscarNombre(@Param("nombre") String nombre);
}
