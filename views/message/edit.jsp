<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<security:authorize access="isAuthenticated()">

	<form:form action="message/edit.do" modelAttribute="m">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="sender"/>
		<form:hidden path="moment"/>
		<form:hidden path="flagSpam"/>

		
		<jstl:if test="${m.id == 0}">
		<form:label path="subject">
			<spring:message code="message.subject" />:
		</form:label>
		<form:input path="subject"/>
		<form:errors cssClass="error" path="subject" />
		<br/>
		<br/>
		
		<form:label path="body">
			<spring:message code="message.body" />:
		</form:label>
		<form:input path="body"/>
		<form:errors cssClass="error" path="body" />
		<br/>
		<br/>
		
		<form:label path="tag">
			<spring:message code="message.tag" />:
		</form:label>
		<form:input path="tag"/>
		<form:errors cssClass="error" path="tag" />
		<br/>
		<br/>
		
		<form:label path="priority">
			<spring:message code="message.priority" />:
		</form:label>
		<form:select path="priority">
		<form:option value="HIGH"><spring:message code="message.priority.high"/></form:option>
		<form:option value="NEUTRAL"><spring:message code="message.priority.neutral"/></form:option>
		<form:option value="LOW"><spring:message code="message.priority.low"/></form:option>
		</form:select>
		<form:errors cssClass="error" path="priority" />
		<br/>
		<br/>
		<form:label path="recipient">
			<spring:message code="message.recipient"/>:
		</form:label>
		<form:select path="recipient">
			<form:options items="${listActors}" itemLabel="userAccount.username" itemValue="id"/>								 		
		</form:select>
		<form:errors cssClass="error" path="recipient"/>
		</jstl:if>
		<jstl:if test="${m.id==0 }">
		<br/>
		<br/>
		</jstl:if>
		 <!-- Editar boxes -->
	<%-- 	<jstl:if test="${m.id != 0}">
		<form:label path="boxes">
			<spring:message code="message.edit.boxes" />:
		</form:label>
		<form:select id ="boxes" path="boxes">
			<form:option value="0" label="---------"/>
			<form:options items="boxes" itemValue="name"/>
		</form:select> --%>
	
		<%-- </jstl:if> --%>
		<jstl:if test="${m.id==0 }">
		<input type="submit" name="save"
		value="<spring:message code="message.edit.save" />" />&nbsp; 
		</jstl:if>
	<jstl:if test="${m.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="message.edit.delete" />"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="message.edit.cancel" />"
		onclick="javascript: relativeRedir('box/list.do');" />
	<br />
	</form:form>
	<jstl:if test="${m.id!=0}">
	<br/>
	<div>
		<a href="message/move.do?messageId=${m.id}"> <spring:message
				code="message.move" />
		</a>
	</div>
	</jstl:if>
</security:authorize>