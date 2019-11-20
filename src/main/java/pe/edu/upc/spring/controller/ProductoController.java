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

import pe.edu.upc.spring.model.Producto;
import pe.edu.upc.spring.service.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private IProductoService rService;
	
	@RequestMapping("/bienvenido")
	public String irProductoBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irRace(Map<String, Object> model) {
		model.put("listaProductores", rService.listar());
		return "productores/listProducto";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("producto", new Producto());
		return "productores/producto";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Producto objProducto, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "race";
		}
		else {
			
			boolean flag = rService.insertar(objProducto);
			if (flag) {
				return "redirect:/producto/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/producto/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Producto objProducto, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/race/listar";
		}
		else {
			boolean flag = rService.modificar(objProducto);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/producto/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/producto/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Producto> objProducto= rService.listarId(id);
		if (objProducto == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/producto/listar";
		}
		else {
			model.addAttribute("producto", objProducto);
			return "productores/producto";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaProductores", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un producto asignado");
			model.put("listaProductores", rService.listar());
		}
		return "productores/listProducto";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaProductores", rService.listar());
		return "productores/listProducto";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Producto producto) throws ParseException {
		rService.listarId(producto.getId());
		return "productores/listProducto";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Producto producto) throws ParseException {
		List<Producto> listaProductores;
		producto.setNombre(producto.getNombre());
		listaProductores = rService.buscarNombre(producto.getNombre());
		
		if (listaProductores.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaProductores", listaProductores);
		return "productores/buscarProducto";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("producto", new Producto());
		return "productores/buscarProducto";
	}
		
}



