package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Producto;

public interface IProductoService {

	public boolean insertar(Producto producto);
	public boolean modificar(Producto producto);
	public void eliminar(int id);
	public Optional<Producto> listarId(int id);
	List<Producto> listar();
	List<Producto> buscarNombre(String nombre);
	
}