
package forms;

import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

public class BrotherhoodForm {

	private String	name;
	private String	middleName;
	private String	surname;
	private String	photoURL;
	private String	email;
	private String	phoneNumber;
	private String	address;
	private String	title;
	private Date	establishmentDate;
	private String	pictures;

	private String	username;
	private String	password;

	private String	password2;
	private Boolean	checkTerms;


	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhotoURL() {
		return this.photoURL;
	}
	public void setPhotoURL(final String photoURL) {
		this.photoURL = photoURL;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}
	public void setAddress(final String address) {
		this.address = address;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyy-MM-dd")
	public Date getEstablishmentDate() {
		return this.establishmentDate;
	}
	public void setEstablishmentDate(final Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	@NotBlank
	public String getPictures() {
		return this.pictures;
	}
	public void setPictures(final String pictures) {
		this.pictures = pictures;
	}
	@Size(min = 5, max = 32)
	public String getUsername() {
		return this.username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}

	@Valid
	@NotEmpty
	@ElementCollection
	public String getPassword2() {
		return this.password2;
	}
	public void setPassword2(final String password2) {
		this.password2 = password2;
	}

	@NotNull
	public Boolean getCheckTerms() {
		return this.checkTerms;
	}
	public void setCheckTerms(final Boolean checkTerms) {
		this.checkTerms = checkTerms;
	}

	//Confirmation password must be equal to the password
	public Boolean equalPass() {
		if (this.getPassword().equals(this.getPassword2()))
			return true;
		else
			return false;
	}

}
