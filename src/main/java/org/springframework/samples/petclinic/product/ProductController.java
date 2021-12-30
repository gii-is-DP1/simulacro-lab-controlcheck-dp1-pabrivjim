package org.springframework.samples.petclinic.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
	
	@Autowired
    private ProductService productService;
    

    @GetMapping(value = "/product/create")
	public String createProduct(ModelMap modelmap) {
		Product product = new Product();
		modelmap.put("product", product);
		return "products/createOrUpdateProductForm";
	}

	@PostMapping(value = "/product/create")
	public String saveProduct(@Valid Product product, BindingResult result, ModelMap modelmap) {
		if (result.hasErrors()) {
			modelmap.put("product", product);
			if(product.price<0){
				return "products/createOrUpdateProductForm";
			}
			if(product.name== ""){
				return "products/createOrUpdateProductForm";
			}
			
			return "welcome";
		}
		else {
			//creating owner, user and authorities
			this.productService.save(product);
			
			return "products/createOrUpdateProductForm";
		}
	}

}

