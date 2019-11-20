package pe.edu.upc.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Servicio;

@Repository
public interface IServicioDAO extends JpaRepository<Servicio, Integer>
{
	@Query("from Servicio p where p.cliente.nombre like %:nombre%")
	List<Servicio> buscarCliente(@Param("nombre") String nombre);
	
	@Query("from Servicio p where p.administrador.nombre like %:nombre%")
	List<Servicio> buscarAdministrador(@Param("nombre") String nombre);
	
	@Query("from Servicio p where p.estado like %:estado%")
	List<Servicio> buscarServicio(@Param("estado") String estado);
	

	
}
