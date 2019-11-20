package pe.edu.upc.spring.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Existencia;
import pe.edu.upc.spring.repository.IExistenciaDAO;
import pe.edu.upc.spring.service.IExistenciaService;
@Service
public class ExistenciasServiceImpl implements IExistenciaService {

	@Autowired
	private IExistenciaDAO dPet;

	@Override
	@Transactional
	public boolean insertar(Existencia existencia) {
		Existencia objPet = dPet.save(existencia);
		if (objPet == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Existencia pet) {
		boolean flag = false;
		try {
			dPet.save(pet);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPet) {

		dPet.deleteById(idPet);

	}

	@Override
	public Optional<Existencia> listarId(int idPet) {
		return dPet.findById(idPet);		
	}

	
	@Override
	public List<Existencia> listar() {

		return dPet.findAll();

	}

	@Override
	public List<Existencia> buscarProducto(String nameRace) {
		// TODO Auto-generated method stub
		return dPet.buscarProducto(nameRace);
	}

	@Override
	public List<Existencia> buscarExistencia(String namePet) {
		// TODO Auto-generated method stub
		return dPet.buscarExistencia(namePet);
	}

	
	

}
