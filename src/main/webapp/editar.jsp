<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.JavaBeans" %>
<% 
	JavaBeans contato =  (JavaBeans) request.getAttribute("contato");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link  rel="icon" href="imagens/phone_icon.png" >
<link  rel="stylesheet" href="css/style.css" >
</head>
<body>
	<img src="imagens/address_book_icon.png" />
	<div id="container" >	
		<h1>Editar contato</h1>
		
		<form name="fmrContato" id="fmrContato" >
		<input id="_method" type="hidden" name="_method" value="PUT">
		<input name="id" id="id" value="<%=contato.getIdcon()%>"  type="hidden" />
			<table>
				<tr>
					<td>
						<input name="nome" id="nome" placeholder="Nome" class="Caixa1" value="<%= contato.getNome() %>"  />
					</td>
				</tr>
				<tr>
					<td>
						<input name="fone" id="fone" placeholder="Telefone" class="Caixa2" value="<%= contato.getFone() %>"  />
					</td>
				</tr>
				<tr>
					<td>
						<input name="email" id="email" placeholder="Email" type="email" class="Caixa1" value="<%= contato.getEmail() %>"  />
					</td>
				</tr>
			</table>
			<input type="button" value="Atualizar" class="btn1" onclick="validar()" >
		</form>
	</div>
	<script src="js/validados.js"  ></script>
</body>
</html>