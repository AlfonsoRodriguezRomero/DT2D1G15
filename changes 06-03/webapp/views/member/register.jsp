<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="member/register.do" modelAttribute="member">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	
	
	<fieldset>
	<legend align="left"><spring:message code="member.edit.contact" /></legend>
	
		<acme:textbox code="member.edit.label.name" path="name"/>		
	
		<br/>
		<br/>
	
		<acme:textbox code="member.edit.label.middleName" path="middleName"/>		
		
		<br/>
		<br/>
		
		<acme:textbox code="member.edit.label.surname" path="surname"/>	
		
		<br/>
		<br/>
		
		<acme:textbox code="member.edit.label.address" path="address"/>	
		
		<br/>
		<br/>
	
		<acme:email code="member.edit.label.email" path="email"/>
		
		<br/>
		<br/>
		
		<acme:phoneNumber code="member.edit.label.phoneNumber" path="phoneNumber" checkCode="member.confirm"/>
		
		
		<br/>
		<br/>
	
		<acme:textbox code="member.edit.label.photoURL" path="photoURL"/>
		
		<br/>
		<br/>

		
	</fieldset>
	<br/>
	<br/>

	<fieldset>
	
		<acme:textbox code="member.edit.label.username" path="userAccount.username"/>
		
		<br/>
		<br/>
		
		<acme:textbox code="member.edit.label.password" path="userAccount.password"/>
		
		<br/>
		<br/>
		
		<acme:textbox code="member.edit.label.password2" path="password2"/>
		
	</fieldset>
	<br/>
	<br/>
	<acme:submit name="save" code="member.edit.save"/>&nbsp;
	<acme:cancel url="welcome/index.do" code="member.edit.cancel"/>

</form:form>
