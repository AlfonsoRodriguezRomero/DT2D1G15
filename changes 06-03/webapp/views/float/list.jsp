<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

		<display:table pagesize="5" class="displaytag"
			name="floats" requestURI="${requestURI}" id="row">
		
			<display:column property="title" titleKey="float.title"/>	
			<display:column property="description" titleKey="float.description"/>
			<display:column property="pictures" titleKey="float.pictures"/>
			<display:column property="brotherhood" titleKey="float.brotherhood.title"/>
			
			<security:authorize access="hasRole('BROTHERHOOD')">
				<display:column>
					<a href="float/brotherhood/edit.do?floatId=${row.id}">
					<spring:message code="float.edit"></spring:message></a>
				</display:column>
				<display:column>
					<a href="float/brotherhood/show.do?floatId=${row.id}">
					<spring:message code="float.show"></spring:message></a>
				</display:column>
			</security:authorize>
		</display:table>

			<security:authorize access="hasRole('BROTHERHOOD')">
			
				<input type="submit" name="create"
					value="<spring:message code="float.create"></spring:message>"
					onclick="javascript:relativeRedir('float/brotherhood/create.do')" />
			</security:authorize>