<html>
	<head>
	</head>
	<body>
	<h1>Stocks Application</h1>
	</body>
	<g:form controller="Stocks" action="login">
		User Name: <g:textField name="userNM" maxlength="255"></g:textField>
		Password: <g:textField type="password" name="passWD" maxlength="255"></g:textField>
		<g:submitButton id="btn" name="loginButton" value="Login"/>
	</g:form>
	<g:if test="${flash.error}">
 		 <div class="alert alert-error" style="display: block">${flash.error}</div>
	</g:if>
</html>