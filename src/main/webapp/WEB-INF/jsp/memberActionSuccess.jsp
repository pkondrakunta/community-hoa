<!-- 
- Author(s): Pragnya Kondrakunta 
- Date: Dec, 2023
- Description: Member Page with action success preview -->

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            Users
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/users">All Users</a></li>
                            <li><a class="dropdown-item" href="/adminSignup">Signup an admin</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/allFees" role="button">Fees</a>
                    </li>
                    <li class="nav-item me-auto">
                        <a class="nav-link" href="/logout" role="button">Logout</a>
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

    <c:choose>
    <c:when test="${requestScope.action == 'deleted'}">
        <p>The member with the ID: ${requestScope.memberID} has been successfully ${requestScope.action}!</p>
    </c:when>
    <c:otherwise>
        <p>The member with the ID: ${requestScope.member.memberID} has been successfully ${requestScope.action}!</p>

        <div class="container">
        <div class="row">
            <div class="col">
                <div class="card bg-light mb-6">
                    <div class="card-header">Member Details</div>
                    <div class="card-body">
                        <h5 class="card-title">
                        
                        ${requestScope.member.firstName} ${requestScope.member.lastName}</h5>
                        <p class="card-text">
                            Member ID: ${requestScope.member.memberID}<br />
                            Address: ${requestScope.member.address}<br />
                            Unit: ${requestScope.member.unit}<br />
                            Unit Type: ${requestScope.member.unitType}<br />
                            Email: ${requestScope.member.email}<br />
                            Phone: ${requestScope.member.phone}<br />
                        </p>
                    </div>
                </div>
                <br />
                <a href="" style="margin-right: 10px;" class="btn btn-theme">Update</a> 
                <a href="" style="margin-left: 10px;" class="btn btn-theme">Delete</a>

            </div>
            <div class="col">
                <div class="card bg-light mb-6">
                    <div class="card-header">Subscription Details</div>
                    <div class="card-body">
                        <h5 class="card-title">
                            ${requestScope.member.subscriptionPlan}
                        </h5>
                        <p class="card-text">
                            Member ID: ${requestScope.member.memberID} <br />
                            <fmt:parseDate value="${requestScope.member.subscriptionExpiry}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime_expiry" type="both" />
                            Subscription expires on <fmt:formatDate pattern="MMM dd, yyyy HH:mm" value="${ parsedDateTime_expiry }" /> <br/>
                            <fmt:parseDate value="${requestScope.member.lastPaid}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime_paid" type="both" />
                            Last paid on <fmt:formatDate pattern="MMM dd, yyyy HH:mm" value="${ parsedDateTime_paid }" /><br/>
                            <%-- Subscription expires on <fmt:formatLocalDateTime value="${requestScope.member.subscriptionExpiry}" pattern="MMM dd, YYYY" /><br /> --%>
                            <%-- Last paid on <fmt:formatLocalDateTime value="${requestScope.member.lastPaid}" pattern="MMM dd, YYYY" /> <br /> --%>
                            <br/><br/><br/>
                        </p>
                    </div>
                </div>
                <br />
                <a href="" class="btn btn-theme">Pay</a>

            </div>
        </div>
        </div>

    </c:otherwise>
    </c:choose>



</body>

</html>