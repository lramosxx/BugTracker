<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 27/07/13
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="statusDetail.title"/></title>
    <meta name="menu" content="StatusMenu"/>
</head>

<div class="span3">
    <h2><fmt:message key='statusDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="status" method="post" action="/admin/statusform" id="statusForm"
               cssClass="well form-horizontal">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="status.description"/>
            <div class="controls">
                <form:input path="description" id="description" maxlength="100"/>
                <form:errors path="description" cssClass="help-inline"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty status.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['statusForm']).focus();
    });
</script>