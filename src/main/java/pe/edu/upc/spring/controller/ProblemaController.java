package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;
import java.util.List;


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

import pe.edu.upc.spring.model.Problema;
import pe.edu.upc.spring.service.IProblemaService;

@Controller
@RequestMapping("/problema")
public class ProblemaController {
	
	@Autowired
	private IProblemaService rService;
	
	@RequestMapping("/bienvenido")
	public String irProblemaBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irRace(Map<String, Object> model) {
		model.put("listaProblemas", rService.listar());
		return "problemas/listProblema";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("problema", new Problema());
		return "problemas/problema";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Problema objProducto, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "race";
		}
		else {
			
			boolean flag = rService.insertar(objProducto);
			if (flag) {
				return "redirect:/problema/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/problema/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Problema objProducto, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/race/listar";
		}
		else {
			boolean flag = rService.modificar(objProducto);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/problema/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/problema/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Problema> objProducto= rService.listarId(id);
		if (objProducto == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/problema/listar";
		}
		else {
			model.addAttribute("problema", objProducto);
			return "problemas/problema";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaProblemas", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un Problema asignado");
			model.put("listaProblemas", rService.listar());
		}
		return "problemas/listProblema";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaProblemas", rService.listar());
		return "problemas/listProblema";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Problema producto) throws ParseException {
		rService.listarId(producto.getId());
		return "problemas/listProblema";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Problema producto) throws ParseException {
		List<Problema> listaProductores;
		producto.setNombre(producto.getNombre());
		listaProductores = rService.buscarNombre(producto.getNombre());
		
		if (listaProductores.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaProblemas", listaProductores);
		return "problemas/buscarProblema";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("problema", new Problema());
		return "problemas/buscarProblema";
	}
		
}



