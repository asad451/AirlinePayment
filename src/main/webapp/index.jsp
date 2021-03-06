<!DOCTYPE html>
<html>
<head>
  <title>Payment</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#">Sheffield Air</a>

  <ul class="navbar-nav ml-auto">
    <li class="nav-item">
      <a class="nav-link" href="#">Home</a>
    </li>
  </ul>

</nav>

<br><br>
<div class="container">
  <h2>Payment</h2>
</div>
<br>

<div class="container">
<label class="formText"><b>Total to Pay: $ 50.00</b></label>
  <form action="save" modelAttribute="user" method="post" name="form1" onsubmit="return validateDate()">
    <div class="row">
        <div class="form-group col-md-6">
            <label class="formText">Name on Card</label>
            <input type="text" class="form-control" id="name" placeholder="John Doe" name="c_name" required="true">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-6">
            <label class="formText">Card Number (Spacing)</label>
            <input type="text" class="form-control" id="number" placeholder="1234 1234 1234 1234" minlength="19" maxlength="19" name="c_num" required="true">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-3">
            <label class="formText">Expiry Date</label>
            <input type="text" class="form-control" id="date" placeholder="MM/YY" name="c_date" onkeypress='return event.charCode >= 47 && event.charCode <= 57' minlength="5" maxlength="5" required="true">
        </div>

        <div class="form-group col-md-3">
            <label class="formText">CVV</label> <span class="badge badge-info" data-toggle="tooltip"title="3 Digit Number at the back of your card !"> ?</span>
            <input type="text" class="form-control" id="cvv" placeholder="123" name="c_cvv" onkeypress='return event.charCode >= 48 && event.charCode <= 57'  minlength="3" maxlength="3"  required="true">
        </div>
    </div><br>

<!-- data-toggle="modal" data-target="#myModal" -->
    <button type="submit" class="btn btn-primary"  >Pay</button>
  </form>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Payment Confirmation</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        Your Payment has been successful !
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>


  

</body>

<script>

//ready() runs the code when the DOM is fully loaded

$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();   
});

//Function to check the date is in correct format

<!--
function validateDate() {

	var x = document.forms["form1"]["c_date"].value;
	if (x.match("((0[1-9])|(1[0-2]))-\\d{2}")) {
		alert("Date should be in correct format");
		return false;
	}
}
-->

</script>

</html>
