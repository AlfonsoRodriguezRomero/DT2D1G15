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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import domain.Member;
import forms.MemberForm;

@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController {

	@Autowired
	MemberService	memberService;


	// Constructors -----------------------------------------------------------

	public MemberController() {
		super();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		MemberForm memberForm;

		memberForm = new MemberForm();

		res = this.createEditModelAndView(memberForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("Member") final MemberForm memberForm, final BindingResult binding) {
		ModelAndView result;
		final Member member;

		member = this.memberService.reconstruct(memberForm, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(memberForm);
		else
			try {
				//Terms and pass must be checked
				Assert.isTrue(memberForm.getCheckTerms());
				Assert.isTrue(memberForm.equalPass());
				this.memberService.save(member);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Madruga");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(member, "register.member.error");
			}

		return result;
	}

	@RequestMapping(value = "/listForAll", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;
		final Collection<Member> members;

		members = this.memberService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("member/listForAll");
		res.addObject("members", members);
		res.addObject("requestURI", "member/listForAll.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		Member member;

		member = this.memberService.findOne(id);
		res = this.createEditModelAndView(member);
		return res;
	}

	protected ModelAndView createEditModelAndView(final Member member) {
		ModelAndView result;

		result = this.createEditModelAndView(member, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Member member, final String message) {
		ModelAndView result;

		result = new ModelAndView("member/register");
		result.addObject("member", member);
		result.addObject("message", message);

		return result;
	}

	//ModelAndView Form
	protected ModelAndView createEditModelAndView(final MemberForm memberForm) {
		ModelAndView result;

		result = this.createEditModelAndView(memberForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final MemberForm memberForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("member/register");
		result.addObject("member", memberForm);
		result.addObject("message", message);

		return result;
	}

}
