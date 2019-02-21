<%@page import="domain.Category"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>




<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="socialprofiles" requestURI="${requestURI}" id="row">


	<display:column property="nick" titleKey="social.nick" />

 	<display:column property="socialNetwork" titleKey="social.socialNetwork" />

 	<display:column property="link" titleKey="social.link" />

	<display:column>	
			<a href="socialprofile/edit.do?socialprofileId=${row.id}">
			<spring:message code="social.edit.link" />
			</a>
	</display:column>
				
</display:table>

<div>
	<a href="socialprofile/create.do"> <spring:message
			code="social.list.create" />
	</a>
</div>