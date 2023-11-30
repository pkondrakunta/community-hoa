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

    <form>
      <div class="form-group row">
        <label for="inputFirstName" class="col-sm-2 col-form-label">First Name</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputFirstName" placeholder="Viola">
        </div>
      </div>  <br/>
      <div class="form-group row">
        <label for="inputLastName" class="col-sm-2 col-form-label">Last Name</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputLastName" placeholder="Davis">
        </div>
      </div>  <br/>
        <div class="form-group row">
          <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
          <div class="col-sm-10">
            <input type="email" class="form-control" id="inputEmail" placeholder="viola.davis@gmail.com">
          </div>
        </div>  <br/>
        <div class="form-group row">
          <label for="inputPhone" class="col-sm-2 col-form-label">Phone</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="inputPhone" placeholder="6173733499">
          </div>
        </div>  <br/>
        <!-- <div class="form-group row">
          <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
          <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
          </div>
        </div><br/>
        
        <div class="form-group row">
          <label for="inputPasswordConfirm" class="col-sm-2 col-form-label">Confirm Password</label>
          <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPasswordConfirm" placeholder="Confirm Password">
          </div>
        </div><br/> -->

        <div class="form-group row">
          <label for="inputAddress" class="col-sm-2 col-form-label">Address</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main Street">
          </div>
        </div>  <br/>

        <div class="form-group row">
          <label for="inputUnit" class="col-sm-2 col-form-label">Unit</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="inputUnit" placeholder="1A">
          </div>
        </div>  <br/>
        <fieldset class="form-group">
          <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Unit Type</legend>
            <div class="col-sm-10">
              <div class="form-check">
                <input class="form-check-input" type="radio" name="inputUnitType" id="gridRadios1" value="Apartment" checked>
                <label class="form-check-label" for="gridRadios1">
                  Apartment 
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="inputUnitType" id="gridRadios2" value="Single-Family">
                <label class="form-check-label" for="gridRadios2">
                  Single-Family
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="inputUnitType" id="gridRadios3" value="Multi-Family">
                <label class="form-check-label" for="gridRadios3">
                  Multi-Family
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="inputUnitType" id="gridRadios4" value="Townhouse">
                <label class="form-check-label" for="gridRadios4">
                  Townhouse
                </label>
              </div>
            </div>
          </div>
        </fieldset><br/>

        <fieldset class="form-group">
          <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Subscription Plan</legend>
            <div class="col-sm-10">
              <div class="form-check">
                <input class="form-check-input" type="radio" name="inputYearly" id="gridRadios1" value="true" checked>
                <label class="form-check-label" for="gridRadios1">
                  Annually 
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="inputYearly" id="gridRadios2" value="false">
                <label class="form-check-label" for="gridRadios2">
                  Monthly
                </label>
              </div>
            </div>
          </div>
        </fieldset><br/>

        <div class="form-group row">
          <div class="col-sm-2">Confirmation</div>
          <div class="col-sm-10">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="gridCheck1">
              <label class="form-check-label" for="gridCheck1">
                You will be signing up for Community HOA. You will be charged for the selected subscription period.
              </label>
            </div>
          </div>
        </div><br/>

        <div class="form-group row">
          <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Add Member</button>
          </div>
        </div>
      </form>


</body>

</html>