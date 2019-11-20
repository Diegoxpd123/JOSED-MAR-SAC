package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Factura;

public interface IFacturaService {

	public boolean insertar(Factura existencia);
	public boolean modificar(Factura existencia);
	public void eliminar(int id);
	public Optional<Factura> listarId(int id);
	List<Factura> listar();
	List<Factura> buscarServicio(String nombre);
	List<Factura> buscarFactura(String codigo);

	
}