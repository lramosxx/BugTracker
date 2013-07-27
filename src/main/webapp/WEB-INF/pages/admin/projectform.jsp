<%--
  Created by IntelliJ IDEA.
  User: luiza
  Date: 15/07/13
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectDetail.title"/></title>
    <meta name="menu" content="ProjectMenu"/>
</head>

<div class="span3">
    <h2><fmt:message key='projectDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="project" method="post" action="/admin/projectform" id="projectForm"
               cssClass="well form-horizontal" onsubmit="selectAllUsers();">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="project.name"/>
            <div class="controls">
                <form:input path="name" id="name" maxlength="100"/>
                <form:errors path="name" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="project.description"/>
            <div class="controls">
                <form:input path="description" id="description" maxlength="200"/>
                <form:errors path="description" cssClass="help-inline"/>
            </div>
        </div>
            <div class="control-group">
                <appfuse:label styleClass="control-label" key="project.users"/>
                <div class="controls">
                    <div>
                        <select id="userList" name="userList" class="span11">
                            <option value="">Selecione um novo usu&#225;rio para adicionar...</option>
                            <c:forEach items="${newUsers}" var="u">
                                <c:set var="contains" value="false" />
                                <c:forEach var="ui" items="${project.users}">
                                    <c:if test="${ui.id eq u.id}">
                                        <c:set var="contains" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${contains == false}">
                                    <option value="${u.id}">${u.firstName}&#32;${u.lastName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <button type="button" class="btn" id="btnAddUser"> + </button>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div>
                        <form:select path="users" id="users" multiple="true" class="span11">
                            <c:forEach var="ui" items="${project.users}">
                                 <option value="${ui.id}">${ui.firstName}&#32;${ui.lastName}</option>
                            </c:forEach>
                        </form:select>
                        <button type="button" class="btn" id="btnRemoveUser"> - </button>
                        <form:errors path="users" cssClass="help-inline"/>
                    </div>
                </div>
            </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty project.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['projectForm']).focus();

        $("#btnAddUser").click(function(){
            addUser();
        });

        $("#btnRemoveUser").click(function(){
            removeUser();
        });
    });

    function addUser(){
        if ($("#userList").val() == "" || $("#userList").val() == null) return;

        var aux = true;
        $('#users option').each(function(a,b){
            if ($(b).val() == $("#userList").val()) aux = false;
        });

        if (aux){
            user = $("#userList");
            $('#users').append('<option value="'+user.val()+'" selected="'+user.val()+'">'+$("#userList option[value='"+user.val()+"']").text()+'</option>');
        }
    }

    function removeUser(){
            if ($("#users").val() == "" || $("#users").val() == null) return;
            $($("#users").val()).each(function(a,b){
                $('#users option[value="'+b+'"]').remove();
            });
    }

    function selectAllUsers(){
        $("#users option").each(function(a,b){
            $(b).attr('selected','selected');
        });
    }

</script>