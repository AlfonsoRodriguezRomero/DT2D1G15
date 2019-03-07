<%--
 * email.tag
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
 
<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>
<%@ attribute name="checkCode" required="true" %>

<%@ attribute name="readonly" required="false" %>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<%-- Definition --%>

<div>
	<form:label path="${path}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${path}" readonly="${readonly}" 
		onchange="check(this)" pattern="^(\d|\(|\)| |\+)+$"/>	
	<form:errors path="${path}" cssClass="error" />
</div>	

<script language='javascript' type='text/javascript'>
		
			var re = /^\+\d{1,3} \(\d{1,3}\) \d{4,}$/;
			var re2 = /^\+\d{1,3} \d{4,}$/;
			var re3 = /^\d{4,}$/;
		
		    function check(input) {
		    	var OK = re.exec(input.value);
		    	var OK2 = re2.exec(input.value);
		    	var OK3 = re3.exec(input.value);
		        if (!(OK || OK2 || OK3)) {
		            alert("<spring:message code="{checkCode}" />" );
		        }
		    }
</script>		
