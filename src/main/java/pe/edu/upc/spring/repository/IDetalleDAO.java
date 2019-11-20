package pe.edu.upc.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Detalle;

@Repository
public interface IDetalleDAO extends JpaRepository<Detalle, Integer>
{
    @Query("from Detalle p where p.id like %:id%")
	List<Detalle> buscarDetalle(@Param("id") int id);
	

	
}
