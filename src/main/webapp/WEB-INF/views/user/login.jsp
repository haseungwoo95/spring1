<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>로그인</h1>
<div>${requestScope.errMsg}</div>
<form action="login" method="post">
    <div><input type="text" name="uid" placeholder="아이디" value="asd"></div>
    <div><input type="password" name="upw" placeholder="비밀번호" value="123"></div>
    <div><input type="submit" value="로그인"></div>
</form>
<div><a href="join">회원가입</a></div>
