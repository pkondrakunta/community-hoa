<!-- 
- Author(s): Pragnya Kondrakunta 
- Date: Nov, 2023
- Description: Search Member Page View to provide form that looks for members with matching criteria -->

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                            <li><a class="dropdown-item" href="/members/download">Download Members List</a></li>
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
        <h3>Community HOA</h3><br/>
        <h4>Utilities Payment</h4>
        <br /><br />

    </div>

    <fmt:parseDate value="${requestScope.member.subscriptionExpiry}" pattern="yyyy-MM-dd" var="parsedDate_expiry" type="date" />
    Member ID <b>${requestScope.member.memberID}</b> <br/>
    Member Name <b>${requestScope.member.firstName} ${requestScope.member.lastName}</b> <br/>
    Utility Subscription currently valid till
    <b><fmt:formatDate value="${parsedDate_expiry}" pattern="MMM dd, YYYY" /></b>
    <br/><br/>

    <%-- <fmt:parseDate value="${requestScope.subscriptionNewValidity}" pattern="yyyy-MM-dd'T'HH:mm" var="newValidity" type="both" /> --%>

    <form id="payUtilitiesForm" method="POST" action="/member/${member.memberID}/confirmUtilitiesPayment">
        <div class="form-group">
            <label class="col-sm-6">Extending validity upto</label>
            <div class="col-sm-6">
                <select class="form-control" name="subscriptionNewValidityDisplay" disabled> 
                    <option class="form-select" selected>${requestScope.subscriptionNewValidity}</option>
                </select>
            </div>
        </div><br/>
        <input name="subscriptionNewValidity" type="hidden" value="${requestScope.subscriptionNewValidity}"/>
        <input name="water" type="hidden" value="${requestScope.water_total}"/>
        <input name="trash" type="hidden" value="${requestScope.trash_total}"/>
        <input name="total" type="hidden" value="${requestScope.total}"/>

    </form>


    Charges breakdown:
    <table class="table">
        <thead style="background-color:gray;">
            <th>Description</th>
            <th>Monthly unit price</th>
            <th>Quantity</th>
            <th>Charges</th>
        </thead>
        <tbody>
        <tr>
            <td>Water</td>
            <td>${requestScope.water_monthly}</td>
            <td>${requestScope.months}</td>
            <td>${requestScope.water_total}</td>

        </tr>
        <tr>
            <td>Trash</td>
            <td>${requestScope.trash_monthly}</td>
            <td>${requestScope.months}</td>
            <td>${requestScope.trash_total}</td>
        </tr>
        <tr>
            <td><b>Total</b></td>
            <td></td>
            <td></td>
            <td><b>${requestScope.total}</b></td>
        </tr>
        </tbody>
    </table> <br/><br/>
    <button form="payUtilitiesForm" type="submit" class="btn btn-theme" style="margin-left:10px">Confirm Payment</button>
    <a href="/member/${member.memberID}/payUtilities" class="btn btn-secondary" style="margin-right:10px">Back</a>

</body>

</html>