
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import forms.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

}
