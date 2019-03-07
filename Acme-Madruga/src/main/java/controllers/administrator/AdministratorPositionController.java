
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FloatService;
import services.PositionService;
import controllers.AbstractController;
import domain.Position;

@Controller
@RequestMapping("position/administrator")
public class AdministratorPositionController extends AbstractController {

	@Autowired
	PositionService	positionService;

	@Autowired
	FloatService	floatService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//public ModelAndView list(@RequestParam final String language) {
	public ModelAndView list() {
		ModelAndView res;
		Collection<Position> positions;

		positions = this.positionService.findAll();
		//positions = this.positionService.findByLanguage(language);
		res = new ModelAndView("positions/list");
		res.addObject("positions", positions);
		res.addObject("requestURI", "positions/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		Position position;

		position = this.positionService.findOne(id);
		res = this.createEditModelAndView(position);
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		Position position;

		position = this.positionService.create();

		res = this.createEditModelAndView(position);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionId) {
		final ModelAndView res;
		Position position;

		position = this.positionService.findOne(positionId);
		res = this.createEditModelAndView(position);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Position position, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(position);
		else
			try {
				this.positionService.save(position);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(position, "commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Position position, final BindingResult binding) {
		ModelAndView res;

		try {
			this.positionService.delete(position);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(position, "position.commit.error");
		}

		return res;
	}
	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Position position) {
		ModelAndView res;
		res = this.createEditModelAndView(position, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Position position, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("position/edit");
		res.addObject("position", position);
		res.addObject("message", messageCode);

		return res;
	}
}
