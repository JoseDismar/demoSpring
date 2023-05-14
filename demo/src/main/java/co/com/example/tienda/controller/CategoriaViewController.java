package co.com.example.tienda.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.example.tienda.entities.Categoria;
import co.com.example.tienda.repository.CategoriaRepository;

@Controller
@RequestMapping//@RequestMapping("/crud")
public class CategoriaViewController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/")
	public String listar(Model model) {
		List<Categoria> categorias=categoriaRepository.findAll();
		model.addAttribute("categorias",categorias);
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregarCategoria(Model model) {
		model.addAttribute("categoria",new Categoria());
		return "agregar";
	}
	
	 @PostMapping("/guardar")
	    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
	        categoriaRepository.save(categoria);
	        return "redirect:/";
	    }
	 
	  @PostMapping("/categoria/eliminar")
	  public String eliminarCategoriabyId(@RequestParam("id") Integer id) {
	    categoriaRepository.deleteById(id);
	    return "redirect:/";
	  }
	  
	  @GetMapping("eliminar/{id}")
	  public String eliminarCategoria(@PathVariable("id") Integer id) {
	    categoriaRepository.deleteById(id);
	    return "redirect:/";
	  }
	  @GetMapping("editar/{id}")
	  public String editarCategoria(@PathVariable("id") Integer id, Model model) {
		  Categoria categoria =categoriaRepository.findById(id).get();
		  model.addAttribute("categoria", categoria);
	    return "agregar";
	  }
	  

}
