<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
      <input type="submit" value="出勤" name="timein">
<input type="submit" value="退勤" name="timeout">
    </div>
  </div>



<script type="text/javascript" src="<%= request.getContextPath() %>/js/clock.js"></script>
    </c:param>
</c:import>