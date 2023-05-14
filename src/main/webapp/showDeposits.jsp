<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Deposits</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="${request.contextPath}/assets/img/favicon.png" rel="icon">
    <link href="${request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

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

<!-- ======= Hero Section ======= -->

<%@ include file="jspf/nav.jspf" %>

<main id="main">
    <section id="hero_two" class="d-flex align-items-center">
        <div class="container position-relative" data-aos="fade-up" data-aos-delay="100">

            <c:set var="icons" value="${['ri-stack-line', 'ri-palette-line', 'ri-command-line', 'ri-fingerprint-line', 'ri-bank-line', 'ri-at-line', 'ri-cpu-line', 'ri-wifi-line']}" />

            <div class="row icon-boxes">
                <c:forEach items="${deposits.elements}" var="deposit" varStatus="loop">
                <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0" data-aos="zoom-in"
                     data-aos-delay="${loop.index}00">
                    <div class="icon-box">
                        <div class="icon"><i class="${icons[loop.index]}"></i></div>
                        <h4 class="title"><a href="" data-bs-toggle="modal" data-bs-target="#exampleModalCenter" data-bs-id="${deposit.id}">
                            ${deposit.name}
                        </a></h4>
                        <p class="description">Bank: ${deposit.bankName}. <br />Interest rate: ${deposit.interestRate}. <br />Term: min <fmt:formatDate type="date" dateStyle="medium" value="${deposit.term}" /></p>
                    </div>
                </div>
                </c:forEach>
            </div>
            <br />

            <p class="text-success text-center"><c:if test="${success == 1}">Deposit successfully confirmed</c:if></p>
            <p class="text-danger text-center"><c:if test="${error == 0}">Deposit cannot be confirmed</c:if></p>

            <c:if test="${deposits.totalPages > 0}">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <nav>
                    <ul class="pagination">
                        <li class="page-item ${deposits.page == 1 ? 'disabled' : ''}">
                            <a class="page-link" href="?page=${deposits.page < 1 ? 0 : deposits.page - 1}" tabindex="-1">Previous</a>
                        </li>

                           <c:set var="num_page" value="${deposits.page}" />
                           <c:forEach begin="${deposits.page}"
                               end="${deposits.totalPages >= deposits.page+4 ? num_page+3 : deposits.totalPages}"
                               step="1" var="i">

                                   <li class="page-item ${i == deposits.page ? 'active' : ''}">
                                       <c:choose>
                                       <c:when test="${i == deposits.page}">
                                           <a class="page-link" href="#">${i}</a>
                                       </c:when>
                                       <c:otherwise>
                                           <a class="page-link" href="?page=${i}">
                                               ${i}
                                           </a>
                                       </c:otherwise>
                                       </c:choose>
                                   </li>

                           </c:forEach>

                        <c:if test="${deposits.page + 4 <= deposits.totalPages}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">...</a>
                            </li>
                        </c:if>

                        <li class="page-item ${deposits.page == deposits.totalPages ? 'disabled' : ''}">
                            <a class="page-link" href="?page=${deposits.page == deposits.totalPages ? 1 : deposits.page + 1}">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
            </c:if>
        </div>
    </section><!-- End Hero -->

</main><!-- End #main -->
<br /><br />
<%@ include file="jspf/footer.jspf" %>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Submit purchase</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="bi bi-x"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <p class="text-center">Do you really want to make this deposit? <br />Fill the form</p>
                <section id="contact" class="fill_data">
                    <div class="container" data-aos="fade-up">
                        <div class="row mt-5 justify-content-center">
                            <div class="col-lg-8 mt-5 mt-lg-0">

                                <form action="investments/add" id="submit_form_modal" method="post" role="form" class="php-email-form">
                                    <div class="row gy-2 gx-md-3">

                                            <input type="hidden" name="investmentId" class="form-control" id="investmentId" />
                                            <input type="hidden" name="clientId" class="form-control" id="clientId" />
                                            <input type="hidden" name="investmentType" class="form-control" id="investmentType" />

                                        <div class="col-md-12 form-group">
                                            <label for="investmentAmount" class="col-sm-2 col-form-label">Amount:</label>
                                            <input type="text" name="investmentAmount" class="form-control" id="investmentAmount" placeholder="Amount" required />
                                        </div>
                                        <div class="form-group col-12">
                                            <label for="term" class="col-sm-2 col-form-label">Term:</label>
                                            <input type="date" class="form-control" name="term" id="term" placeholder="Term" required />
                                        </div>
                                        <div class="my-3 col-12">
                                            <div class="loading">Loading</div>
                                            <div class="error-message"></div>

                                            <div class="sent-message">Your message has been sent. Thank you!</div>
                                        </div>
                                        <div class="text-center col-12"><button type="submit" id="button_submit">Buy</button></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>


<script>

 $('#button_submit').click(function() {

    $('#submit_messsage_paragr').show();
    return false;
});

</script>

<script>
var exampleModal = document.getElementById('exampleModalCenter')
exampleModal.addEventListener('show.bs.modal', function (event) {
  // Button that triggered the modal
  var button = event.relatedTarget
  // Extract info from data-bs-* attributes
  var id = button.getAttribute('data-bs-id')

  var modalTitle = exampleModal.querySelector('.modal-title')
  var modalBodyInput = exampleModal.querySelector('.modal-body input[name="investmentId"]')

  modalTitle.textContent = 'Submit purchase for Deposit ' + id
  modalBodyInput.value = id

  document.getElementById('clientId').value = '1';
  document.getElementById('investmentType').value = 'deposit';
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