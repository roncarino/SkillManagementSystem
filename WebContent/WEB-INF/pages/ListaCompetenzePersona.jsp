<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/SkillManagementSystem/style/style.css" />
	<link href="/SkillManagementSystem/style/favicon.ico" rel="icon" type="image/x-icon" />
	<title>ListaCompetenzePersona</title>
</head>
<body>
	<div id="tornaHome">
		<a class="close" href="JavaScript:window.close()"><img alt="" src="image/exit.png" height="80px" /></a>
	</div>
	
	<div align="center">	
		<table id="competenze">
			<tr>
				<s:iterator value="lista">
					<th><s:property value="%{key}"/></th>
					<tr>
						<s:iterator value="%{value}">
						<tr>
							<td><s:property value="nome_competenza" /></td>
							<td><s:property value="livello" /></td>
							<td>
								<s:if test="livello == 1">
									<img id="livello" src="image/livello1.jpg">
								</s:if>
								<s:if test="livello == 2">
									<img id="livello" src="image/livello2.jpg">
								</s:if>
								<s:if test="livello == 3">
									<img id="livello" src="image/livello3.jpg">
								</s:if>
								<s:if test="livello == 4">
									<img id="livello" src="image/livello4.jpg">
								</s:if>
							</td>	
						</tr>
						</s:iterator>
					</tr>
				</s:iterator>
			</tr>
		</table>
	</div>
	
	<div id="closePage">
		<a class="close" href="JavaScript:window.close()">Close Page</a>
	</div>
</body>
</html>