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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FloatService;

@Controller
@RequestMapping("/float")
public class FloatController extends AbstractController {

	@Autowired
	FloatService	floatService;


	// Constructors -----------------------------------------------------------

	public FloatController() {
		super();
	}

	@RequestMapping(value = "/listForAll", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;
		final Collection<domain.Float> floats;

		floats = this.floatService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("float/listForAll");
		res.addObject("floats", floats);
		res.addObject("requestURI", "float/listForAll.do");

		return res;
	}

}
