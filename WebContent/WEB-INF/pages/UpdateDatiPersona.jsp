<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>UpdateDatiPersona</title>
</head>
<body>

	<div id="tornaHome">
		<a class="close" href="JavaScript:window.close()"><img alt="" src="image/exit.png" height="80px" /></a>
	</div>

	<h1>Update Dati Persona</h1>

	<h3>Inserire i dati della persona da modificare in Persona:</h3>
	
	<s:form action="UpdateDatiPersona" id="formUpdate">
		<s:textfield name="id_protocollo" label="Id Protocollo" value="%{persona.id_protocollo}" readonly="true"/>
		<s:textfield name="nome" label="Nome" value="%{persona.nome}" readonly="true"/>
		<s:textfield name="cognome" label="Cognome" value="%{persona.cognome}" readonly="true"/>
	
		<s:textfield name="paese" label="Paese (PROVINCIA)" value="%{persona.paese}" /> 		
		<s:textfield name="indirizzo" label="Indirizzo" value="%{persona.indirizzo}"/>
		<s:textfield name="telefono" label="Telefono" value="%{persona.telefono}"/>
		<s:textfield name="email" label="E-Mail" value="%{persona.email}"/>
		<s:textarea name="notePersona" label="Note" value="%{persona.notePersona}" />
		<s:textarea name="status_lavorativo" label="Status Lavorativo" value="%{persona.status_lavorativo}"/>
		<s:textarea name="profilo" label="Profilo" value="%{persona.profilo}"/>
		<s:textarea name="da_considerare" label="Da Considerare" value="%{persona.da_considerare}"/>
		<s:submit value="Update Dati" action="UpdateDatiPersona" method="updateDati"/>
	</s:form>
		
	<div id="closePage">
		<a class="close" href="JavaScript:window.close()">Annulla</a>
	</div>
</body>
</html>