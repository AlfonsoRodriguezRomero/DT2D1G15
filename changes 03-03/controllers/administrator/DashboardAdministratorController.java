
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MarchRequestService;
import services.MemberService;
import services.ProcessionService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;

@Controller
@RequestMapping("dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	MarchRequestService	marchRequestService;

	@Autowired
	ProcessionService	processionService;


	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView res;
		res = new ModelAndView("administrator/dashboard");

		final String[] memberPerBrotherhood = this.memberService.memberStatistics().split(",");

		res.addObject("avgMember", memberPerBrotherhood[0].trim());
		res.addObject("minMember", memberPerBrotherhood[1].trim());
		res.addObject("maxMember", memberPerBrotherhood[2].trim());
		res.addObject("stdevMember", memberPerBrotherhood[3].trim());

		final Collection<Brotherhood> largestBrotherhoods = this.brotherhoodService.largestBrotherhoods();
		final List<String> brotherhoodNames = new ArrayList<>();
		for (final Brotherhood b : largestBrotherhoods)
			brotherhoodNames.add(b.getName() + " " + b.getSurname());

		res.addObject("largestBrotherhoods", brotherhoodNames);

		final Collection<Brotherhood> smallestBrotherhoods = this.brotherhoodService.smallestBrotherhoods();
		final List<String> brotherhoodNames2 = new ArrayList<>();
		for (final Brotherhood b : smallestBrotherhoods)
			brotherhoodNames2.add(b.getName() + " " + b.getSurname());

		res.addObject("smallestBrotherhoods", smallestBrotherhoods);

		final Double marchRequestRatio = this.marchRequestService.marchRequestRatio();

		res.addObject("marchRequestRatio", marchRequestRatio);

		final Collection<Procession> processionsIn30days = this.processionService.processionsIn30Days();
		final List<String> processionTitles = new ArrayList<>();
		for (final Procession p : processionsIn30days)
			processionTitles.add(p.getTitle());

		res.addObject("processionsIn30days", processionTitles);

		final Collection<Member> membersWith10percentOfMarchRequestAccepted = this.memberService.membersWith10percentOfMarchRequestAccepted();
		final List<String> memberNames = new ArrayList<>();
		for (final Member m : membersWith10percentOfMarchRequestAccepted)
			memberNames.add(m.getName() + " " + m.getSurname());

		res.addObject("membersWith10percentOfMarchRequestAccepted", memberNames);

		//Histogram of positions

		return res;

	}
}
