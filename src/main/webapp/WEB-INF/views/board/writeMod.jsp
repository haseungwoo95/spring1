<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="writeMod" method="post">
  <input type="hidden" name="iboard" value="${requestScope.data.iboard == null ? 0 : requestScope.data.iboard}">
  <div><input type="text" name="title" placeholder="제목" value="${requestScope.data.title}"></div>
  <div><textarea name="ctnt" placeholder="내용">${requestScope.data.ctnt}</textarea></div>
  <div>
    <input type="submit" value="등록">
    <input type="reset" value="새로작성">
  </div>
</form>
