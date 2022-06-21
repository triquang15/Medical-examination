<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<link rel="stylesheet" href="../css/style.css" >
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="..//css/richtext.min.css">	
	
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<title>
	<c:if test="${doctor != null}">
		Edit Doctor
	</c:if>
	<c:if test="${doctor == null}">
		Create New Doctor
	</c:if>
</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${doctor != null}">
				Edit Doctor
			</c:if>
			<c:if test="${doctor == null}">
				Create New Doctor
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${doctor != null}">
			<form action="update_doctor" method="post" id="doctorForm">
			<input type="hidden" name="doctorId" value="${doctor.mabs}">
		</c:if>
		<c:if test="${doctor == null}">
			<form action="create_doctor" method="post" id="doctorForm">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td align="left"><input type="text" id="name" name="name" size="20" value="${doctor.tenbs}" /></td>
			</tr>
			<tr>
				<td align="right">Birthday:</td>
				<td align="left"><input type="text" id="birthDay" name="birthDay" size="20" 
					value="<fmt:formatDate pattern='MM/dd/yyyy' value='${doctor.ngaysinh}' />" /></td>
			</tr>
			
			<tr>
				<td align="right">Schedule:</td>
				<td align="left">
					<select name=department>
						<c:forEach items="${listDepartment }" var="department">
							<option value="${department.makhoa }">
								${department.tenkhoa }
							</option>
						</c:forEach>
					</select>
				</td>
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
	$('#birthDay').datepicker();
	
	$("#doctorForm").validate({
		rules: {	
			name: "required",
			birthDay: "required",
		},
		
		messages: {
			name: "Please enter doctor name",
			birthDay: "Please enter birthday of the patient",
			
		}
	});
	
	$("#buttonCancel").click(function() {
		history.go(-1);
	});	
});
</script>
</html>
</body>
</html>