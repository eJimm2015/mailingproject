<%@page import="ejb.mailing.entity.User"%>
<%@page import="ejb.mailing.entity.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%Message message = (Message)request.getAttribute("message"); 
User user = (User) request.getSession().getAttribute("user");

%>
<!DOCTYPE html>
<html>
<head>
	<title>Message</title>
  <link rel="stylesheet" type="text/css" href="include/semantic.css">
  <script type="text/javascript" src="include/jquery.js"></script>
  <script type="text/javascript" src="include/semantic.js"></script> 
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

</head>
<body>
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
   	<div class="ui vertical very padded segment">
   	 	<div class="ui container">
   		<h2 class="ui center aligned icon header">
		  <i class="inbox icon"></i>
		  <div class="content">
		    Boite de réception
		  </div>
		</h2>
   		<div class="ui divider"></div>
      <div class="ui stacked segment">
      <span class="ui right floated button">1<%= message.getTempsEnvoi().toString()%></span>
      <span class="ui sub header"><%=message.getExpediteur().getPrenom()%> <%=message.getExpediteur().getNom()%></span><br/>
      <span><%=message.getExpediteur().getMail()%></span>
        <div class="ui divider"></div>
        <p><b>Objet : </b><%=message.getObjet() %></p>
        <div class="ui divider"></div>
        <%=message.getCorps() %>
        <div class="ui divider"></div>
      </div>
     </div>

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