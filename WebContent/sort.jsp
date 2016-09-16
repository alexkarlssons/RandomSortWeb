<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session ="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/layout.css" rel="stylesheet" type="text/css">
<title>Sort</title>

<script type="text/javascript">
function checkField(form) {
    if (form.size.value == "") {
        alert("Field 'Size' is empty");
        return false;
    }
}
</script>

</head>
<body>
	<center><h1>HM Sort Demo</h1></center>
	<div class="main">
	<table id="t01">
		<tr>
			<th>Time</th>
			<th>Counter</th>
			<th>Start Array</th>
		</tr>
		<c:forEach items="${sortlist}" var="sort">
			<tr>
				<td>${sort.getTime()}</td>
				<td>${sort.getCounter()}</td>
				<td>${sort.getStartArray()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<h2> Sort new Array </h2>
	<form action="XmlWriteServlet" name="form" method="get" onsubmit="return checkField(this)">
		Size: <input type="number" name="size" min="1" max="10">
		<input type="submit">
	</form>
	</div>
</body>
</html>