<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>


<!-- header -->
<%@include file="includes/header.jsp" %>
<!-- content -->
<div class="container">
    <div class="main">
        <!-- start registration -->
        <div class="registration">
            <div class="registration_left">
                <center><h2>Dear ${name}.<span>Your profile was updated successfully!</span></h2></center>
                <div class="clearfix"></div>
            </div>
        </div>
        <!-- end registration -->
    </div>
</div>
<!-- footer -->
<%@include file="includes/footer.jsp" %>