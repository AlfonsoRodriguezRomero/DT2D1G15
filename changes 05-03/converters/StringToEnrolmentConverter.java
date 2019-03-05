
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.EnrolmentService;
import domain.Enrolment;

@Component
@Transactional
public class StringToEnrolmentConverter implements Converter<String, Enrolment> {

	@Autowired
	EnrolmentService	enrolmentService;


	@Override
	public Enrolment convert(final String text) {
		Enrolment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.enrolmentService.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
