<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>RicercaPersone</title>
<s:head />
</head>
<body>

	<div id="tornaHome">
		<a href="index.jsp"><img alt="" src="image/home.png" height="80px" /></a>
	</div>
	
		<h1>Ricerca Persone Competenti</h1>
		<h3>Form di ricerca persone da competenza e livello:</h3>
		
		<s:form action="RicercaPersona" id="formRicerca">
			<s:select name="scegliCompetenza" label="Scegli la competenza" list="competenze" headerKey="-1" headerValue="--Scegli competenza--"/>
			<s:select name="scegliLivello" label="Scegli il livello" list="livello" />
			<s:select name="scegliCompetenzaSeconda" label="Scegli la seconda competenza" list="competenzeSeconda" headerKey="-1" headerValue="--Scegli seconda competenza--"/>
			<s:select name="scegliLivelloSecondo" label="Scegli il livello" list="livelloSecondo" />
			<s:submit key="submit" value="Ricerca" name="submit" />
		</s:form>
		
		<s:if test="scegliCompetenza == -1">
			<p class="errorMessage">SCEGLI ALMENO LA PRIMA COMPETENZA</p>
		</s:if>
		
		<s:if test="listaElementi != null">
			<s:if test="listaElementi.size() != 0">
				<table id="tabellaPersone">
					<tr>
						<th>ID</th>
						<th>NOME</th>
						<th>COGNOME</th>
						<th>PAESE</th>
						<th>ETA'</th>
						<th>N°TEL</th>
						<th>E-MAIL</th>
						<th>NOTE</th>
						<th>COMPETENZE</th>
					</tr>
					
					<s:iterator value="listaElementi">
						<tr>
							<td class="primavoce"><s:property value="id_protocollo" /></td>
							<td class="voce"><s:property value="nome" /></td>
							<td class="voce"><s:property value="cognome" /></td>
							<td class="voce"><s:property value="paese" /></td>
							<td class="voce"><s:property value="eta" /></td>
							<td class="voce"><s:property value="telefono" /></td>
							<td class="voce"><s:property value="email" /></td>
							<td class="vocenota"><s:property value="notePersona" /></td>
							<td class="ultimavoce">		        				
		        				<a href="<s:url action="ListaCompetenzePersona">
			            					<s:param name="id_protocollo" value="id_protocollo"/>
			        					</s:url>" target="_blank">
			        					Visualizza Competenze
			        			</a>
	        				</td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<s:if test="listaElementi.size()==0 && !scegliCompetenza.equals('-1')">
					<p class="errorMessage">NESSUNA PERSONA CON LE COMPETENZE SELEZIONATE</p>
			</s:if>
		</s:if>
		
		<div id="closePage">
			<a class="close" href="index.jsp">Back Menù</a>
		</div>

</body>
</html>