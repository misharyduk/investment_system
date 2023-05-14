<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>${client.factory.name}</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

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

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs"  style="margin-top: 6em;">
        <div class="container">

            <div class="d-flex justify-content-between align-items-center">
                <h2>Portfolio Details</h2>
                <ol>
                    <li><a href="${request.contextPath}/clients">Clients</a></li>
                    <li>Portfolio Details</li>
                </ol>
            </div>

        </div>
    </section><!-- End Breadcrumbs -->

    <!-- ======= Portfolio Details Section ======= -->
    <section id="portfolio-details" class="portfolio-details">
        <div class="container">

            <div class="row gy-4">

                <div class="col-lg-8">
                    <div class="portfolio-description">
                        <h2>${client.factory.name}</h2>
                        <p>
                            Representative of ${client.factory.name}. <br/>
                            Factory already has ${client.investments.size()} investments. You can see list of then below. <br/>
                            Factory is based on address ${client.factory.address}.
                        </p>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="portfolio-info">
                        <h3>Factory information</h3>
                        <ul>
                            <li><strong>Name</strong>: ${client.factory.name}</li>
                            <li><strong>Type of property</strong>: ${client.factory.typeOfProperty}</li>
                            <li><strong>Address</strong>: ${client.factory.address}</li>
                            <li><strong>Telephone</strong>: ${client.factory.telephone}</li>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- ======= Frequently Asked Questions Section ======= -->
            <section id="faq" class="faq section-bg">
                <div class="container" data-aos="fade-up">

                    <div class="section-title">
                        <h2>List of Investments</h2>
                        <p>Here you can see <strong>${client.investments.size()} investments</strong> - all investments that factory has. <br /><strong>Deposits: ${deposits.size()}</strong>. <strong>Shares: ${shares.size()}</strong></p>
                    </div>

                    <p class="text-success text-center"><c:if test="${success == 1}">Investment successfully deleted</c:if></p>
                    <p class="text-danger text-center"><c:if test="${error == 0}">Investment cannot be deleted</c:if></p>

                    <div class="faq-list">
                        <ul>
                            <c:forEach items="${deposits}" var="deposit" varStatus="loop">
                            <li data-aos="fade-up" data-aos-delay="${loop.index + 1}00">
                                <i class="bx bx-help-circle icon-help"></i> <a data-bs-toggle="collapse" class="collapse" data-bs-target="#faq-list-${loop.index + 1}">Investment ${loop.index + 1}: ${deposit.pureDeposit.name} (deposit) <i class="bx bx-chevron-down icon-show"></i><i class="bx bx-chevron-up icon-close"></i></a>
                                <div id="faq-list-${loop.index + 1}" class="collapse show" data-bs-parent=".faq-list">
                                    <p>
                                        Investment amount: ${deposit.amount}$. <br />Term: <fmt:formatDate type="date" dateStyle="medium" value="${deposit.term}" />. <br />Bank name: ${deposit.pureDeposit.bankName}. <br />Interest rate: ${deposit.pureDeposit.interestRate}%. <br /><strong>Profit</strong>: ${deposit.profit}.
                                    </p>
                                    <br />
                                    <form action="${request.contextPath}/investments/delete?investmentId=${deposit.investmentId}&clientId=${client.id}&investmentType=deposit" method="post">
                                        <input class="btn btn-danger btn-sm" type="submit" value="Delete investment" />
                                    </form>
                                </div>
                            </li>
                            </c:forEach>

                            <c:forEach items="${shares}" var="share" varStatus="loop">
                                <li data-aos="fade-up" data-aos-delay="${loop.index + 1 + deposits.size()}00">
                                    <i class="bx bx-help-circle icon-help"></i> <a data-bs-toggle="collapse" class="collapse" data-bs-target="#faq-list-${loop.index + 1 + deposits.size()}">Investment ${loop.index + 1 + deposits.size()}: ${share.pureShare.name} (share) <i class="bx bx-chevron-down icon-show"></i><i class="bx bx-chevron-up icon-close"></i></a>
                                    <div id="faq-list-${loop.index + 1 + deposits.size()}" class="collapse show" data-bs-parent=".faq-list">
                                        <p>
                                            Price: ${share.amount}$. <br />Term: <fmt:formatDate type="date" dateStyle="medium" value="${share.term}" />. <br /><strong>Profit</strong>: ${share.profit}.
                                        </p>
                                        <br />
                                        <form action="${request.contextPath}/investments/delete?investmentId=${share.investmentId}&clientId=${client.id}&investmentType=share" method="post">
                                            <input class="btn btn-danger btn-sm" type="submit" value="Delete investment" />
                                        </form>

                                    </div>
                                </li>
                            </c:forEach>

