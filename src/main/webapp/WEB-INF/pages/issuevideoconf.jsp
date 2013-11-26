<%@ include file="/common/taglibs.jsp" %>
<head>
    <script type="text/javascript" src="<c:url value="/scripts/TB.js"/>"></script>
</head>


<div class="span12">
    <h2 class="offset1">
        <fmt:message key='issue.videoConference'>
            <fmt:param value="${issue.id}"/>
            <fmt:param value="${issue.summary}"/>
        </fmt:message>
    </h2>

    <div id="${issue.id}" name="${issue.id}" class="offset3"></div>
    <br>
    <br>

</div>

<script type="text/javascript">
    $('#' +${issue.id}).videoChatInit({ apiKey: ${command.apiKey},
        sessionId: '${command.sessionKey}',
        token: '${command.userToken}' });

</script>