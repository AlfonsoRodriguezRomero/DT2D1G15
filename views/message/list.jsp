<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
	
	<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="messages" requestURI="${requestURI}" id="row">
		
		<!-- Attributes -->
		<spring:message code="complaint.dateFormat" var="dateFormat"/>
		<display:column property="moment" titleKey="message.moment" format="${dateFormat}" 
		sortable="true" />
		
		<display:column property="subject" titleKey="message.subject" sortable="false" />
		
		<display:column property="body" titleKey="message.body" sortable="false" />
		
		<display:column property="sender.name" titleKey="message.sender" sortable="false" />
		
		<!-- Actions -->
		<display:column>
			<a href="message/edit.do?messageId=${row.id}"> 
				<spring:message	code="message.edit" />
			</a>
		</display:column>
	</display:table>
<div>
	<a href="message/create.do">
		<spring:message code="message.create"/>
	</a>
</div>
</security:authorize>
