package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.infa.FileSaver;
import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.Product;
import br.com.casadocodigo.loja.validation.ProductValidator;

@RequestMapping("/products")
@Controller
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductValidator validator;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void addValidators(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView mv = new ModelAndView("products/form");
		mv.addObject("types", BookType.values());
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	@Cacheable(value = "lastProdutcs")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("products/list");
		mv.addObject("products", productDAO.list());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	@CacheEvict(value = "lastProdutcs", allEntries = true)
	public ModelAndView save(@Valid Product product, 
			BindingResult result, RedirectAttributes ra,
			MultipartFile summary) {
		
		if (result.hasErrors()) {
			return form(product);
		}
		
		if (summary != null && !summary.isEmpty()) {
			String baseDir = "uploaded-summaries";
			String webPath = null;
			try {
				webPath = fileSaver.write(summary, baseDir);
				product.setSummaryPath(webPath);
				
			} catch (Exception e) {
				ModelAndView mv = form(product);
				mv.addObject("msgErro", e.getMessage());
				return mv;
			}
		}
		
		productDAO.save(product);
		ra.addFlashAttribute("sucesso", "Produto Cadastrado com Sucesso!");
		
		return new ModelAndView("redirect:/products");
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("products/show");
		mv.addObject("product", productDAO.find(id));
		return mv;
	}

}
