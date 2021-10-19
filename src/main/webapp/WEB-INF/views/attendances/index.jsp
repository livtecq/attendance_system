<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actAtt" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>勤怠　一覧</h2>
        <table id="attendance_list">
            <tbody>
                <tr>
                    <th class="attendance_name">氏名</th>
                    <th class="attendance_date">日付</th>
                    <th class="attendance_timein">出勤時刻</th>
                    <th class="attendance_timeout">退勤時刻</th>
                    <th class="attendance_bodytemperature">体温</th>
                    <th class="attendance_action">操作</th>
                </tr>
                <c:forEach var="attendance" items="${attendances}" varStatus="status">
                    <fmt:parseDate value="${attendance.attendanceDate}" pattern="yyyy-MM-dd" var="attendanceDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="attendance_name"><c:out value="${attendance.employee.name}" /></td>
                        <td class="attendance_date"><fmt:formatDate value='${attendanceDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="attendance_timein">${attendance.timeIn}</td>
                        <td class="attendance_timeout">${attendance.timeOut}</td>
                        <td class="attendance_bodytemperature">${attendance.bodyTemperature}</td>
                        <td class="attendance_action"><a href="<c:url value='?action=${actAtt}&command=${commShow}&id=${attendance.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${attendances_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((attendances_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actAtt}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>