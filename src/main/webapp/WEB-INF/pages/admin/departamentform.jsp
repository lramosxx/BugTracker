<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 19/07/13
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="departamentDetail.title"/></title>
    <meta name="menu" content="DepartamentMenu"/>
</head>

<div class="span3">
    <h2><fmt:message key='departamentDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="departament" method="post" action="/admin/departamentform" id="departamentForm"
               cssClass="well form-horizontal">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="departament.name"/>
            <div class="controls">
                <form:input path="name" id="name" maxlength="100" class="span11"/>
                <form:errors path="name" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="departament.activities"/>
            <div class="controls">
                <div>
                <form:select path="activities" id="activities" multiple="true" class="span11">
                    <c:forEach items="${newActivities}" var="a">
                        <c:set var="contains" value="false" />
                        <c:forEach var="suba" items="${departament.activities}">
                            <c:if test="${suba.id eq a.id}">
                                <c:set var="contains" value="true" />
                            </c:if>
                        </c:forEach>
                        <option value="${a.id}" ${contains ? 'selected' : ''}>${a.name}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="activities" cssClass="help-inline"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty departament.id}">
                <button type="submit" class="btn" name="delete">
                    <i class="icon-trash"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>
            <button type="submit" class="btn" name="cancel">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['departamentForm']).focus();

    });
</script>