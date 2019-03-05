
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Enrolment;

@Component
@Transactional
public class EnrolmentToStringConverter implements Converter<Enrolment, String> {

	@Override
	public String convert(final Enrolment enrolment) {
		String res;

		if (enrolment == null)
			res = null;
		else
			res = String.valueOf(enrolment.getId());

		return res;
	}

}
