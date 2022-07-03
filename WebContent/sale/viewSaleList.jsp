<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title> Sales &amp; Inventory Management System </title>
<link href="css/style.default.css" rel="stylesheet">
<link href="css/select2.css" rel="stylesheet" />
<link href="css/style.datatables.css" rel="stylesheet">
<link href="http://cdn.datatables.net/responsive/1.0.1/css/dataTables.responsive.css" rel="stylesheet">
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
				<img src="../images/logo.png" alt="" width="20%" /> 
			</a>
			<div class="pull-right">
				<a href="#" class="menu-collapse">
					<i class="fa fa-bars"></i>
				</a>
			</div>
		</div>
		<ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                     <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <div class="fa fa-user">&nbsp;
                    <% out.println(name); %>
                    <i class="fa fa-caret-down"></i>
                    </div></a>
                    <ul class="dropdown-menu dropdown-user">
                       
                       <li><a href="viewUpdateProfile?action=viewProfile&userID=<%out.println(userid);%>"><i class="fa fa-gear fa-fw"></i> Edit Profile</a></li>
                      <li><a href="listStaff.jsp"><i class="fa fa-list fa-fw"></i> <%out.println(roleuser);%></a></li>
							
                        <li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i>Sign Out</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
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
				<li><a href="editProfile.jsp"><i class="fa fa-user"></i> <span>User Profile</span></a></li>
				<h5 class="leftpanel-title">Main Menu</h5>
				
				<li class="parent"><a href="#"><i class="fa fa-male"></i> <span>User</span></a>
					<ul class="children">
						<li><a href="viewUserList.jsp">List of User</a></li>
						<li><a href="createUser.jsp">Add New User</a></li>
					</ul>
				</li>
				<li class="parent"><a href="#"><i class="fa fa-truck"></i> <span>Supplier</span></a>
					<ul class="children">
						<li><a href="SupplierController?action=supplier/viewSupplierList">Manage Supplier</a></li>
						<li><a href="createSupplier.jsp">Add New Supplier</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-shopping-cart"></i> <span>Product</span></a>
					<ul class="children"> 
						<li><a href="viewProductList.jsp">Manage Product</a></li>
						<li><a href="createProduct.jsp">Add New Product</a></li>
					</ul>
				</li>				
				<li class="parent active"><a href="#"><i class="fa fa-dollar"></i> <span>Sales</span></a>
					<ul class="children">
						<li class="active"><a href="/SIMS/SaleController?action=sale/viewSaleList">Manage Sales</a></li>
						<li><a href="/SIMS/sale/createSale.jsp">Add New Sales</a></li>
					</ul>
				</li>
				<li class="parent "><a href="#"><i class="fa fa-signal"></i> <span>Report</span></a>
					<ul class="children">
						<li ><a href="reportSales.jsp">Sales</a></li>
						<li><a href="reportProduct.jsp">Product</a></li>
					</ul>
				</li>
				<li><a href="<?php echo $_SERVER['PHP_SELF']."?action=logout";?"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
			</ul>
		</div>
		<div class="mainpanel">
			<div class="pageheader">
				<div class="media">
					<div class="pageicon pull-left">
						<i class="fa fa-dollar"></i>
					</div>
					<div class="media-body">
						<ul class="breadcrumb">
							<li><a href="index.jsp"><i class="glyphicon glyphicon-home"></i></a></li>
							<li>List of Sales </li>
						</ul>
						<h4>List of Sales </h4>
					</div>
				</div>
			</div>
			<div class="contentpanel">
				<div class="row">
					<div class="col-md-12">
						<table id="basicTable" class="table table-striped table-bordered responsive">
							<thead class="">
								<tr>
									<th>Sales ID</th>
									<th>Users ID</th>
									<th>Total Payment</th>									
									<th>Sales Date</th>
									<th>Status Sales</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sales}" var="sale">
								<tr>
									<td><c:out value="${sale.saleID}" /></td>
									<td><c:out value="${sale.userID}" /></td>
									<td><c:out value="${sale.totalPayment}" /></td>									
									<td><c:out value="${sale.dateSale}" /></td>																		
									<td><c:out value="${sale.statusSale}" /></td>								
									<td>
										<a href="admin-list.jsp?action=delete&id=<?php echo $rows_user['user_id'];?>" class="btn btn-warning btn-xs"><i  class="fa fa-info-circle"></i> View</a>
									</td>
									
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
				<div class="copyright">
                         &nbsp;  &copy; <span>2020</span> <span class="text-bold text"> SALES &amp; INVENTORY MANANGEMENT SYSTEMS </span>. <span>All Rights Reserved</span>
                 </div> 
		</div>
	</div>
</section>
<script src="../js/jquery-1.11.1.min.js"></script>
<script src="../js/jquery-migrate-1.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/modernizr.min.js"></script>
<script src=".../js/pace.min.js"></script>
<script src="../js/retina.min.js"></script>
<script src="../js/jquery.cookies.js"></script>
<script src="../js/jquery.dataTables.min.js"></script>
<script src="http://cdn.datatables.net/plug-ins/725b2a2115b/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script src="http://cdn.datatables.net/responsive/1.0.1/js/dataTables.responsive.js"></script>
<script src="../js/select2.min.js"></script>
<script src="../js/custom.js"></script>
<script>
jQuery(document).ready(function(){
	jQuery('#basicTable').DataTable({
		responsive: true
	});
});
</script>
</body>
</html>