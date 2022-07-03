<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
	String roleuser="";
	String name="";
	String userid="";
	if(session.getAttribute("roleuser")!=null && session.getAttribute("roleuser")!=null)
	{
		roleuser = (String)session.getAttribute("roleuser");
		name = (String)session.getAttribute("username");
		userid = (String)session.getAttribute("userID");
	}
	else
	{
		%>
		<script>
          alert('Invalid User!!');
          window.location='login.jsp';
        </script>
		<%
	}
	%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title> E-record Abi Archrey System </title>
<link href="/SIMS/css/style.default.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
</head>
<body>
<header>
	<div class="headerwrapper">
		<div class="header-left">
			<a href="index.jsp" class="logo">
				<img src="/SIMS/images/logo.png" alt="" width="20%" /> 
			</a>
			<div class="pull-right">
				<a href="#" class="menu-collapse">
					<i class="fa fa-bars"></i>
				</a>
			</div>
		</div>
		<div class="header-right">
			<div class="pull-right">
				<div class="btn-group btn-group-option">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						Login as : <% out.println(name); %> <i class="fa fa-caret-down ml5"></i>
					</button>
					<ul class="dropdown-menu pull-right" role="menu">
						<li><a href="/SIMS/ProfileController?action=profile/profile&id=<%=userid %>"><i class="glyphicon glyphicon-user"></i> Edit Profile</a></li>
						<li><i class="fa fa-list fa-fw"></i> <%out.println(roleuser);%></li>
						<li class="divider"></li>
						<li><a href="/SIMS/login.jsp"><i class="glyphicon glyphicon-log-out"></i>Sign Out</a></li>
					</ul>
					</div>
					</div>
					</div>
					
	</div>
</header>
<section>
	<div class="mainwrapper">
		<div class="leftpanel">
			<div class="media profile-left">
				<div class="media-body">
					<div><h3><% out.println(name); %></h3></div>
					<div><h6><%out.println(roleuser);%></h6></div>
				</div>
			</div>
			<h5 class="leftpanel-title">User Menu</h5>
			<ul class="nav nav-pills nav-stacked">
				<li><a href="/SIMS/admin/index.jsp"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
				<li><a href="/SIMS/ProfileController?action=profile/profile&id=<%=userid %>"><i class="fa fa-user"></i> <span>Edit Profile</span></a></li>				
				<h5 class="leftpanel-title">Main Menu</h5>
				
				<li class="parent active"><a href="#"><i class="fa fa-male"></i> <span>User</span></a>
					<ul class="children">
						<li><a href="/SIMS/UserController?action=user/viewUserList">List of User</a></li>
						<li class="active"><a href="/SIMS/UserController?action=createUser">Add New User</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-truck"></i> <span>Supplier</span></a>
					<ul class="children">
						<li><a href="/SIMS/SupplierController?action=supplier/viewSupplierList">Manage Supplier</a></li>
						<li><a href="/SIMS/SupplierController?action=createSupplier">Add New Supplier</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-shopping-cart"></i> <span>Product</span></a>
					<ul class="children"> 
						<li><a href="/SIMS/ProductController?action=product/viewProductList">Manage Product</a></li>
						<li><a href="/SIMS/ProductController?action=createProduct">Add New Product</a></li>
					</ul>
				</li>
				
				<!-- <li class="parent"><a href="#"><i class="fa fa-dollar"></i> <span>Sales</span></a>
					<ul class="children">
						<li><a href="/SIMS/SaleController?action=sale/viewSaleList">Manage Sales</a></li>
						<li><a href="/SIMS/sale/createSale.jsp">Update Sales</a></li>
					</ul>
				</li>  -->
				
				<li class="parent"><a href="#"><i class="fa fa-signal"></i> <span>Report</span></a>
					<ul class="children">
						
						<li><a href="/SIMS/report/viewProductReport.jsp">Product</a></li>
					</ul>
				</li>
				
				
				<li><a href="/SIMS/LoginController?action=Logout"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
			</ul>
		</div>
		<div class="mainpanel">
			<div class="pageheader">
				<div class="media">
					<div class="pageicon pull-left">
						<i class="fa fa-male"></i>
					</div>
					<div class="media-body">
						<ul class="breadcrumb">
							<li><a href="index.jsp"><i class="glyphicon glyphicon-home"></i></a></li>
							<li><a href="/SIMS/user/viewUserList.jsp">List of User</a></li>
							<li>Add New User</li>
						</ul>
						<h4>Add New User</h4>
					</div>
				</div>
			</div>
			<div class="row">	
				<form name="form1" method="post" id="ff" action="/SIMS/UserController">
				<div class="col-md-12">
								<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">Add New User</h4>
								</div>
					<div class="panel-body">
						<div class="mb30"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group mb15">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									
										<input type="text" id="name" name="roleUser"class="form-control" placeholder="Enter role user" value="Staff" readonly />
										</div>
									</div>
								</div>					
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group mb15">
											<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
											<input type="text" id="name" name="nameUser" class="form-control" placeholder="Enter name" required="required" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="input-group mb15">
											<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
											<input type="text" id="email" name="emailUser" class="form-control" placeholder="Enter email" required="required">
										</div>
									</div>						
									<div class="col-sm-6">
										<div class="input-group mb15">
											<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
											<input type="text" maxlength="11" id="tel" name="telUser" class="form-control" placeholder="Enter Phone Number (+06)" required="required">
										</div>
									</div>						
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group mb15">
											<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
											<input type="text" id="address" name="addressUser" class="form-control" placeholder="Enter address" required="required" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="input-group mb15">
											<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
											<input type="text" id="username" name="usernameUser" class="form-control" placeholder="Enter username" required="required">
										</div>
									</div>						
									<div class="col-sm-6">
										<div class="input-group mb15">
											<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
											<input type="text" id="password" name="passwordUser" class="form-control" placeholder="Enter password" required="required">
										</div>
									</div>						
								</div>		
							
					</div>
					<div class="mt-3" align="center">							
					<input type="submit" name="action" value="Create" onclick="return confirm('Succesfully')">					
				</div>
				
			</div>
			</form>	
		</div>
		</div>
		<div class="contentpanel">
				
			</div>
				<div class="copyright">
                         &nbsp;  &copy; <span>2020</span> <span class="text-bold text"> E-RECORD ABI ARCHERY SYSTEMS </span>. <span>All Rights Reserved</span>
                 </div>
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
</body>




</html> 