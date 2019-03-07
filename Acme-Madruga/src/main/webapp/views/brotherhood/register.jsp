<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="brotherhood/register.do" modelAttribute="brotherhood">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	
	
	<fieldset>
	<legend align="left"><spring:message code="brotherhood.edit.contact" /></legend>
	
		<acme:textbox code="brotherhood.edit.label.name" path="name"/>		
	
		<br/>
		<br/>
	
		<acme:textbox code="brotherhood.edit.label.middleName" path="middleName"/>		
		
		<br/>
		<br/>
		
		<acme:textbox code="brotherhood.edit.label.surname" path="surname"/>	
		
		<br/>
		<br/>
		
		<acme:textbox code="brotherhood.edit.label.address" path="address"/>	
		
		<br/>
		<br/>
	
		<acme:email code="brotherhood.edit.label.email" path="email"/>
		
		<br/>
		<br/>
		
		<acme:phoneNumber code="brotherhood.edit.label.phoneNumber" path="phoneNumber" checkCode="brotherhood.confirm"/>
		
		
		<br/>
		<br/>
	
		<acme:textbox code="brotherhood.edit.label.photoURL" path="photoURL"/>
		
		<br/>
		<br/>
		
		<acme:textbox code="brotherhood.edit.label.title" path="title"/>
		
		<br/>
		<br/>
		
		<acme:textbox code="brotherhood.edit.label.establishmentDate" path="establishmentDate"/>
		
		<br/>
		<br/>
		
		<acme:textarea code="brotherhood.edit.label.pictures" path="pictures"/>
		
		<br/>
		<br/>

		
	</fieldset>
	<br/>
	<br/>

	<fieldset>
	
		<acme:textbox code="brotherhood.edit.label.username" path="userAccount.username"/>
		
		<br/>
		<br/>
		
		<acme:textbox code="brotherhood.edit.label.password" path="userAccount.password"/>
		
	</fieldset>
	<br/>
	<br/>
	<acme:submit name="save" code="brotherhood.edit.save"/>&nbsp;
	<acme:cancel url="welcome/index.do" code="brotherhood.edit.cancel"/>

</form:form>
