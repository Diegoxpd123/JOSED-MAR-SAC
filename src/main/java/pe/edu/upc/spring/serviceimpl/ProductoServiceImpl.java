package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Producto;
import pe.edu.upc.spring.repository.IProductoDAO;
import pe.edu.upc.spring.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDAO dProducto;

	@Override
	@Transactional
	public boolean insertar(Producto producto) {
		Producto objProducto = dProducto.save(producto);
		if (objProducto == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Producto producto) {
		boolean flag = false;
		try {
			dProducto.save(producto);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idProducto) {

		dProducto.deleteById(idProducto);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Producto> listarId(int idProducto) {
		return dProducto.findById(idProducto);
	
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> buscarNombre(String nombre) {

		return dProducto.buscarNombre(nombre);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> listar() {
		return dProducto.findAll();
	}

}
