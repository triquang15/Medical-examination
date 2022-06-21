<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>	
<title>
	<c:if test="${khoa != null}">
		Edit Medical Examination Department
	</c:if>
	<c:if test="${khoa == null}">
		Create New Medical Examination Department
	</c:if>
</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${khoa != null}">
				Edit Medical Examination Department
			</c:if>
			<c:if test="${khoa == null}">
				Create New Medical Examination Department
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${khoa != null}">
			<form action="update_department" method="post" id="departmentForm">
			<input type="hidden" name="departmentId" value="${khoa.makhoa}">
		</c:if>
		<c:if test="${khoa == null}">
			<form action="create_department" method="post" id="departmentForm">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td align="left"><input type="text" id="name" name="name" size="20" value="${khoa.tenkhoa}" /></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>				
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#departmentForm").validate({
		rules: {	
			name: "required",
		},
		
		messages: {
			name: "Please enter Medical Examination Department",
		}
	});
	
	$("#buttonCancel").click(function() {
		history.go(-1);
	});	
});
</script>
</html>