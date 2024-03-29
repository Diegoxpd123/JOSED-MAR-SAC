package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Cliente;

public interface IClienteService {

	public boolean insertar(Cliente cliente);
	public boolean modificar(Cliente cliente);
	public void eliminar(int id);
	public Optional<Cliente> listarId(int id);
	List<Cliente> listar();
	List<Cliente> buscarNombre(String nombre);
	
}