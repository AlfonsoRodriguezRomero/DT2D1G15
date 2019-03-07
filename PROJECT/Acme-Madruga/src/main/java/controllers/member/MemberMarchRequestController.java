
package controllers.member;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MarchRequestService;
import services.MemberService;
import services.ProcessionService;
import controllers.AbstractController;
import domain.MarchRequest;
import domain.Member;
import domain.Procession;

@Controller
@RequestMapping("marchRequest/member")
public class MemberMarchRequestController extends AbstractController {

	@Autowired
	MarchRequestService	marchRequestService;

	@Autowired
	ProcessionService	processionService;

	@Autowired
	MemberService		memberService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<MarchRequest> marchRequests;
		final Collection<MarchRequest> approvedMarchRequests;
		final Collection<MarchRequest> rejectedMarchRequests;
		final Collection<MarchRequest> pendingMarchRequests;
		final int memberId = this.memberService.findByPrincipal().getId();

		marchRequests = this.marchRequestService.findByMemberId(memberId);
		approvedMarchRequests = this.marchRequestService.findApprovedByMemberId(memberId);
		rejectedMarchRequests = this.marchRequestService.findRejectedByMemberId(memberId);
		pendingMarchRequests = this.marchRequestService.findPendingByMemberId(memberId);

		res = new ModelAndView("marchRequest/list");
		res.addObject("marchRequests", marchRequests);
		res.addObject("approvedMarchRequests", approvedMarchRequests);
		res.addObject("rejectedMarchRequests", rejectedMarchRequests);
		res.addObject("pendingMarchRequests", pendingMarchRequests);
		res.addObject("requestURI", "marchRequest/member/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		MarchRequest marchRequest;

		marchRequest = this.marchRequestService.findOne(id);
		res = this.createEditModelAndView(marchRequest);
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		MarchRequest marchRequest;

		marchRequest = this.marchRequestService.create();

		res = this.createEditModelAndView(marchRequest);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int marchRequestId) {
		final ModelAndView res;
		MarchRequest marchRequest;

		marchRequest = this.marchRequestService.findOne(marchRequestId);
		res = this.createEditModelAndView(marchRequest);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MarchRequest marchRequest, final BindingResult binding) {
		ModelAndView res;
		MarchRequest marchRequest2;
		marchRequest2 = this.marchRequestService.reconstruct(marchRequest, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(marchRequest2);
		else
			try {
				this.marchRequestService.save(marchRequest2);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(marchRequest2, "marchRequest.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MarchRequest marchRequest, final BindingResult binding) {
		ModelAndView res;

		try {
			this.marchRequestService.delete(marchRequest);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(marchRequest, "marchRequest.commit.error");
		}

		return res;
	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final MarchRequest marchRequest) {
		ModelAndView res;
		res = this.createEditModelAndView(marchRequest, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final MarchRequest marchRequest, final String messageCode) {
		final ModelAndView res;
		final Member logmember = this.memberService.findByPrincipal();
		final int id = logmember.getId();
		final Collection<Procession> processions = this.processionService.findByMemberId(id);

		res = new ModelAndView("marchRequest/edit");
		res.addObject("marchRequest", marchRequest);
		res.addObject("processions", processions);
		res.addObject("message", messageCode);

		return res;
	}
}
