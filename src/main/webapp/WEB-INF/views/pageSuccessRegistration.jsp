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
                <center><h2>User ${createdUser.name} <span>created!</span></h2></center>

                <div class="registration_form">
                    <!-- Form -->
                    <form id="registration_form" action="${pageContext.servletContext.contextPath}/registration"
                          method="post">
                        <div>
                            <label>
                                <input tabindex="3" type="text" name="login" id="login"
                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                       title="Login should contain e-mail address"
                                       required
                                       value="${createdUser.login}"/>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="password" name="password" id="pass" required placeholder="password"
                                />
                            </label>
                        </div>
                        <div class="layer">${enteringError}</div>
                        <div>
                            <input type="hidden" name="key" value="auth">
                            <input type="submit" value="sign in" id="register-submit">
                        </div>
                        <div class="forget">
                            <a href="#">forgot your password</a>
                        </div>
                    </form>
                    <!-- /Form -->
                </div>

                <div class="clearfix"></div>
            </div>
        </div>
        <!-- end registration -->
    </div>
</div>
<!-- footer -->
<%@include file="includes/footer.jsp" %>
