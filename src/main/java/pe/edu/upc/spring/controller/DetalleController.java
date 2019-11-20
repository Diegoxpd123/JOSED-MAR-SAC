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

import pe.edu.upc.spring.model.Detalle;
import pe.edu.upc.spring.model.Existencia;
import pe.edu.upc.spring.model.Factura;
import pe.edu.upc.spring.model.Problema;
import pe.edu.upc.spring.service.IExistenciaService;
import pe.edu.upc.spring.service.IFacturaService;
import pe.edu.upc.spring.service.IProblemaService;
import pe.edu.upc.spring.service.IDetalleService;


@Controller
@RequestMapping("/detalle")
public class DetalleController {
	
	@Autowired
	private IDetalleService dService;
	
	@Autowired
	private IFacturaService faService;
	
	@Autowired
	private IExistenciaService eService;
	
	@Autowired
	private IProblemaService pService;
	
	

	
	
	@RequestMapping("/")
	public String irFactura(Map<String, Object> model) {
		model.put("listaDetlles", dService.listar());
		return "detalles/listDetalles";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("detalle", new Detalle());
		model.addAttribute("listaFacturas", faService.listar());
		model.addAttribute("listaExistencias", eService.listar());
		model.addAttribute("listaProblemas", pService.listar());
		return "detalles/detalle";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Detalle objExistencia, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaFacturas", faService.listar());
			model.addAttribute("listaExistencias", eService.listar());
			model.addAttribute("listaProblemas", pService.listar());

			return "detalles/detalle";
		}
		else {
		
			Optional<Factura> f= faService.listarId(objExistencia.getFactura().getId());
			Optional<Existencia> e= eService.listarId(objExistencia.getExistencia().getId());
			f.get().setMonto(f.get().getMonto()+e.get().getPrecio());
			Optional<Problema> p= pService.listarId(objExistencia.getProblema().getId());
			f.get().setMontoproblemas(f.get().getMontoproblemas()+ p.get().getPrecio());
			faService.modificar(f.get());
	
			 boolean flag = dService.insertar(objExistencia);

			
			if (flag) {
				return "redirect:/detalle/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/detalle/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Detalle objExistencia, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/factura/listar";
		}
		else {
			
			boolean flag = dService.modificar(objExistencia);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/detalle/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/detalle/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Detalle> objExistencia = dService.listarId(id);
						
		if (objExistencia == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/detalle/listar";
		}
		else {
			
						
			model.addAttribute("listaFacturas", faService.listar());
			model.addAttribute("listaExistencias", eService.listar());
			model.addAttribute("listaProblemas", pService.listar());
			if (objExistencia.isPresent())
				objExistencia.ifPresent(o -> model.addAttribute("detalle", o));			

			return "detalles/detalle";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		if (id!=null && id>0) {
			Optional<Detalle> objExistencia=dService.listarId(id);
			Optional<Factura> f= faService.listarId(objExistencia.get().getFactura().getId());
			Optional<Existencia> e= eService.listarId(objExistencia.get().getExistencia().getId());
			f.get().setMonto(f.get().getMonto()-e.get().getPrecio());
			Optional<Problema> p= pService.listarId(objExistencia.get().getProblema().getId());
			f.get().setMontoproblemas(f.get().getMontoproblemas()- p.get().getPrecio());
			faService.modificar(f.get());
			dService.eliminar(id);
			model.put("listaDetalles", dService.listar());
		}
		return "detalles/listDetalles";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDetalles", dService.listar());
		return "detalles/listDetalles";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Detalle existencia) throws ParseException {
		dService.listarId(existencia.getId());
		return "detalles/listDetalles";		
	}
	
}

