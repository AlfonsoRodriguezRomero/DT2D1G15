<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

		<display:table pagesize="5" class="displaytag"
			name="brotherhoods" requestURI="${requestURI}" id="row">
			
			<spring:message code="brotherhood.title" var="title"/>
		<display:column property="title" title="${title}"/>
		
		<spring:message code="brotherhood.establishmentDate" var="establishmentDate"/>
		<display:column property="establishmentDate" title="${establishmentDate}"/>
		
		<spring:message code="brotherhood.pictures" var="pictures"/>
		<display:column property="pictures" title="${pictures}"/>
		
			<display:column titleKey="brotherhood.members">
				<a href="member/list.do?brotherhoodId=${row.id}">
					<spring:message	code="brotherhood.list.members" /></a>
			</display:column>
		
			<display:column titleKey="brotherhood.processions">
				<a href="procession/list.do?brotherhoodId=${row.id}">
					<spring:message	code="brotherhood.list.processions" /></a>
			</display:column>
			
			<display:column titleKey="brotherhood.floats">
				<a href="float/list.do?brotherhoodId=${row.id}">
					<spring:message	code="brotherhood.list.floats" /></a>
			</display:column>
		</display:table>