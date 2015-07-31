<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>UploadExcel</title>
</head>
<body>

	<h1>Upload Skill Matrix</h1>
	<h3><em>Scegli il file...</em></h3>

	<s:form action="UploadFile"  method="post" enctype="multipart/form-data" id="formUpload">
		<s:file name="userFile" label="User File" />
		<s:submit value="Upload File" />
	</s:form>
	
	<div id="closePage">
			<a class="close" href="index.jsp">Back Menù</a>
	</div>
</body>
</html>