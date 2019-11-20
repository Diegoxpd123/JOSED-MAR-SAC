package pe.edu.upc.spring.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.repository.IServicioDAO;
import pe.edu.upc.spring.service.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioDAO dPet;

	@Override
	@Transactional
	public boolean insertar(Servicio existencia) {
		Servicio objPet = dPet.save(existencia);
		if (objPet == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Servicio pet) {
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
	public Optional<Servicio> listarId(int idPet) {
		return dPet.findById(idPet);		
	}

	
	@Override
	public List<Servicio> listar() {

		return dPet.findAll();

	}

	@Override
	public List<Servicio> buscarCliente(String nameRace) {
		// TODO Auto-generated method stub
		return dPet.buscarCliente(nameRace);
	}

	@Override
	public List<Servicio> buscarAdministrador(String nameRace) {
		// TODO Auto-generated method stub
		return dPet.buscarAdministrador(nameRace);
	}
	@Override
	public List<Servicio> buscarServicio(String namePet) {
		// TODO Auto-generated method stub
		return dPet.buscarServicio(namePet);
	}

	
	

}
