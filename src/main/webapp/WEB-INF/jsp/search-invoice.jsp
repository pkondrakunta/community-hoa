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


            </head>

            <body class="container justify-content-center">

                <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light border-bottom border-body"
                    style="background-color: #4776E6;">
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
                                        <li><a class="dropdown-item" href="/search-member">Search Members</a></li>
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
                                        <li><a class="dropdown-item" href="#">Search Invoices</a></li>
                                        <li><a class="dropdown-item" href="#">All Invoices</a></li>
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

                    <form modelAttribute="invoice-text" name="searchInvoice" class="d-flex justify-content-center"
                        role="searchInvoice">
                        <input class="form-control w-25 me-2" type="search" placeholder="Search Invoice"
                            aria-label="Search">
                        <button class="btn btn-outline-secondary" type="submit">Search</button>
                    </form>

                </div>


            </body>

            </html>