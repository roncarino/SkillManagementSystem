<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>InsertAutomaticCompetenza</title>
</head>
<body>

	<div id="tornaHome">
		<a href="index.jsp"><img alt="" src="image/home.png" height="80px" /></a>
	</div>

	<h1>Inserimento Automatico Competenze</h1>
	<em>Upload del file eseguito con successo</em>
	<h3>Inserire i dati della persona a cui associare lo Skill Matrix:</h3>
	
	<s:form action="InsertAutomaticCompetenze" id="formDati">
		<s:textfield name="id_protocollo" label="id_protocollo" />
		<s:textfield name="nome" label="Nome"/>
		<s:textfield name="cognome" label="Cognome"/>
		<s:textfield name="paese" label="Paese (PROVINCIA)" /> 		
		<s:textfield name="indirizzo" label="Indirizzo"/>

		<s:date name="data_nascita" format="dd/MM/yyyy" />
		<s:textfield name="data_nascita" label="Data di nascita" />  
		
		<s:textfield name="telefono" label="Telefono"/>
		<s:textfield name="email" label="E-Mail"/>
		<s:textarea name="notePersona" label="Note Persona" />
		<s:submit value="Inserisci Dati" />
	</s:form>
	
	<div id="closePage">
			<a class="close" href="index.jsp">Back Menù</a>
	</div>
</body>
</html>
