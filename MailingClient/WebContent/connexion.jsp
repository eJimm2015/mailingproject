<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %> --%>

<!DOCTYPE html>
<html>	
<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

  <!-- Site Properties -->
  <title>Connexion</title>
  <link rel="stylesheet" type="text/css" href="include/semantic.css">
  <script type="text/javascript" src="include/jquery.js"></script>
  <script type="text/javascript" src="include/semantic.js"></script> 

  <style type="text/css">

    body {
      background-color: #DADADA;
    }
    body > .grid {
      height: 100%;
    }
    .column {
      max-width: 450px;
    }
  </style>
  <script>
  $(document)
    .ready(function() {
      $('.ui.form')
        .form({
          inline : true,
          fields: {
            email: {
              identifier  : 'email',
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
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Veuillez renseigner ce champs !'
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

<div class="ui middle aligned center aligned grid container">
  <div class="column">
    <h2 class="ui teal header">
    Connexion
    </h2>
    <form class="ui large form" method="POST" action="./Connexion">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="email" placeholder="E-mail address">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="Password">
          </div>
        </div>
        <div class="ui fluid large teal submit button">Connexion</div>
      </div>

      

    </form>

    <div class="ui message">
      Pas de compte ? <a href="./Inscription">Créer un compte</a>
    </div>
	<% if(request.getAttribute("error") != null) { %>
  <div class="ui negative message">
  <div class="header">
   Erreur de connexion
  </div>
  <p><%= request.getAttribute("error") %></p>
  </div>
	
	<%}%>

  </div>
</div>  

</body>

</html>

