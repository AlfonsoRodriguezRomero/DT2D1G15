<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('BROTHERHOOD')">
<h1>
	<spring:message code="procession.show"/>	
</h1>
<br/>
<spring:message code="procession.title"/>
<jstl:out value="${procession.title}"/>
<br/>
<spring:message code="procession.description"/>
<jstl:out value="${procession.description}"/>
<br/>
<spring:message code="procession.moment"/>
<jstl:out value="${procession.moment}"/>
<br/>
<spring:message code="procession.ticker"/>
<jstl:out value="${procession.ticker}"/>
<br/>
<spring:message code="procession.finalMode"/>
<jstl:out value="${procession.finalMode}"/>
<br/>
<spring:message code="procession.brotherhood"/>
<jstl:out value="${procession.brotherhood}"/>
<br/>

<!-- Actions -->


<a href="procession/brotherhood/edit.do?${procession.id}">
<spring:message code="procession.edit"/></a>
<input type="button" name="back"
		value="<spring:message code="procession.back" />"
		onclick="javascript: relativeRedir('procession/brotherhood/list.do');" />
</security:authorize>