<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New Schedule</title>
	
	<link rel="stylesheet" href="../css/style.css" >
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="..//css/richtext.min.css">	
	
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<title>
	<c:if test="${schedule != null}">
		Edit schedule
	</c:if>
	<c:if test="${schedule == null}">
		Create New schedule
	</c:if>
</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${schedule != null}">
				Edit schedule
			</c:if>
			<c:if test="${schedule == null}">
				Create New schedule
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${schedule != null}">
			<form action="update_schedule" method="post" id="scheduleForm">
			<input type="hidden" name="scheduleId" value="${schedule.id}">
		</c:if>
		<c:if test="${schedule == null}">
			<form action="create_schedule" method="post" id="scheduleForm">
		</c:if>
		
		<table class="form">
		<tr>
				<td  align="right">Doctor:</td>
				<td >
					<select name="bacsi">
						<c:forEach items="${listDoctor}" var="bacsi">
							<c:if test="${bacsi.mabs eq schedule.bacsi.mabs}">
								<option value="${bacsi.mabs}" selected>
							</c:if>
							<c:if test="${bacsi.mabs ne schedule.bacsi.mabs}">
								<option value="${bacsi.mabs}">
							</c:if>							
								${bacsi.tenbs}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			
			<tr>
				<td  align="right">Patient:</td>
				<td >
					<select name="benhnhan">
						<c:forEach items="${listPatient}" var="benhnhan">
							<c:if test="${benhnhan.mabn eq schedule.benhnhan.mabn}">
								<option value="${benhnhan.mabn}" selected>
							</c:if>
							<c:if test="${benhnhan.mabn ne schedule.benhnhan.mabn}">
								<option value="${benhnhan.mabn}">
							</c:if>							
								${benhnhan.tenbn}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title" name="title" size="20" value="${schedule.title}" /></td>
			</tr>
		
			<tr>
				<td align="right">Day of the examination</td>
				<td align="left"><input type="text" id="scheduleDay" name="scheduleDay" size="20" 
					value="<fmt:formatDate pattern='MM/dd/yyyy' value='${schedule.ngaykham}' />" /></td>
			</tr>	
			
			<tr>
				<td align="right">Content:</td>
				<td align="left">
					<textarea rows="5" cols="50" name="content" id="content">${schedule.noidung}</textarea>
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

	$('#scheduleDay').datepicker();
	$('#content').richText();
	
	$("#scheduleForm").validate({
		rules: {	
			bacsi: "required",
			benhnhan: "required",
			title: "required",
			scheduleDay: "required",
			content: "required",
			
		},
		
		messages: {
			bacsi: "Please select Doctor.",
			benhnhan: "Please select Patient.",
			title: "Please enter title.",
			scheduleDay: "Please enter scheduleDay of the patient",
			content: "Please enter content"		
		}
	});
	
	
	$("#buttonCancel").click(function() {
		history.go(-1);
	});	
});
</script>
</html>