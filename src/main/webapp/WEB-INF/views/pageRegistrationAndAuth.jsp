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
                <h2>new user? <span> create an account </span></h2>
                <div class="registration_form">
                    <!-- Form -->
                    <form id="registration_form" action="" method="post">
                        <div>
                            <label>
                                <input type="text" name="login" tabindex="3"
                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                       title="Login should contain e-mail address"
                                       autofocus required placeholder="login:"
                                       value=${newUser.login}>
                            </label>
                        </div>
                        <div class="layer">${errorLogin}</div>
                        <div>
                            <label>
                                <input placeholder="name:" type="text" tabindex="1" name="name"
                                       pattern="[A-Z]{1}[a-z]+$"
                                       title="Name should contain latin letters, first letter uppercase, min-2 max-10 symbols"
                                       autofocus required
                                       value=${newUser.name}>
                            </label>
                        </div>
                        <div class="layer">${errorName}</div>
                        <div class="sky-form">
                            <select name="region" autofocus required>
                                <option value="">Choose region</option>
                                <option value="Kyiv" <c:if test="${newUser.region eq 'Kyiv'}">selected</c:if>>
                                    Kyiv
                                </option>
                                <option value="Kharkiv" <c:if test="${newUser.region eq 'Kharkiv'}">selected</c:if>>
                                    Kharkiv
                                </option>
                                <option value="Lviv" <c:if test="${newUser.region eq 'Lviv'}">selected</c:if>>
                                    Lviv
                                </option>
                            </select>
                        </div>
                        <div class="layer">${errorRegion}</div>
                        <div class="sky-form">
                            <div class="sky_form1">
                                <ul>
                                    <li><label class="radio left"><input type="radio"
                                                                         <c:if test="${newUser.gender}">checked</c:if>
                                                                         value="male" name="gender"><i></i>Male</label>
                                    </li>
                                    <li><label class="radio"><input type="radio"
                                                                    <c:if test="${newUser!=null && !newUser.gender}">checked</c:if>
                                                                    value="female" name="gender"><i></i>Female</label>
                                    </li>
                                    <div class="clearfix"></div>
                                </ul>
                            </div>
                        </div>
                        <div class="layer">${errorGender}</div>
                        <div>
                            <label>
                                <input placeholder="password" type="password" tabindex="4" name="password"
                                       pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*"
                                       title="Password should contain minimum one lowercase and one uppercase letters and digits, min-8 symbols"
                                       autofocus required
                                       maxlength="25"
                                       value="">
                            </label>
                        </div>
                        <div class="layer">${errorPass}</div>
                        <div>
                            <label>
                                <input placeholder="retype password" type="password" tabindex="4" name="repassword"
                                       pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*"
                                       title="Password should contain minimum one lowercase and one uppercase letters and digits, min-8 symbols"
                                       maxlength="25"
                                       autofocus required
                                       value="">
                            </label>
                        </div>

                        <div>
                            <label>
									<textarea type="text" rows="3" cols="60" maxlength="350" autofocus required
                                              placeholder="comment"
                                              name="comment">${newUser.comment}</textarea>
                            </label>
                        </div>
                        <div class="layer">${errorComment}</div>
                        <div class="sky-form">
                            <label class="checkbox"><input type="checkbox" autofocus required name="agreement"><i></i>i
                                agree &nbsp;<a class="terms" href="#"> terms of service</a> </label>
                        </div>
                        <div class="layer">${errorAgreement}</div>
                        <div>
                            <input type="hidden" name="key" value="reg">
                            <input type="submit" value="create an account" id="register-submit">
                        </div>
                    </form>
                    <!-- /Form -->
                </div>
            </div>
            <div class="registration_left">
                <h2>existing user</h2>
                <div class="registration_form">
                    <!-- Form -->
                    <form id="registration_form" action="" method="post">
                        <div>
                            <label>
                                <input placeholder="email:" tabindex="3" type="text" name="login" id="login"
                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                       title="Login should contain e-mail address"
                                       required
                                       value="${authLogin}"/>
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
            </div>
            <div class="clearfix"></div>
        </div>
        <!-- end registration -->
    </div>
</div>
<!-- footer -->
<%@include file="includes/footer.jsp" %>
