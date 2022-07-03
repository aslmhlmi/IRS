<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<script>
    $(document).ready(function () {

        //$("#payment").hide();

        //============ Search when click enter ============//
        $('#searchTerm').keypress(function (e) {
            var key = e.which;
            if (key == 13)  // the enter key code
            {
                $("#add_product").click();
                return false;
            }
        });

        //============ Add product function ============//
        $("#add_product").click(function (e) {

            e.preventDefault(); // prevent screen from change position

            var searchTerm = $("#searchTerm").val();
            var dataString = "searchTerm=" + searchTerm;

            if ($.trim(searchTerm).length > 0) {
                $.ajax({
                    type: "POST",
                    url: "function/ajaxTransactionProductSearch.php",
                    data: dataString,
                    cache: false,
                    beforeSend: function () {
                        $("#ajaxLoading").show();
                    },
                    success: function (result) {
                        if (result.success == "true") {

                            var obj = $.parseJSON(JSON.stringify(result.data));
                            var output = "";

                            for (var i in obj) {
                                // Calculate discount
                                var discountPrice = obj[i].discount;
                                var oldPrice = obj[i].price;
                                var newPrice = oldPrice - discountPrice;
                                //console.log(newPrice.toFixed(2));

                                output += "<tr class='items' id='items'>";
                                output += "<td>" + obj[i].code + "</td>";
                                output += "<td>" + obj[i].name + "</td>";
                                output += "<td><input type='text' value='1' name='quantity[]' id='quantity[]'" +
                                    "style='width: 70px;' onkeyup='CalculateTotalPrice();' /></td>";
                                output += "<td>" + obj[i].price + "</td>";
                                output += "<td>" + obj[i].discount + "</td>";
                                output += "<td style='display: none;' name='discPrice[]' id='discPrice[]'>" +
                                    newPrice.toFixed(2) + "</td>";
                                output += "<td name='total[]' id='total[]' class='total'></td>";
                                output += "<td style='display: none;'>" + obj[i].id + "</td>";
                                output += "</tr>";
                            }
                            $("#bootstrap_table tr:last").after(output);
                            $("#bootstrap_table tr:eq(1):contains('No records')").hide();

                            CalculateTotalPrice(); // Calculate price

                            $("#message").html("Product added.");
                            $("#message").removeClass().addClass("alert alert-success").show();

                            //$("#payment").show();
                        }
                        else {
                            $("#message").html("<span style=\"color:#cc0000\"><b>Error:</b></span> Product with code <b>" +
                                searchTerm + "</b> not found.");
                            $("#message").removeClass().addClass("alert alert-danger").show();
                        }
                        $("#ajaxLoading").hide();
                    }
                });
            }
            else {
                $("#message").html("<span style=\"color:#cc0000\"><b>Error:</b></span> Please enter a keyword.");
                classError();
            }
        });


        //============ Calculate Total Amount input ============//
        $('#amount_receive').keypress(function (e) {
            var key = e.which;
            if (key == 13)  // the enter key code
            {
                CalculateAmount();
                return false;
            }
        });


        //============ Make payment ============//
        $("#payment").click(function (e) {
            e.preventDefault(); // prevent screen from change position

            // Get product data from table
            var table = $("table tbody");
            var tableObject = "";
            tableObject += '"product":[';

            table.find("tr.items").each(function (i) {
                var $tds = $(this).find('td'),
                    code = $tds.eq(0).text(),
                    name = $tds.eq(1).text(),
                    getQuantity = $tds.find('[name="quantity\\[\\]"]'),
                    quantity = parseInt(getQuantity.val(), 10),
                    price = $tds.eq(3).text(),
                    discount = $tds.eq(4).text(),
                    total = $tds.eq(6).text(),
                    prod_id = $tds.eq(7).text();

                // console.log('row: ' + (i + 1) + '\ncode: ' + code + '\nname: ' + name + '\nquantity: ' + quantity
                // + '\nprice: ' + price + '\ndiscount: ' + discount + '\ntotal: ' + total); **/

                tableObject += '{"row": "' + (i + 1) + '","code": "' + code + '","name": "' + name + '","quantity": "' + quantity
                    + '","price": "' + price + '","discount": "' + discount + '","total": "' + total + '","prod_id": "' + prod_id + '"},';
            });

            tableObject += ']';
            //console.log(tableObject.replace("},]", "}]"));

            var total = $("#sum").val();
            var method = $("#method").val();
            var amount_receive = $("#amount_receive").val();
            var change = $("#change").val();

            if (total === "0.00" || total === "") {
                $("#message").html("<span style=\"color:#cc0000\"><b>Error:</b></span> Please add any product.");
                classError();
                return false;
            }

            // Validate change is valid or not
            if (!(change == '' || change == 'NaN' || change.charAt(0) == '-')) { // If change not empty or negative

                var dataString = "total=" + total + "&amount_receive=" + amount_receive + "&change=" + change
                    + "&method=" + method + "&product=" + tableObject.replace("},]", "}]");

                $.ajax({
                    type: "POST",
                    url: "function/ajaxTransactionCreate.php",
                    data: dataString,
                    cache: false,
                    beforeSend: function () {
                        $("#ajaxLoading").show();
                    },
                    success: function (result) {
                        if (result.success == "true") {
                            $("#message").html("Transaction have been save. Thank you for your payment.");
                            classSuccess();

                            $("#payment").hide();
                            $("#transaction_id").val(result.transaction_id);
                            $("#view_receipt").show();
                        }
                        else {
                            $("#message").html("<span style=\"color:#cc0000\"><b>Error:</b></span> Transaction failed.");
                            classError();
                        }
                        $("#ajaxLoading").hide();
                    }
                });
            }
            else {
                $("#message").html("<span style=\"color:#cc0000\"><b>Error:</b></span> Please make sure the received amount is enough.");
                classError();
            }
        });


        // view_receipt button
        $("#view_receipt").click(function () {
            var t_id = $("#transaction_id").val();
            location.href = "transaction_receipt.php?id=" + t_id;
        });


        // browse_product button
        $("#browse_product").click(function (e) {
            e.preventDefault();

            $("#bootstrap_table_product tr:eq(1):contains('No records')").show();
            $("#bootstrap_table_product").find("tr:not(:nth-child(1))").remove();

            $.ajax({
                type: "POST",
                url: "function/ajaxProductList.php",
                data: {},
                cache: false,
                beforeSend: function () {
                    $("#ajaxLoading").show();
                },
                success: function (result) {
                    if (result.success == "true") {

                        var obj = $.parseJSON(JSON.stringify(result.data));
                        var output_browse = "";

                        for (var i in obj) {
                            // Calculate discount
                            var discountPrice = obj[i].discount;
                            var oldPrice = obj[i].price;
                            var newPrice = oldPrice - discountPrice;
                            //console.log(newPrice.toFixed(2));

                            output_browse += "<tr class='product_items' id='product_items'>";
                            output_browse += "<td class='ocode'>" + obj[i].code + "</td>";
                            output_browse += "<td class='oname'>" + obj[i].name + "</td>";
                            output_browse += "<td class='oprice'>" + obj[i].price + "</td>";
                            output_browse += "<td class='odiscount'>" + obj[i].discount + "</td>";
                            output_browse += "<td class='opid' style='display: none;'>" + obj[i].id + "</td>";
                            output_browse += "<td class='obtn' style='text-align:center;'>" +
                                "<button class=\"btn btn-primary btn-sm btn_select\" style=\"min-width: 100px;\"> " +
                            "Add</button></td>";
                            output_browse += "</tr>";
                        }
                        $("#bootstrap_table_product tr:last").after(output_browse);
                        $("#bootstrap_table_product tr:eq(1):contains('No records')").hide();


                        // On button_select clicked
                        $(".btn_select").click(function () {

                            var output = "";
                            var $this = $(this).closest("tr");

                            var ocode = $this.find(".ocode").text();
                            var oname = $this.find(".oname").text();
                            var oprice = $this.find(".oprice").text();
                            var odiscount = $this.find(".odiscount").text();
                            var opid = $this.find(".opid").text();

                            console.log(ocode + ", " + oname + ", " + oprice + ", " + odiscount + ", " + opid);

                            //=========== Add to the transaction table ==============//

                            // Calculate discount
                            var discountPrice = odiscount;
                            var oldPrice = oprice;
                            var newPrice = oldPrice - discountPrice;
                            //console.log(newPrice.toFixed(2));

                            output += "<tr class='items' id='items'>";
                            output += "<td>" + ocode + "</td>";
                            output += "<td>" + oname + "</td>";
                            output += "<td><input type='text' value='1' name='quantity[]' id='quantity[]'" +
                                "style='width: 70px;' onkeyup='CalculateTotalPrice();' /></td>";
                            output += "<td>" + oprice + "</td>";
                            output += "<td>" + odiscount + "</td>";
                            output += "<td style='display: none;' name='discPrice[]' id='discPrice[]'>" +
                                newPrice.toFixed(2) + "</td>";
                            output += "<td name='total[]' id='total[]' class='total'></td>";
                            output += "<td style='display: none;'>" + opid + "</td>";
                            output += "</tr>";

                            $("#bootstrap_table tr:last").after(output);
                            $("#bootstrap_table tr:eq(1):contains('No records')").hide();

                            CalculateTotalPrice(); // Calculate price

                        });

                    }
                    $("#ajaxLoading").hide();
                }
            });
        });

    });

    //============ Calculate Total Amount ============//
    function CalculateTotalPrice() {
        //console.log("CalculateTotalPrice");
        $('tr.items').each(function (i, el) {
            var $this = $(this),
                $discPrice = $this.find('[name="discPrice\\[\\]"]'),
                $quantity = $this.find('[name="quantity\\[\\]"]'),
                dPrc = parseFloat($discPrice.html()),
                qtty = parseInt($quantity.val(), 10),
                total = dPrc * qtty || 0;
            $this.find('[name="total\\[\\]"]').html(total.toFixed(2));

            var totalAll = 0;
            $('.total').each(function () {
                totalAll += parseFloat(this.innerHTML)
            });
            $('#sum').val(totalAll.toFixed(2));
        });

        CalculateAmount();
    }

    //============ Calculate Balance ============//
    function CalculateAmount() {

        var sum = parseFloat($("#sum").val());
        var amount_receive = parseFloat($("#amount_receive").val());

        var balance = amount_receive - sum;
        $("#change").val(balance.toFixed(2));
    }

