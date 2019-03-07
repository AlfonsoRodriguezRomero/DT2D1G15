<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<h1>
	<spring:message code="position.show"/>	
</h1>
<br/>
<spring:message code="position.name"/>
<jstl:out value="${position.name}"/>
<br/>
<spring:message code="position.language"/>
<jstl:out value="${position.language}"/>
<br/>


<!-- Actions -->

<input type="button" name="back"
		value="<spring:message code="position.back"/>"
		onclick="javascript: relativeRedir('position/administrator/list.do')"/>
</security:authorize>