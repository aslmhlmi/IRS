<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String role="";
	String name="";
	String id="";
	if(session.getAttribute("role")!=null && session.getAttribute("role")!=null)
	{
		role = (String)session.getAttribute("role");
		name = (String)session.getAttribute("name");
		id = (String)session.getAttribute("id");
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
<title> Sales &amp; Inventory Management System </title>
<link href="css/style.default.css" rel="stylesheet">
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
						Login as : change here <i class="fa fa-caret-down ml5"></i>
					</button>
					<ul class="dropdown-menu pull-right" role="menu">
						<li><a href="/SIMS/ProfileController?action=profile/editProfile"><i class="glyphicon glyphicon-user"></i> Edit Profile</a></li>
						<li class="divider"></li>
						<li><a href="../index.jsp"><i class="glyphicon glyphicon-log-out"></i>Sign Out</a></li>
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
					<h4 class="media-heading">Admin</h4>
					<small class="text-muted">category</small>
				</div>
			</div>
			<h5 class="leftpanel-title">User Menu</h5>
			<ul class="nav nav-pills nav-stacked">
				<li><a href="index.jsp"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
				<li><a href="../ProfileController?action=profile/editProfile"><i class="fa fa-user"></i> <span>Edit Profile</span></a></li>				
				<h5 class="leftpanel-title">Main Menu</h5>
				
				<li class="parent"><a href="#"><i class="fa fa-male"></i> <span>User</span></a>
					<ul class="children">
						<li><a href="/SIMS/UserController?action=user/viewUserList">List of User</a></li>
						<li><a href="/SIMS/user/createUser.jsp">Add New User</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-truck"></i> <span>Supplier</span></a>
					<ul class="children">
						<li><a href="/SIMS/SupplierController?action=supplier/viewSupplierList">Manage Supplier</a></li>
						<li><a href="/SIMS/supplier/createSupplier.jsp">Add New Supplier</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-shopping-cart"></i> <span>Product</span></a>
					<ul class="children"> 
						<li><a href="/SIMS/ProductController?action=product/viewProductList">Manage Product</a></li>
						<li><a href="/SIMS/product/createProduct.jsp">Add New Product</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-dollar"></i> <span>Sales</span></a>
					<ul class="children">
						<li><a href="/SIMS/SaleController?action=sale/viewSaleList">Manage Sales</a></li>
						<li><a href="/SIMS/sale/createSale.jsp">Update Sales</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-signal"></i> <span>Report</span></a>
					<ul class="children">
						<li><a href="/SIMS/ProductReportController?action=report/viewProductReportList.jsp">Product</a></li>
						<li><a href="/SIMS/SalesReportController?action=report/viewSalesReportList.jsp">Sales</a></li>
					</ul>
				</li>
				
				
				<li><a href="../index.jsp"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
			</ul>
		</div>
	</div>
</section>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/pace.min.js"></script>
<script src="js/retina.min.js"></script>
<script src="js/jquery.cookies.js"></script>
<script src="js/custom.js"></script>
</body>
</html>