<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="assets/css/style_login.css">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">

			<div class="row justify-content-center">
         <p class="text-success text-center"><c:if test="${success == 1}">User successfully registered</c:if></p>
         <p class="text-danger text-center"><c:if test="${error == 0}">Cannot login, check credentials</c:if></p>

				<div class="col-md-7 col-lg-5 mt-5">

					<div class="wrap">

						<div class="login-wrap p-4 p-md-5">
						${SPRING_SECURITY_LAST_EXCEPTION.message}
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">Sign In</h3>
			      		</div>

			      	</div>
							<form action="/login" class="signin-form" method="POST">
			      		<div class="form-group mt-3">
			      			<input name="username" id="username" type="text" class="form-control" required>
			      			<label class="form-control-placeholder" for="username">Username</label>
			      		</div>
		            <div class="form-group">
		              <input name="password" id="password-field" type="password" class="form-control" required>
		              <label class="form-control-placeholder" for="password-field">Password</label>
		              <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
		            </div>
		            <div class="form-group">
		            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Sign In</button>
		            </div>

		          </form>
		          <p class="text-center">Not a member? <a href="/signup">Sign Up</a></p>
		        </div>
		      </div>
				</div>
			</div>
		</div>
	</section>

	<script src="assets/js/jquery.min.js"></script>
  <script src="assets/js/popper.js"></script>
  <script src="assets/js/bootstrap.min.js"></script>
  <script src="assets/js/main_login.js.js"></script>

	</body>
</html>

