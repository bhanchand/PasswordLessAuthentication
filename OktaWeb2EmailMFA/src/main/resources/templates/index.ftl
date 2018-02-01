<#import "/spring.ftl" as spring/>
 
<!DOCTYPE HTML>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>

          
      <link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.6/css/bootstrap.min.css">
 	  <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
	  <script src="https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.6/bootstrap.min.js"></script>

	<style>
	.hero-unit {
		padding: 60px;/* CHANGE PADDING HERE*/
		margin-bottom: 30px; 
		font-size: 18px;
		font-weight: 200;
		line-height: 30px;
		color: inherit;
		background-color: #eeeeee; /* CHANGE BACKGROUND HERE*/
		-webkit-border-radius: 6px;
		-moz-border-radius: 6px;
		border-radius: 6px;
		}
	</style>	
   </head>
    
   <body>
   <div class="container" width="70%">
      <#if message??>
      	<h2>${message}</h2>
      </#if>
     
      <div class="hero-unit">
  		<h1>Password-less MF Authentication</h1>
  		<p>This is a simple Springboot application that demonstrates password less authetication using the Okta Platforn.</p>
  		<p>
    		<a class="btn btn-primary btn-large" href="<@spring.url '/register'/>">
      		See it in Action
    		</a>
  	</p>
	</div> 
   </div>    
   </body>
    
</html>