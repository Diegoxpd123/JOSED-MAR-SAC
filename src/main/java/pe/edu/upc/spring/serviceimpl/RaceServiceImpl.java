package pe.edu.upc.spring.serviceimpl;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Race;
import pe.edu.upc.spring.repository.IRaceDAO;
import pe.edu.upc.spring.service.IRaceService;

@Service
public class RaceServiceImpl implements IRaceService {
	
	
	@Autowired
	private IRaceDAO dRace;
	
	@Override
	@Transactional
	public boolean insertar(Race race) {
		Race objRace = dRace.save(race);
		if (objRace == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Race race) {
		boolean flag = false;
		try {
			dRace.save(race);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idRace) {
		dRace.deleteById(idRace);
	}
	
	@Override
	@Transactional(readOnly=true)	
	public Optional<Race> listarId(int idRace) {
		return dRace.findById(idRace);
	}
	
	@Override
	@Transactional(readOnly=true)	
	public List<Race> buscarNombre(String nameRace) {	
		return dRace.buscarNombre(nameRace);
	}
	
	@Override
	@Transactional(readOnly=true)	
	public List<Race> listar() {	
		return dRace.findAll();
	}	
	
	
}
