<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList" %>
<%
	ArrayList<JavaBeans> contatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/phone_icon.png" />
<link  rel="stylesheet" href="css/style.css" >
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="btn1" >Novo contato</a>
	<table id="tabela" >
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			  <%  for(JavaBeans contato:contatos){  %>
			 <tr>
			 		<td><%= contato.getIdcon() %></td>
			 		<td><%=contato.getNome() %></td>
			 		<td><%=contato.getFone() %></td>
			 		<td><%=contato.getEmail() %></td>
			 		<td> 
			 			<a class="btn1" href="select?id=<%=contato.getIdcon() %>" >Editar</a>
			 			<a class="btn1" href="#" id="excluir" onclick="exluirContato(<%=contato.getIdcon() %>)" >Excluir</a>  
			 		</td>
			 </tr>
			 <%  } %>
		</tbody>
	</table>
	<script src="js/validados.js"  ></script>
</body>
</html>