<!--                            <li data-aos="fade-up" data-aos-delay="100">-->
<!--                                <i class="bx bx-help-circle icon-help"></i> <a data-bs-toggle="collapse" data-bs-target="#faq-list-2" class="collapsed">Investment 2 <i class="bx bx-chevron-down icon-show"></i><i class="bx bx-chevron-up icon-close"></i></a>-->
<!--                                <div id="faq-list-2" class="collapse" data-bs-parent=".faq-list">-->
<!--                                    <p>-->
<!--                                        Dolor sit amet consectetur adipiscing elit pellentesque habitant morbi. Id interdum velit laoreet id donec ultrices. Fringilla phasellus faucibus scelerisque eleifend donec pretium. Est pellentesque elit ullamcorper dignissim. Mauris ultrices eros in cursus turpis massa tincidunt dui.-->
<!--                                    </p>-->
<!--                                </div>-->
<!--                            </li>-->

<!--                            <li data-aos="fade-up" data-aos-delay="200">-->
<!--                                <i class="bx bx-help-circle icon-help"></i> <a data-bs-toggle="collapse" data-bs-target="#faq-list-3" class="collapsed">Investment 3 <i class="bx bx-chevron-down icon-show"></i><i class="bx bx-chevron-up icon-close"></i></a>-->
<!--                                <div id="faq-list-3" class="collapse" data-bs-parent=".faq-list">-->
<!--                                    <p>-->
<!--                                        Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis orci. Faucibus pulvinar elementum integer enim. Sem nulla pharetra diam sit amet nisl suscipit. Rutrum tellus pellentesque eu tincidunt. Lectus urna duis convallis convallis tellus. Urna molestie at elementum eu facilisis sed odio morbi quis-->
<!--                                    </p>-->
<!--                                </div>-->
<!--                            </li>-->

<!--                            <li data-aos="fade-up" data-aos-delay="300">-->
<!--                                <i class="bx bx-help-circle icon-help"></i> <a data-bs-toggle="collapse" data-bs-target="#faq-list-4" class="collapsed">Investment 4 <i class="bx bx-chevron-down icon-show"></i><i class="bx bx-chevron-up icon-close"></i></a>-->
<!--                                <div id="faq-list-4" class="collapse" data-bs-parent=".faq-list">-->
<!--                                    <p>-->
<!--                                        Molestie a iaculis at erat pellentesque adipiscing commodo. Dignissim suspendisse in est ante in. Nunc vel risus commodo viverra maecenas accumsan. Sit amet nisl suscipit adipiscing bibendum est. Purus gravida quis blandit turpis cursus in.-->
<!--                                    </p>-->
<!--                                </div>-->
<!--                            </li>-->

<!--                            <li data-aos="fade-up" data-aos-delay="400">-->
<!--                                <i class="bx bx-help-circle icon-help"></i> <a data-bs-toggle="collapse" data-bs-target="#faq-list-5" class="collapsed">Investment 5 <i class="bx bx-chevron-down icon-show"></i><i class="bx bx-chevron-up icon-close"></i></a>-->
<!--                                <div id="faq-list-5" class="collapse" data-bs-parent=".faq-list">-->
<!--                                    <p>-->
<!--                                        Laoreet sit amet cursus sit amet dictum sit amet justo. Mauris vitae ultricies leo integer malesuada nunc vel. Tincidunt eget nullam non nisi est sit amet. Turpis nunc eget lorem dolor sed. Ut venenatis tellus in metus vulputate eu scelerisque.-->
<!--                                    </p>-->
<!--                                </div>-->
<!--                            </li>-->

                        </ul>
                    </div>

                </div>
            </section><!-- End Frequently Asked Questions Section -->
        </div>
    </section><!-- End Portfolio Details Section -->

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
<script src="${request.contextPath}/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="${request.contextPath}/assets/js/main.js"></script>

</body>

</html>