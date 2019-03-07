<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="float.show.head"/>	
</h1>
<br/>
<spring:message code="float.show.title"/>
<jstl:out value="${float.title}"/>
<br/>
<spring:message code="float.show.description"/>
<jstl:out value="${float.description}"/>
<br/>
<spring:message code="float.show.pictures"/>
<jstl:out value="${float.pictures}"/>
<br/>
<spring:message code="float.show.brotherhood"/>
<jstl:out value="${float.brotherhood}"/>
<br/>

<!-- Actions -->
<security:authorize access="hasRole('BROTHERHOOD')">
<a href="brotherhood/show.do?${float.brotherhood.id}"><spring:message code="float.show.brotherhood"/></a>
<br/>
<spring:message code="float.show.brotherhood.title"/>
<jstl:out value="${float.brotherhood.title}"/>
<br/>
<spring:message code="float.show.brotherhood.establishmentDate"/>
<jstl:out value="${float.brotherhood.establishmentDate}"/>
<br/>
<spring:message code="float.show.brotherhood.pictures"/>
<jstl:out value="${float.brotherhood.pictures}"/>
<br/>
<a href="float/create.do?${float.id}">
<spring:message code="float.show"/></a>
<input type="button" name="cancel"
		value="<spring:message code="float.show.cancel" />"
		onclick="javascript: relativeRedir('float/brotherhood/list.do');" />
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
<input type="button" name="cancel"
		value="<spring:message code="float.show.cancel"/>"
		onclick="javascript: relativeRedir('float/brotherhood/list.do')"/>
</security:authorize>