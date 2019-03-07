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
			<display:column property="brotherhood.title" titleKey="float.brotherhood"/>
			</display:table>