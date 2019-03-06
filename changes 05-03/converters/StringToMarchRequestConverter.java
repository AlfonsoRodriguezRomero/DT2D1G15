
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.MarchRequestService;
import domain.MarchRequest;

@Component
@Transactional
public class StringToMarchRequestConverter implements Converter<String, MarchRequest> {

	@Autowired
	MarchRequestService	marchRequestService;


	@Override
	public MarchRequest convert(final String text) {
		MarchRequest result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.marchRequestService.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}