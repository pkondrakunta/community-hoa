<!-- 
- Author(s): Pragnya Kondrakunta 
- Date: Nov, 2023
- Description: Search Member Page View to provide form that looks for members with matching criteria -->

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype HTML>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

    <style>
        .btn-theme {
            background-color: #fff;
            border-color: #4776E6;
            color: #4776E6;

        }

        .btn-theme:hover {
            transition-duration: 0.1s;
            border-color: #fff;
            color: #fff;
            background-image: linear-gradient(to right, #4776E6, #8E54E9);

        }
    </style>

</head>

<body class="container justify-content-center">

    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light border-bottom border-body">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            Members
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/searchMember">Search Members</a></li>
                            <li><a class="dropdown-item" href="/members">All Members</a></li>
                            <li><a class="dropdown-item" href="/addMember">Add Member</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            Invoices
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/searchInvoice">Search Invoices</a></li>
                            <li><a class="dropdown-item" href="/invoices">All Invoices</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <br /><br /><br /><br />

    <div class="container text-center">
        <h3>Community HOA</h3>
        <br /><br />

    </div>

    <form method="POST" action="/member/${member.memberID}/generateInvoice">
      <div class="form-group row">
        <h4>Water & Trash charges</h4>
        <label for="payUntilMonth" class="col-sm-2 col-form-label">Pay until</label>
        <div class="col-sm-10">
          <input onkeyup="updateCheckout()" type="month" class="form-control" name="payUntilMonth">
        </div>
      </div><br/>

      Charges breakdown:
      <div id="info-div"></div>

        <div class="form-group row">
          <div class="col-sm-10">
            <button type="submit" class="btn btn-theme">Confirm Payment</button>
          </div>
        </div>
      </form>

          <script>

        function updateCheckout() {
            var divElement = document.getElementById("info-div");

            //Step 2. Create the XMLHttpRequest object
            var xmlhttprequest = new XMLHttpRequest();

            //Step 3. Call the server side resource asynchronously
            // xmlhttprequest.open(type_of_call, URL_to_make_call_to, asynchronous)
            xmlhttprequest.open("get", "/updateFees", true);
            xmlhttprequest.send();

            //Step 4. Wait for the server response
            xmlhttprequest.onreadystatechange = function(){
                if(xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200){

                    //Step 5. Get the responseText to manipulate the DOM element
                    divElement.innerHTML = xmlhttprequest.responseText;
                }
            }
        }
    </script>


</body>

</html>