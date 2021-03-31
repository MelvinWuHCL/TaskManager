<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Form</title>
</head>
<body>
	<form action="taskcreate" method="post">
	
	<label for="taskname">Task Name:</label><br>
	<input type="text" id="taskname" placeholder="Name Required" name="taskname" required><br><br>
	
	<label for="desc">Task Description:</label><br>
	<input type="text" id="desc" placeholder="Description Required" name="desc" required><br><br>
	
	<label for="sdate">Start Date:</label><br>
	<input type="date" id="sdate" name="sdate"><br><br>
	
	<label for="edate">End Date:</label><br>
	<input type="date" id="edate" name="edate"><br><br>
	
	<label for="sev">Severity</label><br>
	<select id="sev" name="sev"><br><br>
		<option value="high">High</option>
		<option value="med">Medium</option>
		<option value="low">Low</option>
	</select>
	<br>
	<input type="submit" value="Register" >
	</form>
	
</body>
</html>