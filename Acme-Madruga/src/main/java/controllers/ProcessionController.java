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

import services.ProcessionService;
import domain.Procession;

@Controller
@RequestMapping("/procession")
public class ProcessionController extends AbstractController {

	@Autowired
	ProcessionService	processionService;


	// Constructors -----------------------------------------------------------

	public ProcessionController() {
		super();
	}

	@RequestMapping(value = "/listForAll", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;
		final Collection<Procession> processions;

		processions = this.processionService.findAllInFinalMode(brotherhoodId);
		res = new ModelAndView("procession/listForAll");
		res.addObject("processions", processions);
		res.addObject("requestURI", "procession/listForAll.do");

		return res;
	}

}
