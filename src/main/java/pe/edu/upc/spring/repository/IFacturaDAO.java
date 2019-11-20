package pe.edu.upc.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Factura;

@Repository
public interface IFacturaDAO extends JpaRepository<Factura, Integer>
{
	@Query("from Factura p where p.servicio.estado like %:estado%")
	List<Factura> buscarServicio(@Param("estado") String estado);
	
	@Query("from Factura p where p.estado like %:estado%")
	List<Factura> buscarFactura(@Param("estado") String estado);
	

	
}
