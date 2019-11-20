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

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.service.IClienteService;
import pe.edu.upc.spring.model.Cliente;
import pe.edu.upc.spring.service.IAdministradorService;
import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.service.IServicioService;


@Controller
@RequestMapping("/servicio")
public class ServicioController {
	
	@Autowired
	private IClienteService cService;
	
	@Autowired
	private IAdministradorService aService;
	
	@Autowired
	private IServicioService sService;
	
	

	
	
	@RequestMapping("/")
	public String irServicio(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "servicios/listServicios";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("listaClientes", cService.listar());
		model.addAttribute("listaAdministradores", aService.listar());
		return "servicios/servicio";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Servicio objExistencia, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaClientes", cService.listar());
			model.addAttribute("listaAdministradores", aService.listar());

			return "servicios/servicio";
		}
		else {
			objExistencia.setEstado("Nuevo");
		
			Optional<Cliente> ob= cService.listarId(objExistencia.getCliente().getId());
			ob.get().setN_compras(ob.get().getN_compras()+1);
			cService.modificar(ob.get());
			Optional<Administrador> oa= aService.listarId(objExistencia.getAdministrador().getId());
			oa.get().setVentas(oa.get().getVentas()+1);
			aService.modificar(oa.get());
			 boolean flag = sService.insertar(objExistencia);

			
			if (flag) {
				return "redirect:/servicio/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/servicio/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Servicio objExistencia, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/servicio/listar";
		}
		else {
			
			boolean flag = sService.modificar(objExistencia);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/servicio/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/servicio/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Servicio> objExistencia = sService.listarId(id);
						
		if (objExistencia == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/servicio/listar";
		}
		else {
			
						
			model.addAttribute("listaClientes", cService.listar());
			model.addAttribute("listaAdministradores", aService.listar());
			if (objExistencia.isPresent())
				objExistencia.ifPresent(o -> model.addAttribute("servicio", o));			

			return "servicios/servicio";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		if (id!=null && id>0) {
			sService.eliminar(id);
			model.put("listaServicios", sService.listar());
		}
		return "servicios/listServicios";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "servicios/listServicios";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Servicio existencia) throws ParseException {
		sService.listarId(existencia.getId());
		return "servicios/listServicios";		
	}
	
}

