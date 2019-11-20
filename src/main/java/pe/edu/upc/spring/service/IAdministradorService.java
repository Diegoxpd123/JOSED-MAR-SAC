package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Administrador;

public interface IAdministradorService {

	public boolean insertar(Administrador administrador);
	public boolean modificar(Administrador administrador);
	public void eliminar(int id);
	public Optional<Administrador> listarId(int id);
	List<Administrador> listar();
	List<Administrador> buscarNombre(String nombre);
	
}