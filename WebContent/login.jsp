 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
        <%
	String roleuser="";
	String name="";
	String userid="";
	String fail="";
	if(session.getAttribute("roleuser")!=null && session.getAttribute("roleuser")!=null)
	{
		roleuser = (String)session.getAttribute("roleuser");
		name = (String)session.getAttribute("username");
		userid = (String)session.getAttribute("userid");
	}
	else
	{
		fail = (String)session.getAttribute("fail");
	
	}
	%>
    
    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>E-Record Abi Archery System </title>
<link rel="icon" href="images/logo.png" type="image/png" sizes="16x16">
<link href="/SIMS/css/style.default.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
</head>
<body class="signin">
<section>
	<div class="panel panel-signin">
		<div class="panel-body">
			<div class="logo text-center">
				<img src="images/logo.png" width="60%" alt="Chain Logo" >
			</div>
			<br />
			<h4 class="text-center mb5">Welcome to</h4>
			<h4 class="text-center mb5">E-Record Abi Archery System</h4>
			<br />
			<p class="text-center">Please sign in to your account to login</p>
			<div class="mb30"></div>
			
			

				<div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					Login unsuccessful. Please try again.
				</div>
		

			
			
			
			
		
			
			
			
			 <form name="form1" method="post" id="ff" action="/SIMS/LoginController">
				<div class="input-group mb15">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<input type="text" id="userid" name="userID" class="form-control" placeholder="Enter username" required>
				</div>
				<div class="input-group mb15">
					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					<input type="password" id="passworduser" name="passwordUser" class="form-control" placeholder="Enter password" required>
				</div>
				<div class="clearfix" align="center">
					<input type="submit" name="login" value="Login">
				</div>                      
			</form>
		</div>
	</div>
</section>
<script src="/SIMS/js/jquery-1.11.1.min.js"></script>
<script src="/SIMS/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/SIMS/js/bootstrap.min.js"></script>
<script src="/SIMS/js/modernizr.min.js"></script>
<script src="/SIMS/js/pace.min.js"></script>
<script src="/SIMS/js/retina.min.js"></script>
<script src="/SIMS/js/jquery.cookies.js"></script>
<script src="/SIMS/js/custom.js"></script>
<script>
function changeToUpperCase(event,obj) {
	charValue = (document.all) ? event.keyCode : event.which;
	if (charValue!="8" && charValue!="0" && charValue != "27"){
		obj.value += String.fromCharCode(charValue).toUpperCase();
		return false;
	} else {
		return true;
	}
}
</script>
</body>

</html>