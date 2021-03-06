package com.web.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;



import com.web.demo.model.Product;
import com.web.demo.service.ProductService;



import global.GlobalData;



@Controller
public class CartController {
@Autowired
ProductService productService;



@GetMapping("/addToCart/{id}")
public String addToCart (@PathVariable int id) {
GlobalData.cart.add(productService.getProductById(id).get());
return"redirect:/shop";

}
@GetMapping("/cart")
public String cartGet(Model model) {
model.addAttribute("cartCount",GlobalData.cart.size());
model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
model.addAttribute("cart",GlobalData.cart);
return"cart";
}
@GetMapping("/cart/removeItem/{index}")
public String cartItemRemove(@PathVariable int index) {
GlobalData.cart.remove(index);
return"redirect:/cart";
}



@GetMapping("/checkout")
public String checkout(Model model) {
model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
return"checkout";
}
@PostMapping("/card")
public String card() {
GlobalData.cart.clear();
return "card";
}



@GetMapping("/payNow")
public String payNow() {
//GlobalData.cart.clear();
return "ord";
}



@PostMapping("/payNow")
public String payNow2() {
//GlobalData.cart.clear();
return "ord";
}


}
