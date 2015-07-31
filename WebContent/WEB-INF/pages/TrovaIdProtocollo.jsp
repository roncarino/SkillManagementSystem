<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>TrovaIdProtocollo</title>
<s:head />
</head>
<body>
		<div id="tornaHome">
			<a href="index.jsp"><img alt="" src="image/home.png" height="80px" /></a>
		</div>
		
		<h1>Ricerca ID Protocollo Persona</h1>
		<h3>Form di ricerca ID protocollo da nome e cognome:</h3>
			
		<s:form action="TrovaIdProtocollo" id="formTrovaId">
			<s:textfield name="nomePersona" label="Nome" />
			<s:textfield name="cognomePersona" label="Cognome" />
			<s:submit key="submit" value="Ricerca" name="submit" />
		</s:form>

		<s:if test="listaElementi != null">	
			<s:if test="listaElementi.size() != 0">
				<table id="id_protocollo">
					<tr>
						<th>ID</th>
						<th>NOME</th>
						<th>COGNOME</th>
						<th>PAESE</th>
						<th>PROFILO</th>
						<th>DA CONSIDERARE</th>
						<th>COMPETENZE</th>
						<th>UPDATE DATI</th>
					</tr>	
					
					<s:iterator value="listaElementi">
						<tr>
							<td class="primavoce"><s:property value="id_protocollo" /></td> 
							<td class="voce"><s:property value="nome" /></td>
							<td class="voce"><s:property value="cognome" /></td>
							<td class="voce"><s:property value="paese" /></td>
							<td class="voce"><s:property value="profilo" /></td>
							<td class="voce"><s:property value="da_considerare" /></td>
							<td class="voce">		        				
		        				<a href="<s:url action="ListaCompetenzePersona">
			            					<s:param name="id_protocollo" value="id_protocollo"/>
			        					</s:url>" target="_blank">
			        					Visualizza Competenze
			        			</a>
	        				</td>
	        				<td class="ultimavoce">		        				
		        				<a href="<s:url action="UpdateDatiPersona">
  			            					<s:param name="id_protocollo" value="id_protocollo"/>
			        					</s:url>" target="_blank">
			        					Modifica Dati
			        			</a>
	        				</td>	
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<s:if test="listaElementi.size() == 0">
				<p style="color:red; font-weight: bold; font-size: 22px;">NESSUNA PERSONA TROVATA</p>
			</s:if>
		</s:if>
			
		<div id="closePage">
			<a class="close" href="index.jsp">Back Menù</a>
		</div>
</body>
</html>