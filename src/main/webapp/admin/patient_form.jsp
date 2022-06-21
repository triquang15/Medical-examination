<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css" >
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="..//css/richtext.min.css">	
	
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<title>
	<c:if test="${patient != null}">
		Edit Patient
	</c:if>
	<c:if test="${patient == null}">
		Create New Patient
	</c:if>
</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${patient != null}">
				Edit Patient
			</c:if>
			<c:if test="${patient == null}">
				Create New Patient
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${patient != null}">
			<form action="update_patient" method="post" id="patientForm">
			<input type="hidden" name="patientId" value="${patient.mabn}">
		</c:if>
		<c:if test="${patient == null}">
			<form action="create_patient" method="post" id="patientForm">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Patient Name:</td>
				<td align="left"><input type="text" id="name" name="name" size="20" value="${patient.tenbn}" /></td>
			</tr>
			<tr>
				<td align="right">Birthday:</td>
				<td align="left"><input type="text" id="birthDay" name="birthDay" size="20" 
					value="<fmt:formatDate pattern='MM/dd/yyyy' value='${patient.ngaysinh}' />" /></td>
			</tr>	
			<tr>
			
				<td align="right">Gender:</td>
				<td>
					<select class="form-control form-control-lg" name="gender" id="gender" >					
						  		<option value="Male">Male</option>
						  		<option value="Female">Female</option>
						  		<option value="Other">Other</option>					 
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
	
	$("#patientForm").validate({
		rules: {	
			name: "required",
			birthDay: "required",
			gender: "required"
		},
		
		messages: {
			name: "Please enter Patient.",
			birthDay: "Please enter birthday of the patient",
			gender: "Please select gender of the patient"
		}
	});
	
	
	$("#buttonCancel").click(function() {
		history.go(-1);
	});	
});
</script>
</html>