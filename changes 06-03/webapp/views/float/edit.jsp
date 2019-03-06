<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('BROTHERHOOD')">

<form:form action="${requestURI}" modelAttribute="float">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="title">
			<spring:message code="float.title" />
	</form:label>
	<form:input path="title" readonly="${isRead}" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="description">
			<spring:message code="float.description" />
	</form:label>
	<form:input path="description" readonly="${isRead}" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="pictures">
			<spring:message code="float.pictures" />
	</form:label>
	<form:input path="pictures" readonly="${isRead}" />
	<form:errors cssClass="error" path="pictures" />
	<br />

	<form:label path="brotherhood">
			<spring:message code="float.brotherhood" />
	</form:label>
	<form:input path="brotherhood" readonly="${isRead}" />
	<form:errors cssClass="error" path="brotherhood" />
	<br />
	
	<jstl:if test="${isRead == false}">
		<br />
		<input type="submit" name="save"
			value="<spring:message code="float.save"/>"
			onclick=" javascript: relativeRedir('welcome/index.do');">
			
		<input type="button" name="cancel"
			value="<spring:message code="float.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />
	</jstl:if>
	
	<jstl:if test="${isRead == true}">
		<input type="button" name="back"
			value="<spring:message code="float.black" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />
	</jstl:if>
</form:form>
</security:authorize>