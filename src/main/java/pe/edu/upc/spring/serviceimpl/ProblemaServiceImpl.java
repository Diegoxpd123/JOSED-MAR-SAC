package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Problema;
import pe.edu.upc.spring.repository.IProblemaDAO;
import pe.edu.upc.spring.service.IProblemaService;

@Service
public class ProblemaServiceImpl implements IProblemaService {

	@Autowired
	private IProblemaDAO dProducto;

	@Override
	@Transactional
	public boolean insertar(Problema producto) {
		Problema objProducto = dProducto.save(producto);
		if (objProducto == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Problema producto) {
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
	public Optional<Problema> listarId(int idProducto) {
		return dProducto.findById(idProducto);
	
	}

	@Override
	@Transactional(readOnly=true)
	public List<Problema> buscarNombre(String nombre) {

		return dProducto.buscarNombre(nombre);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Problema> listar() {
		return dProducto.findAll();
	}

}
