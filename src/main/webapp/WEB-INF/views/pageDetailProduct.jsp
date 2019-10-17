<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- header -->
<%@include file="includes/header.jsp" %>
<!-- content -->
<div class="container">
    <div class="women_main">
        <!-- start content -->
        <div class="row single">
            <div class="col-md-9 det">
                <div class="single_left">
                    <div class="grid images_3_of_2">
                        <a href="optionallink.html">
                            <img class="etalage_thumb_image" width="260"
                                 src="${pageContext.servletContext.contextPath}/resources/images/w${productById.id}.jpg"
                                 class="img-responsive"/>
                        </a>
                        <div class="clearfix"></div>
                    </div>
                    <div class="desc1 span_3_of_2">
                        <h3>${productById.name}</h3>
                        <span class="brand">Brand: <a href="#">${productById.name}</a></span>
                        <br>
                        <span class="code">Product Code: ${productById.id}</span>
                        <p>${productById.description}</p>
                        <div class="price">
                            <span class="text">Price:</span>
                            <span class="price-new">$${productById.price}.00</span><span
                                class="price-old">$${productById.price+75}.00</span>
                            <span class="price-tax">Ex Tax: $${productById.price-20}.00</span><br>
                        </div>
                        <div class="det_nav1">
                            <h4>quantity :</h4>

                            <div class="quantity-block">
                                <button class="quantity-arrow-minus" id="imgMinus" onclick="minus(${productById.id})"> -</button>
                                <span class="raz" id="span">1</span>
                                <button class="quantity-arrow-plus" id="imgPlus" onclick="plus(${productById.id})"> +</button>
                            </div>

                        </div>
                        <div class="btn_form">
                            <a href="${pageContext.servletContext.contextPath}/cart">buy</a>
                        </div>
                        <a href="${pageContext.servletContext.contextPath}/registration"><span>login to save in wishlist</span></a>
                        <script>
                            var number = 0;

                            function plus(id) {
                                var number = document.getElementById("span").innerHTML;
                                if (number < 10) {
                                    number++;
                                    addToCart(id)
                                    document.getElementById("span").innerHTML = number;
                                }
                            }

                            function minus(id) {
                                var number = document.getElementById("span").innerHTML;
                                if (number > 1) {
                                    number--;
                                    addToCart(id)
                                    document.getElementById("span").innerHTML = number;
                                }
                            }

                            function addToCart(id) {
                                var num = document.getElementById("span").innerHTML;
                                $.ajax
                                ({
                                    data: "productId=" + id + "&qnt=" + num,
                                    type: 'POST',
                                    url: "${pageContext.servletContext.contextPath}/cart",
                                    success : function(response){
                                       /* value = response.company;*/
                                        console.log(response)
                                    }
                                });
                            }
                        </script>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="single-bottom1">
                    <h6>Details</h6>
                    <p class="prod-desc">Bla bla bla bla ${productById.getDescription()} bla bla bla bla bla bla bla bla
                        bla bla bla bla bla bla bla bla bla bla</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="w_sidebar">
                    <div class="w_nav1">
                        <h4>All</h4>
                        <ul>
                            <li><a href="#">women</a></li>
                            <li><a href="#">new arrivals</a></li>
                            <li><a href="#">trends</a></li>
                            <li><a href="#">boys</a></li>
                            <li><a href="#">girls</a></li>
                            <li><a href="#">sale</a></li>
                        </ul>
                    </div>
                    <h3>filter by</h3>
                    <section class="sky-form">
                        <h4>catogories</h4>
                        <div class="row1 scroll-pane">
                            <div class="col col-4">
                                <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>kurtas</label>
                            </div>
                            <div class="col col-4">
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>kutis</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>churidar
                                    kurta</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>salwar</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>printed
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>shree</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion
                                    sari</label>
                            </div>
                        </div>
                    </section>
                    <section class="sky-form">
                        <h4>brand</h4>
                        <div class="row1 scroll-pane">
                            <div class="col col-4">
                                <label class="checkbox"><input type="checkbox" name="checkbox"
                                                               checked=""><i></i>shree</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                            </div>
                            <div class="col col-4">
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>vishud</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>amari</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>shree</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>shree</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>shree</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                            </div>
                        </div>
                    </section>
                    <section class="sky-form">
                        <h4>colour</h4>
                        <ul class="w_nav2">
                            <li><a class="color1" href="#"></a></li>
                            <li><a class="color2" href="#"></a></li>
                            <li><a class="color3" href="#"></a></li>
                            <li><a class="color4" href="#"></a></li>
                            <li><a class="color5" href="#"></a></li>
                            <li><a class="color6" href="#"></a></li>
                            <li><a class="color7" href="#"></a></li>
                            <li><a class="color8" href="#"></a></li>
                            <li><a class="color9" href="#"></a></li>
                            <li><a class="color10" href="#"></a></li>
                            <li><a class="color12" href="#"></a></li>
                            <li><a class="color13" href="#"></a></li>
                            <li><a class="color14" href="#"></a></li>
                            <li><a class="color15" href="#"></a></li>
                            <li><a class="color5" href="#"></a></li>
                            <li><a class="color6" href="#"></a></li>
                            <li><a class="color7" href="#"></a></li>
                            <li><a class="color8" href="#"></a></li>
                            <li><a class="color9" href="#"></a></li>
                            <li><a class="color10" href="#"></a></li>
                        </ul>
                    </section>
                    <section class="sky-form">
                        <h4>discount</h4>
                        <div class="row1 scroll-pane">
                            <div class="col col-4">
                                <label class="radio"><input type="radio" name="radio" checked=""><i></i>60 % and
                                    above</label>
                                <label class="radio"><input type="radio" name="radio"><i></i>50 % and above</label>
                                <label class="radio"><input type="radio" name="radio"><i></i>40 % and above</label>
                            </div>
                            <div class="col col-4">
                                <label class="radio"><input type="radio" name="radio"><i></i>30 % and above</label>
                                <label class="radio"><input type="radio" name="radio"><i></i>20 % and above</label>
                                <label class="radio"><input type="radio" name="radio"><i></i>10 % and above</label>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>

        <!-- end content -->
        <!-- footer -->
<%@include file="includes/footer.jsp" %>