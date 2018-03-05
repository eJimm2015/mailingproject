<%@page import="ejb.mailing.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <% User user = (User) request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Envoyer un message</title>
  <link rel="stylesheet" type="text/css" href="include/semantic.css">
  <script type="text/javascript" src="include/jquery.js"></script>
  <script type="text/javascript" src="include/semantic.js"></script> 
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<script>
  $(document)
    .ready(function() {
      $('.ui.form')
        .form({
          inline : true,
          fields: {
            destinataire: {
              identifier  : 'destinataire',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Veuillez renseigner ce champs !'
                },
                {
                  type   : 'email',
                  prompt : 'Veuillez entrer un email valide !'
                }
              ]
            }
          }
        })
      ;
    })
  ;
  </script>
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
   	<div class="ui vertical center aligned very padded segment">
   	 	<div class="ui container">
   		<h2 class="ui icon header">
		  <i class="send icon"></i>
		  <div class="content">
		    Envoyer un mail
		  </div>
		</h2>
   		<div class="ui divider"></div>
		<% if(request.getAttribute("error") != null){ %>
		   <div class="ui negative message">
		  <div class="header">
		   Erreur d'envoi
		  </div>
		  <p><%= request.getAttribute("error") %></p>
		  </div>
		<%} %>
  	</div>  

  		<!-- MODIFIER INFOS PERSO FORM -->
  		<div class="ui vertical segment">
  			<div class="ui container">
  				<form class="ui form" method="POST" action="./Envoyer">
  				<h4 class="ui horizontal divider header">
				  <i class="tag icon"></i>
				  DESTINATAIRE
				</h4>
				  <div class="field">
				    <input type="text" name="destinataire" autofocus="autofocus" placeholder="Entrez le mail du destinataire">
				  </div>
				<h4 class="ui horizontal divider header">
				  <i class="tag icon"></i>
				  OBJECT
				</h4>
				  <div class="field">
				    <input type="text" name="objet" placeholder="Entrez l'objet du mail">
				  </div>
				 <h4 class="ui horizontal divider header">
				  <i class="tag icon"></i>
				  CORPS
				</h4>
				<div class="field">
				    <textarea name="corps" placeholder="Tapez votre message ..."></textarea>
				</div>
				<div class="ui right aligned vertical segment">
					<button type="submit" class="ui right labeled primary basic icon large button">
				  <i class="right arrow icon"></i>
				 Envoyer
				</button>
				</div>
				</form>
 

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