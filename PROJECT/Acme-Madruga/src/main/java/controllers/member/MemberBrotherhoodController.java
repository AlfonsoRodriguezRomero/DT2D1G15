/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import controllers.AbstractController;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood/member")
public class MemberBrotherhoodController extends AbstractController {

	@Autowired
	MemberService	memberService;


	// Constructors -----------------------------------------------------------

	public MemberBrotherhoodController() {
		super();
	}

	@RequestMapping(value = "/listForMember", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Brotherhood> brotherhoods;
		final int memberId = this.memberService.findByPrincipal().getId();

		brotherhoods = this.memberService.belongedBrotherhoods(memberId);
		res = new ModelAndView("brotherhood/member/listForMember");
		res.addObject("brotherhoods", brotherhoods);
		res.addObject("requestURI", "brotherhood/member/listForMember.do");

		return res;
	}

}
