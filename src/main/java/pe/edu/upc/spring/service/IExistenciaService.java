package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Existencia;

public interface IExistenciaService {

	public boolean insertar(Existencia existencia);
	public boolean modificar(Existencia existencia);
	public void eliminar(int id);
	public Optional<Existencia> listarId(int id);
	List<Existencia> listar();
	List<Existencia> buscarProducto(String nombre);
	List<Existencia> buscarExistencia(String codigo);

	
}