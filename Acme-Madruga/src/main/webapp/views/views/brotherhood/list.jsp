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
		
		<display:column property="title" titleKey="brotherhood.title"/>
		<display:column property="establishmentDate" titleKey="brotherhood.establishmentDate" 
		sortable="true" format="{0,date,dd/MM/yyyy}" />
		<display:column property="pictures" titleKey="brotherhood.pictures"/>
		<display:column property="members" titleKey="brotherhood.members"/>
		
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