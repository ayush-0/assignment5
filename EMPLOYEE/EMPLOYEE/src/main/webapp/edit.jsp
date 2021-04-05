<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored = "false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nagarro.dto.Employee" %>

<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

input[type=submit] {
	background-color: #1E90FF;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	
}

input[type=submit]:hover {
  background-color: #4169E1;
}



button {
	background-color: #FF0000;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	
}

input[type=date], input[type = "text"]  {
   width: 100%; 
  padding: 12px; /* Some padding */ 
  border: 1px solid #ccc; /* Gray border */
  border-radius: 4px; /* Rounded borders */
  margin-top: 3px; /* Add a top margin */
  margin-bottom: 3px; /* Bottom margin */
}
		</style>
	<title>Home</title>
	</head>
	<body>
		<c:if test = "${hrmId != null}">
		<header>
			<div>
				<table width = "100%">
					<tr>
						<td>
							<h2>Welcome <c:out value = "${hrmId}"/> ! You can edit employee here.</h2>
						</td>
						<td align = "right">
							<form action="SignOut">
							<input type = "submit" value = "SignOut"/>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</header>
		<br>
		<div class = "container">
		<br>
		<br>
			<form action = "Update" method = "post">
			<table  align = "center">
				<tr>
					<td>Employee Code : </td>
					<td>
						<input type = "text" name = "empCode" value = "${employee.getEmpCode()}" readonly/>
					</td>
				</tr>
				<tr>
					<td>Employee Name : </td>
					<td>
						<input type = "text" name = "empName" value = "${employee.getEmpName()}"/>
					</td>
				</tr>
				<tr>
					<td>Employee Location : </td>
					<td>
						<input type = "text" name = "empLoc" value = "${employee.getEmpLoc()}"/>
					</td>
				</tr>
				<tr>
					<td>Employee E-Mail : </td>
					<td>
						<input type = "text" name = "empMail" value = "${employee.getEmpMail()}"/>
					</td>
				</tr>
				<tr>
					<td>Employee D.O.B : </td>
					<td>
						<input type = "date" name = "empDOB" value = "${employee.getEmpDOB()}"/>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align = "right">
						&nbsp;
						 <input  type = "submit" value = "Update" />
					</td>
				</tr>
			</table>
				<input type = "hidden" name = "hrmId" value = "${hrmId}" />  
			</form>
			<br>
			
		<form action="DeleteEmployee" align = "center">
			<input type="hidden" name="empCode" value="${employee.getEmpCode()}">
			<input type="hidden" name="hrmId" value="${hrmId}"> 
<!-- 			<input	class = "delete" type="submit" value="Delete"> -->
		<button>Delete</button>
		</form>
			<br>
			
			</div>		
		</c:if>
		<c:if test="${hrmId  == null}">
			<c:redirect url = "index.jsp"/>
		</c:if>
	</body>
	
</html>