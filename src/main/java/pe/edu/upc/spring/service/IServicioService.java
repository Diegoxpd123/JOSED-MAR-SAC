package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Servicio;

public interface IServicioService {

	public boolean insertar(Servicio existencia);
	public boolean modificar(Servicio existencia);
	public void eliminar(int id);
	public Optional<Servicio> listarId(int id);
	List<Servicio> listar();
	List<Servicio> buscarCliente(String nombre);
	List<Servicio> buscarAdministrador(String nombre);
	List<Servicio> buscarServicio(String codigo);

	
}