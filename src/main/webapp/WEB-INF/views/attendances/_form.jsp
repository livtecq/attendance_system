<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="${AttributeConst.ATT_IN.getValue()}">出勤時刻</label><br />
<input type="time" name="${AttributeConst.ATT_IN.getValue()}" value="${attendance.timeIn}" />
<br /><br />

<label for="${AttributeConst.ATT_OUT.getValue()}">退勤時刻</label><br />
<input type="time" name="${AttributeConst.ATT_OUT.getValue()}" value="${attendance.timeOut}" />
<br /><br />

<label for="${AttributeConst.ATT_BODY.getValue()}">体温</label><br />
<input type="number" max="41.0" min="35.0" step="0.1" name="${AttributeConst.ATT_BODY.getValue()}" value="${attendance.bodyTemperature}" />
<br /><br />
<input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>