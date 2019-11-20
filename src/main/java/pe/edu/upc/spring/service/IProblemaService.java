package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Problema;

public interface IProblemaService {

	public boolean insertar(Problema producto);
	public boolean modificar(Problema producto);
	public void eliminar(int id);
	public Optional<Problema> listarId(int id);
	List<Problema> listar();
	List<Problema> buscarNombre(String nombre);
	
}