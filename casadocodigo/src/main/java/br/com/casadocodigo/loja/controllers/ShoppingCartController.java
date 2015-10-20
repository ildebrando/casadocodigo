package br.com.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.PaymentData;
import br.com.casadocodigo.loja.model.Product;
import br.com.casadocodigo.loja.model.ShoppingCart;
import br.com.casadocodigo.loja.model.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "shoppingCart/items";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, @RequestParam BookType bookType) {
		
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		
		shoppingCart.add(item);
		return new ModelAndView("redirect:/products");
	}

	@RequestMapping(method = RequestMethod.POST, value="/checkout")
	public Callable<ModelAndView> checkout(RedirectAttributes ra) {
		
		return new Callable<ModelAndView>() {
		
			@Override
			public ModelAndView call() throws Exception {
				
				BigDecimal total = shoppingCart.getTotal();
				String uri = "http://book-payment.herokuapp.com/payment";
				String response = null;
				
				try {
					response = restTemplate.postForObject(uri, 
							new PaymentData(total), String.class);
					System.out.println(response);
					
					ModelAndView mv = new ModelAndView("redirect:/products");
					ra.addFlashAttribute("sucesso", response);
					return mv;
					
				} catch (HttpClientErrorException e) {
					response = e.getMessage();
					System.out.println("Ocorreu um erro ao criar o Pagamento: "+ e.getMessage());
					
					ModelAndView mv = new ModelAndView("redirect:/shopping");
					ra.addFlashAttribute("error", response);
					return mv;
				}
			}
		};
	}

}
