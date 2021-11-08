<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_TIMEOUT.getValue()}" />
<c:set var="commBody" value="${ForwardConst.CMD_BODYTEMPERATURE.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>勤怠管理システムへようこそ</h2>


          <div class="container">
    <div class="clock">
      <p class="clock-date"></p>
      <p class="clock-time"></p>

<form method="POST" action="<c:url value='?action=${action}&command=${commCrt}' />">
<input type="submit" name="${AttributeConst.ATT_IN.getValue()}" value="出勤" />
<input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
</form>
<form method="POST" action="<c:url value='?action=${action}&command=${commOut}' />">
<input type="submit" name="${AttributeConst.ATT_OUT.getValue()}" value="退勤" />
<input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
</form>

    </div>
  </div>
<div class="container-body">
<label for="${AttributeConst.ATT_BODY.getValue()}">体温 </label>
<form method="POST" action="<c:url value='?action=${action}&command=${commBody}' />">
<input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<input type="number" max="41.0" min="35.0" step="0.1" name="${AttributeConst.ATT_BODY.getValue()}" value="${attendance.bodyTemperature}" />℃<button type="submit">登録</button>
</form>
</div>




<script type="text/javascript" src="<%= request.getContextPath() %>/js/clock.js"></script>
    </c:param>
</c:import>