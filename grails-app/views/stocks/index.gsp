<html>
	<head>
	</head>
	<body>
	<h1>Stocks Application</h1>
	</body>
	<g:form controller="Stocks" action="login">
		User Name: <g:textField name="userNM" maxlength="255"></g:textField>
		Password: <g:passwordField name="passWD" maxlength="255"></g:passwordField>
		<g:submitButton id="btn" name="loginButton" value="Login"/>
	</g:form>
	<hr></hr>
	<h3>Create an Account</h3>
	<g:form controller="Stocks" action="createAccount">
		User Name: <g:textField name="userNm" maxlength="255"></g:textField>
		Password: <g:passwordField type="password" name="passWd" maxlength="255"></g:passwordField>
		<g:submitButton id="btn1" name="createAccount" value="Create Account"/>
	</g:form>
</html>