package com.Grupo10OO22022.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.Grupo10OO22022.entities.Final;
import com.Grupo10OO22022.helpers.ViewRouteHelper;
import com.Grupo10OO22022.models.FinalModel;
import com.Grupo10OO22022.services.IFinalService;

@Controller
@PreAuthorize("hasAuthority('auditoria')")
@RequestMapping("/finales")
public class FinalController {

	@Autowired
	@Qualifier("finalService")
	private IFinalService finalService;

	private ModelMapper modelMapper = new ModelMapper();

	
	@GetMapping("") // url
	public RedirectView redirectListarFinales() {
		return new RedirectView("/index");
	}
  

	@GetMapping("/nuevo")
	public ModelAndView mostrarFormularioDeFinales() {

		ModelAndView mv= new ModelAndView(ViewRouteHelper.FINAL_VER_FORM);
		mv.addObject("final",new FinalModel(' ', null, 0, null, null, null, null, null));

		return mv;
	}
  

	@PostMapping("/guardar")
	public RedirectView guardarFinal(@ModelAttribute("final") FinalModel f) {
		finalService.guardarFinal(modelMapper.map(f, Final.class));
		return new RedirectView("");
	}


	@GetMapping("/finales/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {

		modelo.addAttribute("final",finalService.getById(id));

		return "editar_final";
	}

	@PostMapping("/finales/editar/{id}")
	public String actualizarFinal(@PathVariable int id,@ModelAttribute("final")Final f, Model modelo) {
    
		Final finalExistente= finalService.getById(id);

		finalExistente.setId(id);
    
		finalExistente.setMesa(f.getMesa());
    
		finalExistente.setFecha(f.getFecha());

		return "redirect:/finales";
	}
}
