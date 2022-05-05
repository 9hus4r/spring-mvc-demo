package com.luv2code.springdemo.mvc;


import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		
		// create a customer object
		Customer theCustomer = new Customer();
		
		// add student object to the model
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}

	@RequestMapping("/processForm")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult
			) {
		
		System.out.println("\"\\n\\n\\n\\nLast name: |"+theCustomer.getLastName()+"|");
		
		System.out.println("Binding result: "+theBindingResult);
		
		System.out.println("\n\n\n\n");
		
		
		if(theBindingResult.hasErrors()) {
			return "customer-form";	
		} else {
			return "customer-confirmation";	
		}
	}
	
	
	//this function will trim all String data from form
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	
	
}