</script>	
	
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title> Sales &amp; Inventory Management System </title>
<title><?php echo "Administrator - ".$title;?></title>
<link href="../css/style.default.css" rel="stylesheet">
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
		<div class="header-right">
			<div class="pull-right">
				<div class="btn-group btn-group-option">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						Login as : <?php echo $rows_admin['user_name'];?> <i class="fa fa-caret-down ml5"></i>
					</button>
					<ul class="dropdown-menu pull-right" role="menu">
						<li><a href="editProfile.jsp"><i class="glyphicon glyphicon-user"></i> Edit Profile</a></li>
						<li class="divider"></li>
						<li><a href="<?php echo $_SERVER['PHP_SELF']."?action=logout";?>"><i class="glyphicon glyphicon-log-out"></i>Sign Out</a></li>
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
				<li><a href="editProfile.jsp"><i class="fa fa-user"></i> <span>User Profile</span></a></li>
				<h5 class="leftpanel-title">Main Menu</h5>
				
				<li class="parent"><a href="#"><i class="fa fa-male"></i> <span>User</span></a>
					<ul class="children">
						<li><a href="viewStaff.jsp">List of User</a></li>
						<li><a href="createStaff.jsp">Add New User</a></li>
					</ul>
				</li>

				<li class="parent"><a href="#"><i class="fa fa-truck"></i> <span>Supplier</span></a>
					<ul class="children">
						<li><a href="viewSupplier.jsp">Manage Supplier</a></li>
						<li><a href="createSupplier.jsp">Add New Supplier</a></li>
					</ul>
				</li>
				
				<li class="parent"><a href="#"><i class="fa fa-shopping-cart"></i> <span>Product</span></a>
					<ul class="children"> 
						<li><a href="viewProduct.jsp">Manage Product</a></li>
						<li><a href="createProduct.jsp">Add New Product</a></li>
					</ul>
				</li>				
				<li class="active parent"><a href="#"><i class="fa fa-dollar"></i> <span>Sales</span></a>
					<ul class="children">
						<li><a href="/SIMS/SaleController?action=sale/viewSaleList">Manage Sales</a></li>
						<li class="active"><a href="/SIMS/sale/createSale.jsp">Add New Sales</a></li>
					</ul>
				</li>
				<li class="parent"><a href="#"><i class="fa fa-signal"></i> <span>Report</span></a>
					<ul class="children">
						<li><a href="reportSales.jsp">Sales</a></li>
						<li><a href="reportSales.jsp">Product</a></li>
					</ul>
				</li>
											
				<li><a href="<?php echo $_SERVER['PHP_SELF']."?action=logout";?>"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
			</ul>
		</div>
		<div class="mainpanel">
			<div class="pageheader">
				<div class="media">
					<div class="pageicon pull-left">
						<i class="fa fa-book"></i>
					</div>
					<div class="media-body">
						<ul class="breadcrumb">
							<li><a href="index.php"><i class="glyphicon glyphicon-home"></i></a></li>
							<li><a href="booking-list.php">List Of Sales</a></li>
							<li>Add New Sales </li>
						</ul>
						<h4>Add New Sales</h4>
					</div>
				</div>
			</div>
			<div class="contentpanel">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-12">
									
