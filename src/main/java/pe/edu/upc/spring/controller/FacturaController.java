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

import pe.edu.upc.spring.model.Factura;
import pe.edu.upc.spring.service.IServicioService;

import pe.edu.upc.spring.service.IFacturaService;


@Controller
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	private IServicioService sService;
	
	@Autowired
	private IFacturaService fService;
	
	

	
	
	@RequestMapping("/")
	public String irFactura(Map<String, Object> model) {
		model.put("listaFacturas", fService.listar());
		return "facturas/listFacturas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("factura", new Factura());
		model.addAttribute("listaServicios", sService.listar());
		return "facturas/factura";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Factura objExistencia, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaServicios", sService.listar());

			return "facturas/factura";
		}
		else {
		
			 boolean flag = fService.insertar(objExistencia);

			
			if (flag) {
				return "redirect:/factura/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/factura/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Factura objExistencia, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/factura/listar";
		}
		else {
			
			boolean flag = fService.modificar(objExistencia);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/factura/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/factura/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Factura> objExistencia = fService.listarId(id);
						
		if (objExistencia == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/factura/listar";
		}
		else {
			
						
			model.addAttribute("listaServicios", sService.listar());
			if (objExistencia.isPresent())
				objExistencia.ifPresent(o -> model.addAttribute("factura", o));			

			return "facturas/factura";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		if (id!=null && id>0) {
			fService.eliminar(id);
			model.put("listaFacturas", fService.listar());
		}
		return "facturas/listFacturas";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFacturas", fService.listar());
		return "facturas/listFacturas";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Factura existencia) throws ParseException {
		fService.listarId(existencia.getId());
		return "facturas/listFacturas";		
	}
	
}

