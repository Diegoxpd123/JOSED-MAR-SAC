package pe.edu.upc.spring.controller;


import java.text.ParseException;
import java.util.Map;

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

import pe.edu.upc.spring.model.Existencia;
import pe.edu.upc.spring.service.IProductoService;
import pe.edu.upc.spring.model.Producto;
import pe.edu.upc.spring.service.IExistenciaService;


@Controller
@RequestMapping("/existencia")
public class ExistenciaController {
	
	@Autowired
	private IProductoService rService;
	
	@Autowired
	private IExistenciaService pService;
	
	

	
	
	@RequestMapping("/")
	public String irExistencia(Map<String, Object> model) {
		model.put("listaExistencias", pService.listar());
		return "existencias/listExistencias";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("existencia", new Existencia());
		model.addAttribute("listaProductores", rService.listar());
		return "existencias/existencia";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Existencia objExistencia, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaProductores", rService.listar());

			return "existencias/existencia";
		}
		else {
		
			Optional<Producto> ob= rService.listarId(objExistencia.getProducto().getId());
			ob.get().setStock(ob.get().getStock()+1);
			rService.modificar(ob.get());
			 boolean flag = pService.insertar(objExistencia);

			
			if (flag) {
				return "redirect:/existencia/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/existencia/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Existencia objExistencia, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/existencia/listar";
		}
		else {
			
			boolean flag = pService.modificar(objExistencia);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/existencia/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/existencia/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Existencia> objExistencia = pService.listarId(id);
						
		if (objExistencia == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/existencia/listar";
		}
		else {
			
						
			model.addAttribute("listaProductores", rService.listar());
			if (objExistencia.isPresent())
				objExistencia.ifPresent(o -> model.addAttribute("existencia", o));			

			return "existencias/existencia";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		if (id!=null && id>0) {
			Optional<Existencia> objExistencia=pService.listarId(id);
			
			Optional<Producto> ob= rService.listarId(objExistencia.get().getProducto().getId());
			ob.get().setStock(ob.get().getStock()-1);
			rService.modificar(ob.get());
			pService.eliminar(id);
			model.put("listaExistencias", pService.listar());
		}
		return "existencias/listExistencias";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaExistencias", pService.listar());
		return "existencias/listExistencias";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Existencia existencia) throws ParseException {
		pService.listarId(existencia.getId());
		return "existencias/listExistencias";		
	}
	
}

