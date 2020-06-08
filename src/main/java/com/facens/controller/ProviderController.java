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

import com.facens.entity.Provider;
import com.facens.entity.dto.ProviderDTO;
import com.facens.entity.newdto.ProviderNewDTO;
import com.facens.service.ProviderService;

@Controller
public class ProviderController {
	
	@Autowired
	private ProviderService ps;
	
	@GetMapping("/providers")
	public ModelAndView getProviders() {
		ModelAndView mv = new ModelAndView("ProviderList");
		List<ProviderDTO> list = ps.getProviders ().stream ().map (c -> new ProviderDTO (c)).collect (Collectors.toList ());
		mv.addObject("providers", list);
		return mv;
	}
	
	@GetMapping("/newProvider")
	public ModelAndView redirectToInsert ()
	{
		ModelAndView mv = new ModelAndView("ProviderInsert");
		return mv;
	}
	
	@PostMapping("/insertProviders")
	public String insert (@ModelAttribute ProviderNewDTO dto) {
		Provider p = ps.fromNewDTO(dto);
		ps.insert(p);
		return "redirect:/providers";
	}
	
	@PostMapping("/updateProviders")
	public String update (@ModelAttribute ProviderDTO dto) {
		Provider p = ps.fromDTO (dto);
		ps.update (p);
		return "redirect:/providers";
	}
	
	@GetMapping("/redirectToUpdateProvider")
	public ModelAndView redirectToUpdate (@RequestParam Integer id)
	{
		ModelAndView mv = new ModelAndView("ProviderUpdate");
		Provider p = ps.getProviderById (id);
		ProviderDTO dto = new ProviderDTO (p);
		mv.addObject ("dto", dto);
		return mv;
	}
	
	@GetMapping("/deleteProviders")
	public String delete (@RequestParam Integer id)
	{
		try 
		{
			Provider p = ps.getProviderById (id);
			if (p != null) {
				ps.deleteById (id);
				return "redirect:/providers";
			}
			else {
				return "redirect:/errorPage?message=Este fornecedor nao pode ser excluido&back=providers";
			}
		} 
		catch (Exception e) 
		{
			return "redirect:/errorPage?message=Este fornecedor nao pode ser excluido&back=providers";
		}
	}
}
