package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Race;
import pe.edu.upc.spring.service.IRaceService;

@Controller
@RequestMapping("/race")
public class RaceController {
	
	@Autowired
	private IRaceService rService;
	
	@RequestMapping("/bienvenido")
	public String irRaceBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irRace(Map<String, Object> model) {
		model.put("listaRazas", rService.listar());
		return "listRace";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("race", new Race());
		return "race";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Race objRace, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "race";
		}
		else {
			boolean flag = rService.insertar(objRace);
			if (flag) {
				return "redirect:/race/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/race/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Race objRace, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/race/listar";
		}
		else {
			boolean flag = rService.modificar(objRace);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/race/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/race/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Race> objRace = rService.listarId(id);
		if (objRace == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/race/listar";
		}
		else {
			model.addAttribute("race", objRace);
			return "race";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRazas", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar una raza asignada");
			model.put("listaRazas", rService.listar());
		}
		return "listRace";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRazas", rService.listar());
		return "listRace";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Race race) throws ParseException {
		rService.listarId(race.getIdRace());
		return "listRace";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Race race) throws ParseException {
		List<Race> listaRazas;
		race.setNameRace(race.getNameRace());
		listaRazas = rService.buscarNombre(race.getNameRace());
		
		if (listaRazas.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaRazas", listaRazas);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("race", new Race());
		return "buscar";
	}
		
}



