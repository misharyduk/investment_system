<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Add client</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="${request.contextPath}/assets/img/favicon.png" rel="icon">
    <link href="${request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="${request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="${request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="${request.contextPath}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="${request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${request.contextPath}/assets/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: OnePage
    * Updated: Mar 10 2023 with Bootstrap v5.2.3
    * Template URL: https://bootstrapmade.com/onepage-multipurpose-bootstrap-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<%@ include file="jspf/nav.jspf" %>

<main id="main">


    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact" style="margin-top: 6em;">
        <div class="container" data-aos="fade-up">

            <div class="section-title">
                <h2>Add client</h2>
                <p>Fill the form to add new client. All fields are required.</p>
            </div>

            <div class="row mt-5 justify-content-center">

                <p class="text-success text-center"><c:if test="${success == 1}">Investment successfully deleted</c:if></p>
                <p class="text-danger text-center"><c:if test="${error == 0}">Investment cannot be deleted</c:if></p>

                <div class="col-lg-8 mt-5 mt-lg-0">

                    <form:form action="add" method="post" role="form" class="php-email-form" modelAttribute="client">
                        <div class="row gy-2 gx-md-3">
                            <div class="col-md-6 form-group">
                                <form:label for="name" path="name" class="col-sm-6 col-form-label">Factory Name:</form:label>
                                <form:input type="text" name="name" path="name" class="form-control" id="name" placeholder="Factory Name" required="true" />
                            </div>
                            <div class="col-md-6 form-group">
                                <form:label for="typeOfProperty" path="typeOfProperty" class="col-sm-6 col-form-label">Type of Property:</form:label>
                                <form:input type="text" class="form-control" name="typeOfProperty" path="typeOfProperty" id="typeOfProperty" placeholder="Type of property" required="true" />
                            </div>
                            <div class="form-group col-12">
                                <form:label for="address" path="address" class="col-sm-6 col-form-label">Address:</form:label>
                                <form:input type="text" class="form-control" name="address" path="address" id="address" placeholder="Address" required="true" />
                            </div>
                            <div class="form-group col-12">
                                <form:label for="telephone" path="telephone" class="col-sm-6 col-form-label">Telephone:</form:label>
                                <form:input type="text" class="form-control" name="telephone" path="telephone" id="telephone" placeholder="Telephone" required="true" />
                            </div>
                            <div class="my-3 col-12">
                                <div class="loading">Loading</div>
                                <div class="error-message"></div>
                                <div class="sent-message">Your message has been sent. Thank you!</div>
                            </div>
                            <div class="text-center col-12"><form:button type="submit">Add client</form:button></div>
                        </div>
                    </form:form>

                </div>

            </div>

        </div>
    </section><!-- End Contact Section -->

</main><!-- End #main -->
<br /><br />
<%@ include file="jspf/footer.jspf" %>

<div id="preloader"></div>
<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="${request.contextPath}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
<script src="${request.contextPath}/assets/vendor/aos/aos.js"></script>
<script src="${request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="${request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="${request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>


<!-- Template Main JS File -->
<script src="${request.contextPath}/assets/js/main.js"></script>

</body>

</html>