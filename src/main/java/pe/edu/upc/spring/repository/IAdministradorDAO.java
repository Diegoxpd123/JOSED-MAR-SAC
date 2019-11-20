package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Administrador;

@Repository
public interface IAdministradorDAO extends JpaRepository<Administrador, Integer>
{
	@Query("from Administrador r where r.nombre like %:nombre%")
	List<Administrador> buscarNombre(@Param("nombre") String nombre);
}
