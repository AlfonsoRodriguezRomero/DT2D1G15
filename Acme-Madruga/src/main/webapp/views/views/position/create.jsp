<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="position/administrator/create.do"
	modelAttribute="positionForm">

	<form:hidden path="id" />
	
	<form:label path="name">
			<spring:message code="position.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="language">
			<spring:message code="position.language" />:
	</form:label>
	<form:input path="language" />
	<form:errors cssClass="error" path="language" />
	<br />

	<br />

	<input type="submit" name="save" value="<spring:message code="position.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="position.cancel" />"
		onclick="javascript: relativeRedir('position/administrator/list.do');" />
	<br />
</form:form>