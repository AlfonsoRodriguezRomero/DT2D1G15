/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import domain.Administrator;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		final AdministratorForm administratorForm;

		administratorForm = new AdministratorForm();
		res = this.createEditModelAndView(administratorForm);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("Administrator") final AdministratorForm administratorForm, final BindingResult binding) {
		ModelAndView result;
		Administrator administrator;
		administrator = this.administratorService.reconstruct(administratorForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(administratorForm);
		else
			try {
				//Terms and pass must be checked
				Assert.isTrue(administratorForm.getCheckTerms());
				Assert.isTrue(administratorForm.equalPass());
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Handy-Worker");
			} catch (final Throwable error) {
				result = this.createEditModelAndView(administrator, "administrator.comit.error");
			}

		return result;

	}

	// Ancillary methods
	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/register");
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;
	}

	//ModelAndView Form
	protected ModelAndView createEditModelAndView(final AdministratorForm administratorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(administratorForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final AdministratorForm administratorForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/register");
		result.addObject("administrator", administratorForm);
		result.addObject("message", message);

		return result;
	}

}
