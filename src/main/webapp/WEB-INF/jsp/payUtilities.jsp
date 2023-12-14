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
    Utility Subscription valid till <b><fmt:formatDate value="${parsedDate_expiry}" pattern="MMM dd, YYYY" /></b>
    <br/><br/>

    <form id="payUtilitiesForm" method="POST" action="/member/${member.memberID}/payUtilitiesBreakdown">
        <div class="form-group">
            <label class="col-sm-6">Extend validity upto</label>
            <div class="col-sm-6">
            <%-- Generate months/years for new validity  --%>
            <c:choose>
            <c:when test="${requestScope.member.subscriptionPlan == 'Monthly'}">
                <select class="form-control" name="subscriptionNewValidity"> 
                <c:forEach items="${requestScope.monthList}" var="month">
                    <option class="form-select">${month}</option>
                </c:forEach>
                </select>
            </c:when>
            <c:when test="${requestScope.member.subscriptionPlan == 'Annually'}">
                <select class="form-control" name="subscriptionNewValidity"> 
                <c:forEach items="${requestScope.yearList}" var="year">
                    <option class="form-select">${year}</option>
                </c:forEach>
                </select>
            </c:when>
            </c:choose>
            </div>
        </div><br/>

    </form>

    <c:choose>
    <c:when test="${requestScope.chargesBreakdown == 'false'}">
        <button form="payUtilitiesForm" type="submit" class="btn btn-theme">Calculate Total</button>

    </c:when>
    <c:when test="${requestScope.chargesBreakdown == 'true'}">
        Charges breakdown:
        <table class="table">
            <thead style="background-color:gray;">
                <th>Description</th>
                <th>Charges</th>
            </thead>
            <tbody>
            <tr>
                <td>Water</td>
                <td>${requestScope.water}</td>
            </tr>
            <tr>
                <td>Trash</td>
                <td>${requestScope.trash}</td>
            </tr>
            <tr>
                <td><b>Total</b></td>
                <td><b>${requestScope.total}</b></td>
            </tr>
            </tbody>
        </table> <br/><br/>

         <button form="payUtilitiesForm" type="submit" class="btn btn-theme">Confirm Payment</button>

    </c:when>
    </c:choose>

</body>

</html>