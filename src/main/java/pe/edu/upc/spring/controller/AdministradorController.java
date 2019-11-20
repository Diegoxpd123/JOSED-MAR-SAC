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

import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.service.IAdministradorService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired
	private IAdministradorService rService;
	
	@RequestMapping("/bienvenido")
	public String irAdministradorBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irRace(Map<String, Object> model) {
		model.put("listaAdministradores", rService.listar());
		return "administradores/listAdministrador";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "administradores/administrador";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Administrador objAdministrador, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "race";
		}
		else {
			
			boolean flag = rService.insertar(objAdministrador);
			if (flag) {
				return "redirect:/administrador/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/administrador/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Administrador objAdministrador, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/race/listar";
		}
		else {
			boolean flag = rService.modificar(objAdministrador);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/administrador/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/administrador/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Administrador> objAdministrador= rService.listarId(id);
		if (objAdministrador == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/administrador/listar";
		}
		else {
			model.addAttribute("administrador", objAdministrador);
			return "administradores/administrador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaAdministradores", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un administrador asignado");
			model.put("listaAdministradores", rService.listar());
		}
		return "administradores/listAdministrador";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAdministradores", rService.listar());
		return "administradores/listAdministrador";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Administrador administrador) throws ParseException {
		rService.listarId(administrador.getId());
		return "administradores/listAdministrador";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Administrador administrador) throws ParseException {
		List<Administrador> listaAdministradores;
		administrador.setNombre(administrador.getNombre());
		listaAdministradores = rService.buscarNombre(administrador.getNombre());
		
		if (listaAdministradores.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaAdministradores", listaAdministradores);
		return "administradores/buscarAdministrador";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "administradores/buscarAdministrador";
	}
		
}



