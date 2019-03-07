<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="procession.show.head"/>	
</h1>
<br/>
<spring:message code="procession.show.title"/>
<jstl:out value="${procession.title}"/>
<br/>
<spring:message code="procession.show.description"/>
<jstl:out value="${procession.description}"/>
<br/>
<spring:message code="procession.show.moment"/>
<jstl:out value="${procession.moment}"/>
<br/>
<spring:message code="procession.show.ticker"/>
<jstl:out value="${procession.ticker}"/>
<br/>
<spring:message code="procession.show.finalMode"/>
<jstl:out value="${procession.finalMode}"/>
<br/>
<spring:message code="procession.show.brotherhood"/>
<jstl:out value="${procession.brotherhood}"/>
<br/>

<!-- Actions -->
<security:authorize access="hasRole('BROTHERHOOD')">
<a href="brotherhood/show.do?${procession.brotherhood.id}"><spring:message code="float.show.brotherhood"/></a>
<br/>
<spring:message code="procession.show.brotherhood.title"/>
<jstl:out value="${procession.brotherhood.title}"/>
<br/>
<spring:message code="procession.show.brotherhood.description"/>
<jstl:out value="${procession.brotherhood.description}"/>
<br/>
<spring:message code="procession.show.brotherhood.moment"/>
<jstl:out value="${procession.brotherhood.moment}"/>
<br/>
<spring:message code="procession.show.brotherhood.ticker"/>
<jstl:out value="${procession.brotherhood.ticker}"/>
<br/>
<spring:message code="procession.show.brotherhood.finalMode"/>
<jstl:out value="${procession.brotherhood.finalMode}"/>
<br/>
<spring:message code="procession.show.brotherhood.brotherhood"/>
<jstl:out value="${procession.brotherhood.brotherhood}"/>
<br/>
<a href="procession/create.do?${procession.id}">
<spring:message code="procession.show"/></a>
<input type="button" name="cancel"
		value="<spring:message code="procession.show.cancel" />"
		onclick="javascript: relativeRedir('procession/brotherhood/list.do');" />
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
<input type="button" name="cancel"
		value="<spring:message code="procession.show.cancel"/>"
		onclick="javascript: relativeRedir('procession/brotherhood/list.do')"/>
</security:authorize>