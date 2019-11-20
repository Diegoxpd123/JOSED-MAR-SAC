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

import pe.edu.upc.spring.model.Cliente;
import pe.edu.upc.spring.service.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService rService;
	
	@RequestMapping("/bienvenido")
	public String irClienteBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irRace(Map<String, Object> model) {
		model.put("listaClientes", rService.listar());
		return "clientes/listCliente";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clientes/cliente";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Cliente objCliente, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "race";
		}
		else {
			objCliente.setEstado("Nuevo");
			
			int e=0;
			
			objCliente.setN_compras(e);
		
			boolean flag = rService.insertar(objCliente);
			if (flag) {
				return "redirect:/cliente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/cliente/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Cliente objCliente, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/race/listar";
		}
		else {
			boolean flag = rService.modificar(objCliente);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/cliente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/cliente/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Cliente> objCliente= rService.listarId(id);
		if (objCliente == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/cliente/listar";
		}
		else {
			model.addAttribute("cliente", objCliente);
			return "clientes/cliente";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaClientes", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un cliente asignado");
			model.put("listaClientes", rService.listar());
		}
		return "clientes/listCliente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClientes", rService.listar());
		return "clientes/listCliente";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Cliente cliente) throws ParseException {
		rService.listarId(cliente.getId());
		return "clientes/listCliente";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Cliente cliente) throws ParseException {
		List<Cliente> listaClientes;
		cliente.setNombre(cliente.getNombre());
		listaClientes = rService.buscarNombre(cliente.getNombre());
		
		if (listaClientes.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaClientes", listaClientes);
		return "clientes/buscarCliente";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clientes/buscarCliente";
	}
		
}



