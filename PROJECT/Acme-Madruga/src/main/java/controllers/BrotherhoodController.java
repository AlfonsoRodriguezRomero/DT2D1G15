/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import domain.Brotherhood;
import forms.BrotherhoodForm;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;


	// Constructors -----------------------------------------------------------

	public BrotherhoodController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Brotherhood> brotherhoods;

		brotherhoods = this.brotherhoodService.findAll();
		res = new ModelAndView("brotherhood/list");
		res.addObject("brotherhoods", brotherhoods);
		res.addObject("requestURI", "brotherhood/list.do");

		return res;
	}

	// Register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		BrotherhoodForm brotherhoodForm;

		brotherhoodForm = new BrotherhoodForm();
		res = this.createEditModelAndView(brotherhoodForm);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("Brotherhood") final BrotherhoodForm brotherhoodForm, final BindingResult binding) {
		ModelAndView result;
		final Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.reconstruct(brotherhoodForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(brotherhoodForm);
		else
			try {
				//Terms and pass must be checked
				Assert.isTrue(brotherhoodForm.getCheckTerms());
				Assert.isTrue(brotherhoodForm.equalPass());
				this.brotherhoodService.save(brotherhood);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Handy-Worker");
			} catch (final Throwable error) {
				result = this.createEditModelAndView(brotherhood, "brotherhood.comit.error");
			}

		return result;

	}

	// Ancillary methods
	protected ModelAndView createEditModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createEditModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;

		result = new ModelAndView("brotherhood/register");
		result.addObject("brotherhood", brotherhood);
		result.addObject("message", message);

		return result;
	}

	//ModelAndView Form
	protected ModelAndView createEditModelAndView(final BrotherhoodForm brotherhoodForm) {
		ModelAndView result;

		result = this.createEditModelAndView(brotherhoodForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final BrotherhoodForm brotherhoodForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("brotherhood/register");
		result.addObject("brotherhood", brotherhoodForm);
		result.addObject("message", message);

		return result;
	}

}
