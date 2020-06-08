package com.facens.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.facens.entity.Professional;
import com.facens.entity.dto.ProfessionalDTO;
import com.facens.entity.newdto.ProfessionalNewDTO;
import com.facens.service.ProfessionalService;

@Controller
public class ProfessionalController {
	
	@Autowired
	private ProfessionalService ps;
	
	@GetMapping("/professionals")
	public ModelAndView getProfessionals() {
		ModelAndView mv = new ModelAndView("ProfessionalList");
		List<ProfessionalDTO> list = ps.getProfessionals().stream ().map (p -> new ProfessionalDTO (p)).collect (Collectors.toList ());
		mv.addObject("professionals", list);
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView redirectToInsert ()
	{
		ModelAndView mv = new ModelAndView("ProfessionalInsert");
		return mv;
	}
	
	@PostMapping("/insertProfessionals")
	public String insert (@ModelAttribute ProfessionalNewDTO dto) {
		Professional p = ps.fromNewDTO(dto);
		ps.insert(p);
		return "redirect:/professionals";
	}
	
	@PostMapping("/updateProfessionals")
	public String update (@ModelAttribute ProfessionalDTO dto) {
		Professional p = ps.fromDTO (dto);
		ps.update (p);
		return "redirect:/professionals";
	}
	
	@GetMapping("/update")
	public ModelAndView redirectToUpdate (@RequestParam Integer id)
	{
		ModelAndView mv = new ModelAndView("ProfessionalUpdate");
		Professional professional = ps.getProfessionalById (id);
		ProfessionalDTO dto = new ProfessionalDTO (professional);
		mv.addObject ("dto", dto);
		return mv;
	}
	
	@GetMapping("/deleteProfessionals")
	public String delete (@RequestParam Integer id)
	{
		try {
			Professional p = ps.getProfessionalById (id);
	
			if (p != null) {
				
				if (p.getAttendances().size() != 0) {
					return "redirect:/errorPage?message=Este profissional nao pode ser excluido&back=professionals";
				}
				
				ps.deleteById (id);
				return "redirect:/professionals";
			}
			else {
				return "redirect:/errorPage?message=Este profissional nao pode ser excluido&back=professionals";
			}
		} catch (Exception e) {
			return "redirect:/errorPage?message=Este profissional nao pode ser excluido&back=professionals";
		}
	}
}
