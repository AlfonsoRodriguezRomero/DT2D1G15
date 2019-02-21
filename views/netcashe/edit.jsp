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
<form:form action="netcashe/customer/edit.do" modelAttribute="netcashe">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="ticker"/>
	<form:hidden path="customer"/>
	
	<jstl:if test="${netcashe.id != 0}">
		<form:hidden path="fixUpTask"/>
	</jstl:if>
	<jstl:if test="${netcashe.id == 0}">
		<form:label path="fixUpTask">
			<spring:message code="customer.fixUpTask" />:
		</form:label>
		<form:select id="fixUpTasks" path="fixUpTask">
			<form:options items="${fixUpTasks}" itemLabel="description" itemValue="id"/>
			<form:option value="0" label="----"/>
		</form:select>
		<form:errors cssClass="error" path="fixUpTask" />
		<br/>
	</jstl:if>
	
		<form:label path="moment">
			<spring:message code="netcashe.moment" />:
		</form:label>
		<form:input path="moment"/>
		<form:errors cssClass="error" path="moment" />
		<br/>
	
	<form:label path="body">
			<spring:message code="netcashe.body" />:
		</form:label>
		<form:input path="body"/>
		<form:errors cssClass="error" path="body" />
		<br/>
		
		<form:label path="picURL">
			<spring:message code="netcashe.picURL" />:
		</form:label>
		<form:input path="picURL"/>
		<form:errors cssClass="error" path="picURL" />
		<br/>
		
		<form:select path="finalMode" titleKey="netcashe.finalMode">
		<form:option value="Yes"><spring:message code="netcashe.yes"></spring:message></form:option>
		<form:option value="No"><spring:message code="netcashe.no"></spring:message></form:option>
		</form:select>
		
		<form:errors cssClass="error" path="finalMode" />
		<br/>
		
		<input type="submit" name="save"
		value="<spring:message code="netcashe.save" />" />&nbsp; 
	<jstl:if test="${netcashe.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="netcashe.delete" />"
			onclick="return confirm('<spring:message code="netcashe.confirm.delete" />')" />&nbsp;
	</jstl:if>
</form:form>
<input type="button" name="cancel"
		value="<spring:message code="netcashe.cancel" />"
		onclick="javascript: relativeRedir('netcashe/customer/list.do');" />
	<br />
</security:authorize>