<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Investments</title>
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
    <link href="${request.contextPath}/assets/css/new_styles.css" rel="stylesheet">
</head>

<body>

<%@ include file="jspf/nav.jspf" %>

<main id="main">

    <!-- ======= Pricing Section ======= -->
    <section id="pricing" class="pricing" style="margin-top: 6em;">
        <div class="container" data-aos="fade-up">

            <div class="section-title">
                <h2>Buy investments</h2>
                <p>Here you can see the type and the number of investments that you can buy. To make it true, just click on Show Now button</p>
            </div>

            <p class="text-success text-center"><c:if test="${success == 1}">Investment successfully added</c:if></p>
            <p class="text-danger text-center"><c:if test="${error == 0}">Investment cannot be added</c:if></p>

            <div class="row justify-content-center">

                <div class="col-lg-4 col-md-6" data-aos="zoom-im" data-aos-delay="100">
                    <div class="box deposited">
                        <h3>Deposits</h3>
<!--                        <h4>-->

                                <div class="count-box">
                                    <h4>${depositsCount}</h4>
                                    <h5>available</h5>
                                </div>

<!--                        </h4>-->
                        <div class="btn-wrap">
                            <a href="deposits" class="btn-buy">Show Now</a>
                        </div>
<%
                        if(user.getRole().equals("ADMIN")){
                            out.println("<div class='btn-wrap'>");
                            out.println("<a href='#' class='btn-buy' style='background-color: #24CE6B;' data-toggle='modal' data-target='#exampleModalCenter1'>Add new</a>");
                            out.println("</div>");
                        }
%>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mt-4 mt-md-0" data-aos="zoom-in" data-aos-delay="100">
                    <div class="box featured">
                        <h3>Shares</h3>
                        <div class="count-box">
                            <h4>${sharesCount}</h4>
                            <h5>available</h5>
                        </div>
                        <div class="btn-wrap">
                            <a href="shares" class="btn-buy">Show Now</a>
                        </div>
<%
                        if(user.getRole().equals("ADMIN")){
                            out.println("<div class='btn-wrap'>");
                            out.println("<a href='#' class='btn-buy' style='background-color: #24CE6B;' data-toggle='modal' data-target='#exampleModalCenter2'>Add new</a>");
                            out.println("</div>");
                        }
%>
                    </div>
                </div>



            </div>

        </div>
    </section><!-- End Pricing Section -->


</main><!-- End #main -->
<br /><br />
<%@ include file="jspf/footer.jspf" %>

<!-- Modal1 -->
<div class="modal fade" id="exampleModalCenter1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle1">Add deposit</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="bi bi-x"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <p class="text-center">Do you really want to add new deposit? <br />Fill the form</p>
                <section id="contact1" class="fill_data">
                    <div class="container" data-aos="fade-up">
                        <div class="row mt-5 justify-content-center">
                            <div class="col-lg-8 mt-5 mt-lg-0">

                                <form:form action="deposits/add" method="post" role="form" class="php-email-form" modelAttribute="deposit">
                                    <div class="row gy-2 gx-md-3">
                                        <div class="form-group col-12">
                                            <form:label for="deposit_name" path="name" class="col-sm-6 col-form-label">Name:</form:label>
                                            <form:input type="text" class="form-control" path="name" name="name" id="deposit_name" placeholder="Name" required="true" />
                                        </div>
                                        <div class="form-group col-12">
                                            <form:label for="bankName" path="bankName" class="col-sm-6 col-form-label">Bank name:</form:label>
                                            <form:input type="text" class="form-control" path="bankName" name="bankName" id="bankName" placeholder="Bank name" required="true" />
                                        </div>
                                        <div class="form-group col-12">
                                            <form:label for="interestRate" path="interestRate" class="col-sm-6 col-form-label">Interest rate:</form:label>
                                            <form:input type="text" class="form-control" path="interestRate" name="interestRate" id="interestRate" placeholder="Interest rate" required="true" />
                                        </div>
                                        <div class="form-group col-12">
                                            <form:label for="term_deposit" path="term" class="col-sm-2 col-form-label">Term:</form:label>
                                            <form:input type="date" class="form-control" path="term" name="term" id="term_deposit" placeholder="Term" required="true" />
                                        </div>
                                        <div class="my-3 col-12">
                                            <div class="loading">Loading</div>
                                            <div class="error-message"></div>
                                            <div class="sent-message">Your message has been sent. Thank you!</div>
                                        </div>
                                        <div class="text-center col-12"><form:button type="submit">Add</form:button></div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Discard</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal2 -->
<div class="modal fade" id="exampleModalCenter2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle2">Add share</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="bi bi-x"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <p class="text-center">Do you really want to add new share? <br />Fill the form</p>
                <section id="contact2" class="fill_data">
                    <div class="container" data-aos="fade-up">
                        <div class="row mt-5 justify-content-center">
                            <div class="col-lg-8 mt-5 mt-lg-0">

                                <form:form action="shares/add" method="post" role="form" class="php-email-form" modelAttribute="share">
                                    <div class="row gy-2 gx-md-3">
                                        <div class="form-group col-12">
                                            <form:label for="share_name" path="name" class="col-sm-6 col-form-label">Name:</form:label>
                                            <form:input type="text" class="form-control" path="name" name="name" id="share_name" placeholder="Name" required="true" />
                                        </div>
                                        <div class="form-group col-12">
                                            <form:label for="price" path="price" class="col-sm-6 col-form-label">Price:</form:label>
                                            <form:input type="text" class="form-control" path="price" name="price" id="price" placeholder="Price" required="true" />
                                        </div>
                                        <div class="my-3 col-12">
                                            <div class="loading">Loading</div>
                                            <div class="error-message"></div>
                                            <div class="sent-message">Your message has been sent. Thank you!</div>
                                        </div>
                                        <div class="text-center col-12"><form:button type="submit">Add</form:button></div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Discard</button>
            </div>
        </div>
    </div>
</div>


<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>


<script>
    $('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})
</script>

<!-- Vendor JS Files -->
<script src="${request.contextPath}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
<script src="${request.contextPath}/assets/vendor/aos/aos.js"></script>
<script src="${request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="${request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="${request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.6/dist/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>


<!-- Template Main JS File -->
<script src="${request.contextPath}/assets/js/main.js"></script>

</body>

</html>