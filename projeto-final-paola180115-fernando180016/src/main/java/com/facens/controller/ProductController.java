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

import com.facens.entity.Product;
import com.facens.entity.Provider;
import com.facens.entity.dto.ProductDTO;
import com.facens.entity.dto.ProviderDTO;
import com.facens.entity.newdto.ProductNewDTO;
import com.facens.service.ProductService;
import com.facens.service.ProviderService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	@Autowired
	private ProviderService providerServ;
	
	@GetMapping("/products")
	public ModelAndView getProducts() {
		ModelAndView mv = new ModelAndView("ProductList");
		List<ProductDTO> list = ps.getProducts ().stream ().map (c -> new ProductDTO (c)).collect (Collectors.toList ());
		mv.addObject("products", list);
		return mv;
	}
	
	@GetMapping("/productsByProvider")
	public ModelAndView getProductsByProvider(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("ProductList");
		Provider p = providerServ.getProviderById (id);
		List<ProductDTO> list = ps.getProductsByProvider (p).stream ().map (c -> new ProductDTO (c)).collect (Collectors.toList ());
		mv.addObject("products", list);
		return mv;
	}
	
	@GetMapping("/newProduct")
	public ModelAndView redirectToInsert ()
	{
		ModelAndView mv = new ModelAndView("ProductInsert");
		List<ProviderDTO> list = providerServ.getProviders ().stream ().map (p -> new ProviderDTO (p)).collect (Collectors.toList ());
		mv.addObject("providers", list);
		return mv;
	}
	
	@PostMapping("/insertProducts")
	public String insert (@ModelAttribute ProductNewDTO dto) {
		Product p = ps.fromNewDTO(dto);
		ps.insert(p);
		return "redirect:/products";
	}
	
	@PostMapping("/updateProducts")
	public String update (@ModelAttribute ProductDTO dto) {
		Product p = ps.fromDTO (dto);
		ps.update (p);
		return "redirect:/products";
	}
	
	@GetMapping("/redirectToUpdateProduct")
	public ModelAndView redirectToUpdate (@RequestParam Integer id)
	{
		ModelAndView mv = new ModelAndView("ProductUpdate");
		Product p = ps.getProductById (id);
		ProductDTO dto = new ProductDTO (p);
		mv.addObject ("dto", dto);
		return mv;
	}
	
	@GetMapping("/deleteProducts")
	public String delete (@RequestParam Integer id)
	{
		Product p = ps.getProductById (id);
		if (p != null) {
			ps.deleteById (id);
			return "redirect:/products";
		}
		else {
			return "redirect:/erroProduct";
		}
	}
	
	@GetMapping("/erroProduct")
	public ModelAndView getError()
	{
		ModelAndView mv = new ModelAndView("ErrorPage");
		return mv;
	}
}
