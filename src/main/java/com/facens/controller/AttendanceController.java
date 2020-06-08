package com.facens.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.facens.entity.Attendance;
import com.facens.entity.Client;
import com.facens.entity.Product;
import com.facens.entity.Professional;
import com.facens.entity.dto.AttendanceDTO;
import com.facens.entity.dto.ClientDTO;
import com.facens.entity.dto.ProductDTO;
import com.facens.entity.dto.ProfessionalDTO;
import com.facens.entity.newdto.AttendanceNewDTO;
import com.facens.service.AttendanceService;
import com.facens.service.ClientService;
import com.facens.service.ProductService;
import com.facens.service.ProfessionalService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService as;
	
	@Autowired
	private ProfessionalService ps;
	
	@Autowired
	private ClientService cs;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/attendances")
	public ModelAndView getAttendances() {
		ModelAndView mv = new ModelAndView("AttendanceList");
		List<Attendance> listAtt = as.getAttendances ();
		List<AttendanceDTO> listDto = listAtt.stream ().map (att -> new AttendanceDTO (att)).collect (Collectors.toList ());
		for (int index = 0; index < listAtt.size(); index++) {
			for (Integer id : listAtt.get(index).getProductsIds()) {
				Product p = productService.getProductById(id);
				listDto.get(index).getProducts().add(new ProductDTO(p));
			}
		}
		
		mv.addObject("attendances", listDto);
		return mv;
	}
	
	@GetMapping("/attendancesByProfessional")
	public ModelAndView getAttendancesByProfessional(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("AttendanceList");
		Professional p = ps.getProfessionalById (id);
		List<Attendance> listAtt = as.getAttendancesByProfessional(p);
		List<AttendanceDTO> listDto = listAtt.stream ().map (att -> new AttendanceDTO (att)).collect (Collectors.toList ());
		for (int index = 0; index < listAtt.size(); index++) {
			for (Integer productId : listAtt.get(index).getProductsIds()) {
				Product prod = productService.getProductById(productId);
				listDto.get(index).getProducts().add(new ProductDTO(prod));
			}
		}
		mv.addObject("attendances", listDto);
		return mv;
	}
	
	@GetMapping("/attendancesByProfessionalAjax")
	public ResponseEntity<List<AttendanceDTO>> getAttendancesByProfessionalAjax(@RequestParam Integer id) {
		Professional p = ps.getProfessionalById (id);
		List<Attendance> listAtt = as.getAttendancesByProfessional(p);
		List<AttendanceDTO> listDto = listAtt.stream ().map (att -> new AttendanceDTO (att)).collect (Collectors.toList ());
		for (int index = 0; index < listAtt.size(); index++) {
			for (Integer productId : listAtt.get(index).getProductsIds()) {
				Product prod = productService.getProductById(productId);
				listDto.get(index).getProducts().add(new ProductDTO(prod));
			}
		}
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/attendancesByClient")
	public ModelAndView getAttendancesByClient(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("AttendanceList");
		Client c = cs.getClientById (id);
		List<Attendance> listAtt = as.getAttendancesByClient (c);
		List<AttendanceDTO> listDto = listAtt.stream ().map (att -> new AttendanceDTO (att)).collect (Collectors.toList ());
		for (int index = 0; index < listAtt.size(); index++) {
			for (Integer productId : listAtt.get(index).getProductsIds()) {
				Product prod = productService.getProductById(productId);
				listDto.get(index).getProducts().add(new ProductDTO(prod));
			}
		}
		
		mv.addObject("attendances", listDto);
		return mv;
	}
	
	@GetMapping("/newAttendance")
	public ModelAndView redirectToInsert ()
	{
		ModelAndView mv = new ModelAndView("AttendanceInsert");
		List<ProfessionalDTO> listP = ps.getProfessionals().stream().map(p -> new ProfessionalDTO(p)).collect(Collectors.toList());
		List<ClientDTO> listC = cs.getClients().stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
		mv.addObject("professionals", listP);
		mv.addObject("clients", listC);
		return mv;
	}
	
	@PostMapping("/insertAttendances")
	public String insert (@ModelAttribute AttendanceNewDTO dto) {
		Attendance c = as.fromNewDTO(dto);
		as.insert(c);
		return "redirect:/attendances";
	}
	
	@PostMapping("/updateAttendances")
	public String update (@ModelAttribute AttendanceNewDTO dto) {
		Attendance c = as.fromNewDTO (dto);
		as.update (c);
		return "redirect:/attendances";
	}
	
	@GetMapping("/redirectToUpdateAttendance")
	public ModelAndView redirectToUpdate (@RequestParam Integer id)
	{
		ModelAndView mv = new ModelAndView("AttendanceUpdate");
		Attendance c = as.getAttendanceById (id);
		AttendanceDTO dto = new AttendanceDTO (c);
		
		List<ProductDTO> listP = productService.getProductsWhenQuantitySkuGreaterThanZero ().stream().map(p -> new ProductDTO(p)).collect (Collectors.toList ());
		mv.addObject ("dto", dto);
		mv.addObject ("products", listP);
		return mv;
	}
	
	@GetMapping("/cancelAttendance")
	public String cancelAttendance (@RequestParam Integer id)
	{
		Attendance att = as.getAttendanceById (id);
		att = as.cancelAttendance (att);
		return "redirect:/attendances";
	}
	
	@GetMapping("/deleteAttendances")
	public String delete (@RequestParam Integer id)
	{
		try 
		{
			Attendance c = as.getAttendanceById (id);
			if (c != null) {
				as.deleteById (id);
				return "redirect:/attendances";
			}
			else {
				return "redirect:/errorPage";
			}
		} 
		catch (Exception e) 
		{
			return "redirect:/errorPage";
		}		
	}
}
