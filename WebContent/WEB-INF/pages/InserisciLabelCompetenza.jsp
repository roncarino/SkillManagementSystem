<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>InsertLabelCompetenza</title>
</head>
<body>

	<div id="tornaHome">
		<a href="index.jsp"><img alt="" src="image/home.png" height="80px" /></a>
	</div>	
	
	<h1>Inserimento Manuale Competenza</h1>
	<h3>Form inserimento nuova LabelCompetenza</h3>
	
	<s:form action="InserisciLabelCompetenza" id="formLabelCompetenza">
		<s:textfield name="id_label_competenza" label="ID Label Competenza" />
		<s:textfield name="nome_competenza" label="Nome Competenza" />
		<s:textfield name="nome_tipologia" label="Nome Tipologia" />
		<s:submit value="Inserisci Competenza" />
	</s:form>
	
	<div id="closePage">
			<a class="close" href="index.jsp">Back Menù</a>
	</div>	
</body>
</html>