<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="float.show"/>	
</h1>
<br/>
<spring:message code="float.title"/>
<jstl:out value="${float.title}"/>
<br/>
<spring:message code="float.establishmentDate"/>
<jstl:out value="${float.establishmentDate}"/>
<br/>
<spring:message code="float.description"/>
<jstl:out value="${float.description}"/>
<br/>
<spring:message code="float.pictures"/>
<jstl:out value="${float.pictures}"/>
<br/>
<spring:message code="float.brotherhood"/>
<jstl:out value="${float.brotherhood}"/>
<br/>

<!-- Actions -->
<security:authorize access="hasRole('BROTHERHOOD')">
<a href="brotherhood/show.do?${float.brotherhood.id}"><spring:message code="float.brotherhood"/></a>
<br/>
<spring:message code="float.brotherhood.title"/>
<jstl:out value="${float.brotherhood.title}"/>
<br/>
<spring:message code="float.brotherhood.establishmentDate"/>
<jstl:out value="${float.brotherhood.establishmentDate}"/>
<br/>
<spring:message code="float.brotherhood.pictures"/>
<jstl:out value="${float.brotherhood.pictures}"/>
<br/>
<spring:message code="float.brotherhood.brotherhood"/>
<jstl:out value="${float.brotherhood.brotherhood}"/>
<br/>
<a href="float/create.do?${float.id}">
<spring:message code="float.show"/></a>
<input type="button" name="cancel"
		value="<spring:message code="float.cancel" />"
		onclick="javascript: relativeRedir('float/brotherhood/list.do');" />
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
<input type="button" name="cancel"
		value="<spring:message code="float.cancel"/>"
		onclick="javascript: relativeRedir('float/brotherhood/list.do')"/>
</security:authorize>