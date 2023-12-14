<!-- 
- Author(s): Pragnya Kondrakunta 
- Date: Nov, 2023
- Description: Search Invoice Page View to provide form that looks for invoices with matching criteria  -->

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">

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
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
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
                </ul>
            </div>
        </div>
    </nav>

    <br /><br /><br /><br />

    <div class="container text-center">
        <h3>Community HOA</h3>
        <br /><br />

        <form method="POST" name="searchInvoice" class="d-flex justify-content-center" role="searchInvoice">
            <input name="invoice-search-text" class="form-control w-25 me-2" type="search" placeholder="Search Invoice" aria-label="Search">
            <button class="btn btn-theme" type="submit">Search</button>
        </form>

        <c:choose>
        <c:when test="${requestScope.resultsOutcome == 'false'}">
            <br/><h4>Search results for: ${requestScope.sText}</h4>
            <br/><p>No matching invoice found.<p>  
        </c:when>
        <c:when test="${requestScope.resultsOutcome == 'true'}">
            <br/><h4>Search results for: ${requestScope.sText}</h4>
            <br/>
            <table class="table table-striped">
                <thead class="table-dark">
                <tr>
                    <th>Invoice ID</th>
                    <th>Member ID</th>
                    <th>Invoice Date</th>
                    <th>Total</th>
                    <th>Download</th>
                </tr>
                </thead>
                
                <c:forEach items="${requestScope.invoiceResultList}" var="inv">
                    <tr>
                        <td>${inv.invoiceID}</td>
                        <td>${inv.memberID}</td>
                        <fmt:parseDate value="${inv.date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime_paid" type="both" />
                        <td><fmt:formatDate pattern="MMM dd, yyyy HH:mm" value="${ parsedDateTime_paid }" /></td>
                        <td>${inv.total}</td>
                        <td><a href="/invoice/${inv.invoiceID}/download" class="btn btn-sm btn-theme">Download</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br/>
        </c:otherwise>
        </c:choose>

    </div>

</body>

</html>