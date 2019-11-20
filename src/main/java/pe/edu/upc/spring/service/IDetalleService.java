package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Detalle;

public interface IDetalleService {

	public boolean insertar(Detalle existencia);
	public boolean modificar(Detalle existencia);
	public void eliminar(int id);
	public Optional<Detalle> listarId(int id);
	List<Detalle> listar();
	
	List<Detalle> buscarDetalle(int codigo);

	
}