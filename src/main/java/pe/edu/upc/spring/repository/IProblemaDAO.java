package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Problema;

@Repository
public interface IProblemaDAO extends JpaRepository<Problema, Integer>
{
	@Query("from Problema r where r.nombre like %:nombre%")
	List<Problema> buscarNombre(@Param("nombre") String nombre);
}
