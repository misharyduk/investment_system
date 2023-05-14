<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Sign Up</title>
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
         <p class="text-danger text-center"><c:if test="${error == 0}">Cannot register user</c:if></p>
            <div class="col-md-7 col-lg-5 mt-5">
                <div class="wrap">

                    <div class="login-wrap p-4 p-md-5">
                        <div class="d-flex">
                            <div class="w-100">
                                <h3 class="mb-4">Sign In</h3>
                            </div>

                        </div>
                        <form:form action="/signup" class="signin-form" method="POST" modelAttribute="client">
                            <div class="form-group mt-3">
                                <form:input name="username" path="username" id="username" type="text" class="form-control"/>
                                <form:label class="form-control-placeholder" for="username" path="username">Username</form:label>
                            </div>
                            <div class="form-group">
                                <form:input style="mt-2" name="password" path="password" id="password-field" type="password" class="form-control"/>
                                <form:label class="form-control-placeholder" for="password-field" path="password">Password</form:label>
                                <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                            </div>
                            <div class="form-group">
                                <form:input style="mt-2" name="name" path="name" id="name" type="text" class="form-control"/>
                                <form:label class="form-control-placeholder" for="name" path="name">Factory Name</form:label>
                            </div>
                            <div class="form-group">
                                <form:input style="mt-2" name="typeOfProperty" path="typeOfProperty" id="typeOfProperty" type="text" class="form-control"/>
                                <form:label class="form-control-placeholder" for="typeOfProperty" path="typeOfProperty">Type of Property</form:label>
                            </div>
                            <div class="form-group">
                                <form:input style="mt-2" name="address" path="address" id="address" type="text" class="form-control"/>
                                <form:label class="form-control-placeholder" for="address" path="address">Address</form:label>
                            </div>
                            <div class="form-group">
                                <form:input style="mt-2" name="telephone" path="telephone" id="telephone" type="text" class="form-control"/>
                                <form:label class="form-control-placeholder" for="telephone" path="telephone">Telephone</form:label>
                            </div>
                            <div class="form-group">
                                <form:button type="submit" class="form-control btn btn-primary rounded submit px-3">Sign Up</form:button>
                            </div>

                        </form:form>
                        <p class="text-center">Already a member? <a href="/login">Log In</a></p>
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

