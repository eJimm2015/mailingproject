<%@page import="ejb.mailing.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ejb.mailing.entity.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
List<Message> messages = (ArrayList<Message>)request.getAttribute("messages");
String type = (String)request.getAttribute("type");
User user = (User) request.getSession().getAttribute("user");
%>
<html style="height : 100%;">
<head>
	<title><%= type %></title>
  <link rel="stylesheet" type="text/css" href="include/semantic.css">
  <script type="text/javascript" src="include/jquery.js"></script>
  <script type="text/javascript" src="include/semantic.js"></script> 
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
</head>
<body>
  <div class="ui sidebar left inverted vertical container menu">
    <a href="./Envoyer" class="item">
    <i class="send icon"></i>
      Envoyer un message
    </a>
    <a href="./Boite" class="item">
    <i class="inbox icon"></i>
      Boite de réception
    </a>
    <a href="./Envoi" class="item">
    <i class="mail forward icon"></i>
      Boite d'envoi
    </a>
  </div>
 
  <div class="ui fixed top inverted menu">
		  	<div class="ui container">
		  		<a id="toggle" class="item">
		  		<i class="sidebar icon"></i>
		  		Menu
		  		</a>
		  		<a class="item">
		  		<%= user.getPrenom() %> <%= user.getNom() %>
		  		</a>
		  		<div class="right menu">
    			<a href="./Deconnexion" class="item">
    			<i class="sign out icon"></i>
    			Déconnexion</a>
  				</div>
		  	</div>
  		</div>


  	<!-- DYNAMIC CONTENT -->
  <div class="pusher">
   	<div class="ui vertical center aligned very padded segment" style="min-height : 80%">
   	 	<div class="ui container">
   		<h2 class="ui icon header">
		  <i class="inbox icon"></i>
		  <div class="content">
		    <%= type %>
		  </div>
		</h2>
   		<div class="ui divider"></div>
  <form method="POST" action="./Supprimer?type=<%=type%>">
  	  	<table class="ui center aligned compact celled definition table">
  <thead>
    <tr>
      <th></th>
      <th><%if(type.equals("Boite de réception")) {%>
      Expéditeur
      <%} else { %>
      Destinataire
      <%} %>
      </th>
      <th>Objet du message</th>
      <th>Date d'envoi</th>
    </tr>
  </thead>
  <tbody>
	<% for(int i=0; i<messages.size(); i++){ %>
	    <tr
	     <% if(!messages.get(i).isLu() && type.equals("Boite de réception")){ %> 
	    style="font-weight : bold; font-style : italic;"
	    <% } %>
	    >
  	    <td class="center aligned">
        	<div class="ui fitted checkbox">
		    <input type="checkbox" name="delete" value="<%=messages.get(i).getId()%>">
		    <label></label>
		  	</div>
      </td>
      <td><%if(type.equals("Boite de réception")) {%>
      <%= messages.get(i).getExpediteur().getPrenom() %> <%= messages.get(i).getExpediteur().getNom() %>
      <%}else { %>
      <%= messages.get(i).getDestinataire().getPrenom() %> <%= messages.get(i).getDestinataire().getNom() %>
      <%} %>
      </td>
      <td><a href="./MessageDisplay?id=<%= messages.get(i).getId()%>&type=<%= type %>"><%= messages.get(i).getObjet() %></a></td>
      <td><%= messages.get(i).getTempsEnvoi()%></td>	
     </tr>	
	
	<%}%>
  </tbody>
  <tfoot>
    <tr>
      <th></th>
      <th colspan="4">
        <button type="submit" class="ui right floated small labeled negative basic icon button">
         
				<i class="remove icon"></i>
				Supprimer sélection
        </button>
      </th>
    </tr>
  </tfoot>
</table>
  </form>
  </div>

 
  </div>
  <script>
  	$('#toggle').click(function(){ 
  		$('.ui.sidebar').sidebar('toggle'); 
  	});

  </script>
</body>
</body>
</html>