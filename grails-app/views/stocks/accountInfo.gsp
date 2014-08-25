<html>
	<head>
	</head>
	<body>
	<h1>Stocks Application</h1>
	</body>
		<h2>Welcome to your Account!</h2>
		<g:form controller="Stocks" action="index">
				<g:submitButton id="btn" name="logout" value="Log out"/>
		</g:form>
		<hr></hr>
		<g:form controller="Stocks" action="addTicker" params="[accountID: "${accountID}"]">
			  <g:hiddenField name="userID" value="${accountID}" />
			Ticker: <g:textField name="ticker" maxlength="255"></g:textField>
			Quantity: <g:textField name="quantity" maxlength="255"></g:textField>
		<g:submitButton id="btn" name="loginButton" value="Add Company"/>
		</g:form>
		<hr></hr>
		<g:if test="${stocksInfo}">
			<table border="1" style="width:400">
				<tr>
					<th>Company</th>
					<th>Quantity</th>
					<th>Price</th>
				</tr>
				<g:each in="${stocksInfo}">
					<tr>
						<td>${it.ticker}</td>
						<td>${it.quantity}</td>
						<td>${it.price}</td>
					</tr>
				</g:each>
			</table>
			<p>Portfolio Value: ${marketValue}</p>
		</g:if>
		<g:else>
    		 Your portfolio is empty!
		</g:else>
	
	
</html>