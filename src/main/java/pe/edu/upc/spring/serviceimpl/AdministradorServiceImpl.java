package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.repository.IAdministradorDAO;
import pe.edu.upc.spring.service.IAdministradorService;

@Service
public class AdministradorServiceImpl implements IAdministradorService {

	@Autowired
	private IAdministradorDAO dAdministrador;

	@Override
	@Transactional
	public boolean insertar(Administrador administrador) {
		Administrador objAdministrador = dAdministrador.save(administrador);
		if (objAdministrador == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Administrador administrador) {
		boolean flag = false;
		try {
			dAdministrador.save(administrador);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idAdministrador) {

		dAdministrador.deleteById(idAdministrador);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Administrador> listarId(int idAdministrador) {
		return dAdministrador.findById(idAdministrador);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> buscarNombre(String nombre) {

		return dAdministrador.buscarNombre(nombre);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> listar() {
		return dAdministrador.findAll();
	}

}
