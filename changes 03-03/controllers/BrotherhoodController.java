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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;


	// Constructors -----------------------------------------------------------

	public BrotherhoodController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

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
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.create();
		res = this.createEditModelAndView(brotherhood);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Brotherhood brotherhood, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(brotherhood);
		else
			try {
				this.brotherhoodService.save(brotherhood);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Handy-Worker");
			} catch (final Throwable error) {
				result = this.createEditModelAndView(brotherhood, "brotherhood.comit.error");
				System.out.println(error.getMessage());
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

}
