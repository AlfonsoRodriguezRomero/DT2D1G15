<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action="application/handyWorker/edit.do" modelAttribute="application">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="fixUpTask" />
	
	<form:hidden path="rejectedCause" />
	<form:hidden path="creditCard" />
	<form:hidden path="status"/>
	

	<!-- Parámetros -->

	
	<form:label path="comment">
		<spring:message code="application.comment" />:
	</form:label>
	<form:textarea path="comment" />
	<form:errors cssClass="error" path="comment" />
	<br />
	
	
	
	<form:label path="offeredPrice">
		<spring:message code="application.offeredPrice.currency" />:
	</form:label>
	<form:input path="offeredPrice" />
	<form:errors cssClass="error" path="offeredPrice" />
	<br />
	<input type="submit" name="save"
		value="<spring:message code="application.save" />" />&nbsp; 
		
	
	<input type="button" name="cancel"
		value="<spring:message code="application.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/list.do');" />
	<br />
	</form:form>
	</security:authorize>
	
	
	
	<security:authorize access="hasRole('CUSTOMER')">
	
	<form:form action="application/customer/edit.do" modelAttribute="application">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="fixUpTask" />
	<form:hidden path="comment" />
	<form:hidden path="offeredPrice"/>
	<jstl:if test="${application.status=='pending'}">
	<form:hidden path="creditCard"/>
	<form:hidden path="rejectedCause"/>
	<form:label path="status">
	<spring:message code="application.status" />:
	</form:label>
	<form:select id="status" path="status" multiple="false">
			<form:option value="pending"/>
			<form:option value="rejected"/>
			<form:option value="accepted"/>
		</form:select>
	<br />
	</jstl:if>
	
	
	
	<jstl:if test="${application.status=='rejected'}">
	<form:hidden path="creditCard"/>
	<form:hidden path="status"/>
	<form:label path="rejectedCause">
	<spring:message code="application.rejectedCause"/>
	</form:label>
	<form:input path="rejectedCause"/>
	<form:errors cssClass="error" path="rejectedCause"/>
	<br/>
	
	</jstl:if>

<jstl:if test="${application.status=='accepted'}">

<form:hidden path="status"/>
<form:hidden path="rejectedCause"/>

<form:label path="creditCard">
	<spring:message code="application.creditCard"/>
	</form:label>
	<br/>
	<form:label path="creditCard.holderName">
				<spring:message code="application.holderName" />
			</form:label>
			<form:input path="creditCard.holderName" />
			<form:errors class="error" path="creditCard.holderName" />
			<br>

			<form:label path="creditCard.brandName">
				<spring:message code="application.brandName" />:
			</form:label>
			<form:select id="brandName" path="creditCard.brandName">
				<form:option value="0" label="  --  " />
				<form:option value="VISA" label="VISA" />
				<form:option value="MASTERCARD" label="MASTERCARD" />
				<form:option value="DINNERS" label="DINNERS" />
				<form:option value="DISCOVER" label="DISCOVER" />
				<form:option value="AMEX" label="AMEX" />
			</form:select>
			<form:errors class="error" path="creditCard.brandName" />
			<br>

			<form:label path="creditCard.number">
				<spring:message code="application.number" />
			</form:label>
			<form:input path="creditCard.number"/>
			<form:errors class="error" path="creditCard.number" />
			<br>

			<form:label path="creditCard.expirationMonth">
				<spring:message code="application.expirationMonth" />
			</form:label>
			<form:input path="creditCard.expirationMonth" type = "number"/> 
 			<form:errors class="error" path="creditCard.expirationMonth" /> 
			<br>
			<form:label path="creditCard.expirationYear">
				<spring:message code="application.expirationYear" />
			</form:label>
			<form:input path="creditCard.expirationYear" type = "number"/> 
 			<form:errors class="error" path="creditCard.expirationYear" /> 
			<br>

 			<form:label path="creditCard.CVV"> 
 				<spring:message code="application.CVV" /> 
 			</form:label> 
 			<form:input path="creditCard.CVV" type="number" /> 
			<form:errors class="error" path="creditCard.CVV" /> 
 			<br>
 			</jstl:if>

	<input type="submit" name="save"
		value="<spring:message code="application.save" />" />&nbsp; 
		
	
	<input type="button" name="cancel"
		value="<spring:message code="application.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/customer/list.do');" />
	<br />
</form:form>
</security:authorize>