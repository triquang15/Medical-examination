<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Medical Examination Department Management Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Medical Examination Department Management</h2>
		<h3><a href="department_form.jsp">Create  New Medical Examination Department	</a></h3>
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
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="khoa" items="${listKhoa}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${khoa.makhoa}</td>
				<td>${khoa.tenkhoa}</td>
				<td>
					<a href="edit_department?id=${khoa.makhoa}">Edit</a> &nbsp;
					<a href="javascript:void(0);" class="deleteLink" id="${khoa.makhoa}">Delete</a>
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
					makhoa = $(this).attr("id");
					if (confirm('Are you sure you want to delete the Medical Examination Department with ID ' +  makhoa + '?')) {
						window.location = 'delete_department?id=' + makhoa;
					}					
				});
			});
		});	
	</script>
</body>
</html>