<div class="row">
    <div class="col-lg-6">
        <div class="form-inline">
            <label>Product Code</label>
            <input class="form-control" id="searchTerm" name="searchTerm">
            <button class="btn btn-primary" style="min-width: 100px; margin-top: 5px;" id="add_product"
                name="add_product">
                Add
               
            </button>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="form-inline">
            <button class="btn btn-primary pull-right" style="min-width: 100px; margin-top: 5px;" id="browse_product"
                name="browse_product" data-toggle="modal" data-target="#myModal">
                Browse Product
               
            </button>
        </div>
    </div>
</div>
<br>

<div class="table-responsive">
    <table class="table table-bordered table-hover table-striped" id="bootstrap_table">
        <thead>
            <tr>
                <th class="col-md-2" style="background-color: #e7e7e7;">Code</th>
                <th class="col-md-5" style="background-color: #e7e7e7;">Name</th>
                <th class="col-md-1" style="background-color: #e7e7e7;">Quantity</th>
                <th class="col-md-1" style="background-color: #e7e7e7;">Unit Price</th>
                <th class="col-md-1" style="background-color: #e7e7e7;">Discount</th>
                <th class="col-md-1" style="background-color: #e7e7e7; display: none;">After Discount</th>
                <th class="col-md-2" style="background-color: #e7e7e7;">Total</th>
                <th class="col-md-1" style="background-color: #e7e7e7; display: none;">Product ID</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="6" style="text-align: center;">No records</td>
            </tr>
        </tbody>
    </table>
