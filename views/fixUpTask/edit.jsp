<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action="fixUpTask/customer/edit.do" modelAttribute="fixUpTask">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="ticker"/>
	<form:hidden path="moment"/>
	<form:hidden path="applications"/>
	<form:hidden path="phases"/>
	
	
	<fieldset>
	<legend align="left"><spring:message code="fixUpTask.edit.fields"/></legend>
	
	<form:label path="description">
			<spring:message code="fixUpTask.edit.description" />:
		</form:label>
		<form:input path="description"/>
		
		<form:errors cssClass="error" path="description" />		
		
		<br/>
		<br/>
	
		<form:label path="address">
			<spring:message code="fixUpTask.edit.address" />:
		</form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address" />		
		
		<br/>
		<br/>
		<form:label path="maximumPrice">
			<spring:message code="fixUpTask.edit.maxPrice"/>
		</form:label>
		<form:input path="maximumPrice"/>
		<form:errors cssClass="error" path="maximumPrice" />	
	
		
		<br/>
		<br/>
	
		<form:label path="startDate">
			<spring:message code="fixUpTask.edit.startDate" />:
		</form:label>
		<form:input path="startDate"/>
		<form:errors cssClass="error" path="startDate" />	
			
		<br/>
		<br/>
		
		<form:label path="endDate">
			<spring:message code="fixUpTask.edit.endDate" />:
		</form:label>
		<form:input path="endDate"/>
		<form:errors cssClass="error" path="endDate" />
		
		<br/>
		<br/>
		<form:label path="warranty">
			<spring:message code="fixUpTask.edit.warranties"/>
		</form:label>
		<form:select id="warranties" path="warranty" multiple="true">
			<form:option value="0" label="----"/>
			<form:options items="${warranties}"/>
		</form:select>
		<form:label path="category">
			<spring:message code="fixUpTask.edit.category" />:
		</form:label>
		<form:select id ="category" path="category">
			<form:option value="0" label="----"/>
			<form:options items="${categories}" 
			
		 	itemValue = "id"
			itemLabel="name"/>
		</form:select>
		
		<br/>
		<br/>

		</fieldset>
	<input type="submit" name="save"
		value="<spring:message code="fixUpTask.edit.save" />" />&nbsp; 
	<jstl:if test="${fixUpTask.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="fixUpTask.edit.delete" />"
			onclick="return confirm('<spring:message code="fixUpTask.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.edit.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/customer/list.do');" />
	<br />
	
</form:form>
</security:authorize>