<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Okta MFA Example</title>
			
  <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style1.css'/>"/>  
   
	
 	<link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.6/css/bootstrap.min.css">
 	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
	<script src="https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.6/bootstrap.min.js"></script>
	
  <link href="https://jankyco.oktapreview.com/js/sdk/okta-theme-1.0.0.css" type="text/css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400" type="text/css" rel="stylesheet">
 <link rel = 'stylesheet' href = 'https://ok1static.oktacdn.com/assets/js/sdk/okta-signin-widget/1.7.0/css/okta-sign-in.min.css'/>

		
<link rel = 'stylesheet' href = 'https://ok1static.oktacdn.com/assets/js/sdk/okta-signin-widget/1.7.0/css/okta-theme.css'/>

		
<style>
  #container {
    z-index: 101;
    position: absolute;
    top: 60px;
    left: 20px;
  }
  
  #container #okta-login-container .button {
    color: #ffffff;
    background-color: #3a3f44;
    border-color: #3a3f44;
    background-image: -webkit-linear-gradient(#484e55, #3a3f44 60%, #313539);
    background-image: -o-linear-gradient(#484e55, #3a3f44 60%, #313539);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#484e55), color-stop(60%, #3a3f44), to(#313539));
    background-image: linear-gradient(#484e55, #3a3f44 60%, #313539);
    background-repeat: no-repeat;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff484e55', endColorstr='#ff313539', GradientType=0);
    -webkit-filter: none;
    filter: none;
  }
  

	.hero-unit {
		padding: 60px;/* CHANGE PADDING HERE*/
		margin-bottom: 30px; 
		font-size: 18px;
		font-weight: 200;
		line-height: 30px;
		color: inherit;
		//background-color: #ffffff; /* CHANGE BACKGROUND HERE*/
		-webkit-border-radius: 6px;
		-moz-border-radius: 6px;
		border-radius: 6px;
		}

</style>
  	
 
</head>
<body>  

<div class="container" style="text-align: center;vertical-align: middle;line-height: 90px; ">
	<h1><font size="+2">Password-less Authentication</font></h1>
</div>
	
<div id="main">
	<div id="okta-login-container" >
		<div class="auth-container main-container no-beacon" id="okta-sign-in">	
			<div class="okta-sign-in-header auth-header">
				<img class="auth-org-logo" src="<@spring.url '/images/logo.jpg' />">
					<div class="beacon-container" data-type="beacon-container"></div>
			</div>
			<div class="auth-content">
				<div class="auth-content-inner">
					<div class="primary-auth">
						<div class="social-auth">
							
								<h2 class="okta-form-title o-form-head" data-se="o-form-head">
									<#if state == 'register' >
											Email Verification
										<#elseif state="checkEmail">
											Passcode verification
										<#elseif state="emailVerified">
											Email successfully validated!
									</#if>	
								</h2>
						</div>
							
<form name = "account" method="POST" action="" data-se="o-form" id="form16" class="primary-auth-form o-form o-form-edit-mode">

<#if errorMessage??>
<div class="o-form-error-container o-form-has-errors" data-se="o-form-error-container">
	<div>	    
	<div class="okta-form-infobox-error infobox infobox-error" role="alert">	      
		<span class="icon error-16"></span>	      	        
		<p><strong>Error!</strong>  ${errorMessage}</p>	      	    
</div>	  
</div>
</div>	
	
 </#if>
      
	<#if state == 'register' >
		<div data-se="o-form-fieldset" class="o-form-fieldset o-form-label-top">
		<div data-se="o-form-input-container" class="o-form-input">
			<span data-se="o-form-input-username" class="okta-form-input-field input-fix o-form-control">
				<span class="input-tooltip icon form-help-16" data-hasqtip="0"></span>
				<span class="icon input-icon person-16-gray"></span>	
					<@spring.bind "accountForm.userid"/>						
				<input name="${spring.status.expression}"  type = 'email' placeholder = 'email' value="${spring.status.value?default("")}" required>
	 		</span>	 		
		</div>
		</div>
		<div class="o-form-button-bar">
	 			<input name="checkEmail" type="submit" class="button button-primary" value="Submit Email" data-type="save">
		</div>
	</#if>	

 <#if state == 'checkEmail'>
 		<div data-se="o-form-fieldset" class="o-form-fieldset o-form-label-top">
		<div data-se="o-form-input-container" class="o-form-input">
			<span data-se="o-form-input-username" class="okta-form-input-field input-fix o-form-control">
				<span class="input-tooltip icon form-help-16" data-hasqtip="0"></span>
				<span class="icon input-icon person-16-gray"></span>	
					<@spring.bind "accountForm.userid"/>						
				<input name="${spring.status.expression}"  type = 'email' placeholder = 'email' value="${spring.status.value?default("")}" required>
	 		</span>	 		
		</div>
		</div>
		
		<div data-se="o-form-fieldset" class="o-form-fieldset o-form-label-top">
		<div data-se="o-form-input-container" class="o-form-input">
			<span data-se="o-form-input-code" class="okta-form-input-field input-fix o-form-control">
				<span class="input-tooltip icon form-help-16" data-hasqtip="0"></span>
				<span class="icon input-icon person-16-gray"></span>	
					<@spring.bind "accountForm.code"/>						
				<input name="${spring.status.expression}"  type = 'text' placeholder = 'Authorizationn code' value="${spring.status.value?default("")}" required>
	 		</span>	 		
		</div>
		</div>

				<div class="o-form-button-bar" style="text-align:center;">
	 			<input name="checkMFA" type="submit" class="button button-primary" value="Submit verification" data-type="save">
	 			
		</div> 
		
		<div class="alert alert-success" role="alert"">                     
	    		<p><strong style="font-weight: bold;">Almost Done! </strong>We sent you a passcode in your email. Please validate the passcode above</p>
		</div>
   		
</#if>


<#if state == 'emailVerified'>
	<#if jwt??> 
	<hr class="featurette-divider"/> 
	<div class="alert alert-success" role="alert"">                     
		<p><strong style="font-weight: bold;">Done! </strong><a id="myLink" title="Click here to download the document" href="<@spring.url '/download?accesstoken=${accesstoken}&pdf=okta’s_core_values.pdf'/>">Click here to download your document</a></p>
		<br/>
	    <p><strong style="font-weight: bold;">Done! </strong><a id="myLink2" title="Coupon" href="<@spring.url '/download?accesstoken=${accesstoken}&pdf=Coupon.pdf'/>">Click here to download your Coupon</a></p>
	</div>		
	</#if>
</#if>

<p style="margin: 20px 0 0 0;"><a id="myLink2" title="Click here to go back to the beginning" href="<@spring.url '/register'/>">To start over, <strong style="font-weight: bold;">click here</strong></a></p>      			                                              

</div>
</div>
</div>
</div>
</div> 
</div>  <!--Main -->
        
</body>    
</html>