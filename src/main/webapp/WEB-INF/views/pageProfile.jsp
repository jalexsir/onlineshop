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
                <center><h2>Change info of <span>${user.login}</span></h2></center>
                <div class="registration_form">
                    <!-- Form -->
                    <form id="registration_form" action="" method="post">
                        <div>
                            <label>
                                <input placeholder="name:" type="text" tabindex="1" name="name"
                                       pattern="[A-Z]{1}[a-z]+$"
                                       title="Name should contain latin letters, first letter uppercase, min-2 max-10 symbols"
                                       autofocus required
                                       value=${user.name}>
                            </label>
                        </div>
                        <div class="layer">${errorName}</div>
                        <div class="sky-form">
                            <select name="region" autofocus required>
                                <option value="">Choose region</option>
                                <option value="Kyiv" <c:if test="${user.region eq 'Kyiv'}">selected</c:if>>
                                    Kyiv
                                </option>
                                <option value="Kharkiv" <c:if test="${user.region eq 'Kharkiv'}">selected</c:if>>
                                    Kharkiv
                                </option>
                                <option value="Lviv" <c:if test="${user.region eq 'Lviv'}">selected</c:if>>
                                    Lviv
                                </option>
                            </select>
                        </div>
                        <div class="layer">${errorRegion}</div>
                        <div class="sky-form">
                            <div class="sky_form1">
                                <ul>
                                    <li><label class="radio left"><input type="radio"
                                                                         <c:if test="${user.gender}">checked</c:if>
                                                                         value="male" name="gender"><i></i>Male</label>
                                    </li>
                                    <li><label class="radio"><input type="radio"
                                                                    <c:if test="${user!=null && !user.gender}">checked</c:if>
                                                                    value="female" name="gender"><i></i>Female</label>
                                    </li>
                                    <div class="clearfix"></div>
                                </ul>
                            </div>
                        </div>
                        <div class="layer">${errorGender}</div>
                        <div>
                            <label>
									<textarea type="text" rows="3" cols="60" maxlength="350" autofocus required
                                              placeholder="comment"
                                              name="comment">${user.comment}</textarea>
                            </label>
                        </div>
                        <div class="layer">${errorComment}</div>
                        <div class="sky-form">
                            <label class="checkbox"><input type="checkbox" autofocus required name="agreement"><i></i>i
                                agree &nbsp;<a class="terms" href="#"> terms of service</a> </label>
                        </div>
                        <div class="layer">${errorAgreement}</div>
                        <div>
                            <input type="submit" value="update an account" id="register-submit">
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