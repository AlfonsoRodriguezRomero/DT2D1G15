<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="netcashes" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	<spring:message code="netcashe.ticker" var="ticker"/>
	<display:column property="ticker" title="${ticker}"/>
	
	<spring:message code="netcashe.moment" var="moment"/>
	<display:column property="moment" title="${moment}"/>
	
	<spring:message code="netcashe.body" var="body"/>
	<display:column property="body" title="${body}"/>	
	
	<!-- Action Links -->
	
		<display:column>
			<a href="netcashe/customer/edit.do?netcasheId=${row.id}">
				<spring:message	code="netcashe.edit" />
			</a>
		</display:column>	
</display:table>
<br/>
		<div>
		<a href="netcashe/customer/create.do"> <spring:message
				code="netcashe.create" />
		</a>
	</div>
</security:authorize>
	

