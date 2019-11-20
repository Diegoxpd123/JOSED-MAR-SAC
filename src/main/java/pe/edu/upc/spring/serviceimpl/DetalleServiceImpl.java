package pe.edu.upc.spring.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Detalle;
import pe.edu.upc.spring.repository.IDetalleDAO;
import pe.edu.upc.spring.service.IDetalleService;
@Service
public class DetalleServiceImpl implements IDetalleService {

	@Autowired
	private IDetalleDAO dPet;

	@Override
	@Transactional
	public boolean insertar(Detalle existencia) {
		Detalle objPet = dPet.save(existencia);
		if (objPet == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Detalle pet) {
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
	public Optional<Detalle> listarId(int idPet) {
		return dPet.findById(idPet);		
	}

	
	@Override
	public List<Detalle> listar() {

		return dPet.findAll();

	}

	

	@Override
	public List<Detalle> buscarDetalle(int namePet) {
		// TODO Auto-generated method stub
		return dPet.buscarDetalle(namePet);
	}

	
	

}
