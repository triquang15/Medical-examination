<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Doctor Management Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Doctor Management</h2>
		<h3><a href="new_doctor">Create Doctor</a></h3>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Medical Examination Department</th>
				<th>Doctor</th>			
				<th>Birthday</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="doctor" items="${listDoctors}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${doctor.khoa.tenkhoa}</td>
				<td>${doctor.tenbs}</td>
				<td><fmt:formatDate pattern='MM/dd/yyyy' value='${doctor.ngaysinh}' /></td>
				<td>
					<a href="edit_doctor?id=${doctor.mabs}">Edit</a> &nbsp;
					<a href="javascript:void(0);" class="deleteLink" id="${doctor.mabs}">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click", function() {
					doctorId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the doctor with ID ' +  doctorId + '?')) {
						window.location = 'delete_doctor?id=' + doctorId;
					}					
				});
			});
		});	
	</script>
</body>
</html>