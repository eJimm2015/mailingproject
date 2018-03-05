<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

  <!-- Site Properties -->
  <title>Inscription</title>
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
		  
		    prenom : {
		  	identifier : 'prenom',
			rules : [
			{
				type : "empty",
				prompt : 'Veuillez renseigner ce champs !'
			}
			]
		  },
		  
		  nom : {
		  	identifier : 'nom',
			rules : [
			{
				type : "empty",
				prompt : 'Veuillez renseigner ce champs !'
			}
			]
		  },
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
            },
            password_confirm : {
              identifier  : 'password_confirm',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Veuillez renseigner ce champs !'
                },
				{
					type : 'match[password]',
					prompt : 'Les mots de passes correspondent pas !'
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
    Inscription
    </h2>
    <form class="ui large form" method="POST" action="./Inscription">
      <div class="ui stacked segment">
        <div class="field">
		  <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="prenom" placeholder="Prénom">
          </div>
		 </div>
		  <div class="field">
		  <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="nom" placeholder="Nom">
          </div>
		  </div>
		  <div class="field">
		  <div class="ui left icon input">
            <i class="mail icon"></i>
            <input type="text" name="email" placeholder="E-mail">
          </div>
		  </div>
  			<div class="field">
			<div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="Mot de passe">
          </div>
			</div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password_confirm" placeholder="Confirmer mot de passe">
          </div>
        </div>
        <div class="ui fluid large teal submit button">S'inscrire</div>
      </div>

      

    </form>

    <div class="ui message">
      Compte déjà créé ? <a href="./Connexion">Se connecter</a>
    </div>

<%if(request.getAttribute("error") != null) {%>
<div class="ui negative message">
<div class="header">
 Erreur de connexion
</div>
<p><%=request.getAttribute("error") %></p>
</div>
<%}%>

  </div>
</div>

    

</body>

</html>

