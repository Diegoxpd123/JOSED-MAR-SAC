package pe.edu.upc.spring.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Factura;
import pe.edu.upc.spring.repository.IFacturaDAO;
import pe.edu.upc.spring.service.IFacturaService;
@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private IFacturaDAO dPet;

	@Override
	@Transactional
	public boolean insertar(Factura existencia) {
		Factura objPet = dPet.save(existencia);
		if (objPet == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Factura pet) {
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
	public Optional<Factura> listarId(int idPet) {
		return dPet.findById(idPet);		
	}

	
	@Override
	public List<Factura> listar() {

		return dPet.findAll();

	}

	@Override
	public List<Factura> buscarServicio(String nameRace) {
		// TODO Auto-generated method stub
		return dPet.buscarServicio(nameRace);
	}

	@Override
	public List<Factura> buscarFactura(String namePet) {
		// TODO Auto-generated method stub
		return dPet.buscarFactura(namePet);
	}

	
	

}
