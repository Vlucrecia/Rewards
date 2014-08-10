<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>


</head>
<body>
	<c:if test="${not empty errMsg}">
		<div class="errorblock">${errMsg}</div>
	</c:if>
	<b>Account Number : </b>${accountNo}
	<br>
	<c:if test="${empty result}">
		<b>No Rewards</b>
	</c:if>
	
	<table border="2">
		<th>
			Rewards
		</th>
		<c:forEach var="listValue" items="${result}">
			<tr>
				<td>${listValue.reward}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>