<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Schedule Management Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Schedule Management</h2>
		<h3><a href="new_schedule">Create Schedule</a></h3>
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
				<th>Doctor</th>
				<th>Patient</th>
				<th>Title Schedule</th>
				<th>Day of the examination</th>
				<th>Content</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="schedule" items="${listSchedules}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${schedule.benhnhan.tenbn}</td>			
				<td>${schedule.bacsi.tenbs}</td>
				<td>${schedule.title}</td>
				<td><fmt:formatDate pattern='MM/dd/yyyy' value='${schedule.ngaykham}' /></td>
				<td>${schedule.noidung}</td>
				<td>
					<a href="edit_schedule?id=${schedule.id}">Edit</a> &nbsp;
					<a href="javascript:void(0);" class="deleteLink" id="${schedule.id}">Delete</a>
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
					scheduleId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the schedule with ID ' +  scheduleId + '?')) {
						window.location = 'delete_schedule?id=' + scheduleId;
					}					
				});
			});
		});	
	</script>
</body>
</html>