</div>

<div class="row">

    <div class="col-lg-5 pull-right" id="payment-form">
        <div class="row" style="padding-top: 5px;">
            <div class="col-lg-4">
                <label>Total</label>
            </div>
            <div class="col-lg-8 form-inline">
                <input class="form-control" id="sum" name="sum" readonly="readonly">
            </div>
        </div>
        <div class="row" style="padding-top: 5px;">
            <div class="col-lg-4">
                <label>Method</label>
            </div>
            <div class="col-lg-8 form-inline">
                <select class="form-control" id="method" name="method" style="min-width: 195px;">
                    <option value="cash">Cash</option>
                    <option value="credit_card">Credit Card</option>
                </select>
            </div>
        </div>
        <div class="row" style="padding-top: 5px;">
            <div class="col-lg-4" style="white-space: nowrap;">
                <label>Amount Receive</label>
            </div>
            <div class="col-lg-8 form-inline">
                <input class="form-control" id="amount_receive" name="amount_receive"
                    onblur="CalculateAmount();return false;">
            </div>
        </div>
        <div class="row" style="padding-top: 5px;">
            <div class="col-lg-4">
                <label>Change</label>
            </div>
            <div class="col-lg-8 form-inline">
                <input class="form-control" id="change" name="change" readonly="readonly">
            </div>
        </div>
        <div class="row" style="padding-top: 5px;">
            <div class="col-lg-4">
            </div>
            <div class="col-lg-8 form-inline">
                <button class="btn btn-success form-control" style="min-width: 195px; margin-top: 5px;"
                    id="payment"
                    name="payment">
                    PAY
                   
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
            </div>
            <div class="col-lg-8 form-inline">
                <button class="btn btn-warning form-control" style="min-width: 195px; margin-top: 5px; display: none;"
                    id="view_receipt"
                    name="view_receipt">
                    View Receipt                   
                </button>
                <input type="hidden" id="transaction_id" />
            </div>
        </div>
    </div>

</div>
									
								
								
								
							</div>
						</div>
						<div class="table-responsive">
							
						</div>
					</div>
				</div>
			</div>
				<div class="copyright">
                         &nbsp;  &copy; <span>2017</span> <span class="text-bold text"> Reservationcom </span>. <span>All Rights Reserved</span>
                 </div> 
		</div>
	</div>
</section>
<script src="../js/jquery-1.11.1.min.js"></script>
<script src="../js/jquery-migrate-1.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/modernizr.min.js"></script>
<script src="../js/pace.min.js"></script>
<script src="../js/retina.min.js"></script>
<script src="../js/jquery.cookies.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>
		