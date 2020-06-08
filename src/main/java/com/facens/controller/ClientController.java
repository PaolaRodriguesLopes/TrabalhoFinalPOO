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

import com.facens.entity.Client;
import com.facens.entity.dto.ClientDTO;
import com.facens.entity.newdto.ClientNewDTO;
import com.facens.service.ClientService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService cs;
	
	@GetMapping("/clients")
	public ModelAndView getClients() {
		ModelAndView mv = new ModelAndView("ClientList");
		List<ClientDTO> list = cs.getClients ().stream ().map (c -> new ClientDTO (c)).collect (Collectors.toList ());
		mv.addObject("clients", list);
		return mv;
	}
	
	@GetMapping("/newClient")
	public ModelAndView redirectToInsert ()
	{
		ModelAndView mv = new ModelAndView("ClientInsert");
		return mv;
	}
	
	@PostMapping("/insertClients")
	public String insert (@ModelAttribute ClientNewDTO dto) {
		Client c = cs.fromNewDTO(dto);
		cs.insert(c);
		return "redirect:/clients";
	}
	
	@PostMapping("/updateClients")
	public String update (@ModelAttribute ClientDTO dto) {
		Client c = cs.fromDTO (dto);
		cs.update (c);
		return "redirect:/clients";
	}
	
	@GetMapping("/redirectToUpdateClient")
	public ModelAndView redirectToUpdate (@RequestParam Integer id)
	{
		ModelAndView mv = new ModelAndView("ClientUpdate");
		Client c = cs.getClientById (id);
		ClientDTO dto = new ClientDTO (c);
		mv.addObject ("dto", dto);
		return mv;
	}
	
	@GetMapping("/deleteClients")
	public String delete (@RequestParam Integer id)
	{
		try {
			Client c = cs.getClientById (id);
			if (c != null) {
				cs.deleteById (id);
				return "redirect:/clients";
			}
			else {
				return "redirect:/errorPage?message=Este cliente nao pode ser excluido&back=clients";
			}
		} catch (Exception e) {
			return "redirect:/errorPage?message=Este cliente nao pode ser excluido&back=clients";
		}
	}
}
