
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import repositories.RegistrationRepository;

@Service
@Transactional
public class RegistrationService {

	// Repository
	@Autowired
	private RegistrationRepository	registrationRepository;

	@Autowired
	private Validator				validator;


	// Constructor
	public RegistrationService() {
		super();
	}

}
