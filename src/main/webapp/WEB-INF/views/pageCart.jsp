<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@include file="includes/header.jsp" %>
<%--body start--%>
<div class="container">
    <div class="check">

        <%--cart left panel start--%>
        <div class="col-md-3 cart-total">
            <a class="continue" href="${pageContext.servletContext.contextPath}/cart">Update basket</a>
            <div class="price-details">
                <h3>Price Details</h3>
                <span>Total</span>
                <span class="total1">${summ} $</span>
                <span>Discount</span>
                <span class="total1">${(elogin!=null)?(summ*0.15):("0%") } ${(elogin!=null)?("$ (15%)"):""}</span>
                <span>Delivery Charges</span>
                <span class="total1">${summ!=null?(50.00):(0)} $</span>
                <div class="clearfix"></div>
            </div>
            <ul class="total_price">
                <li class="last_price"><h4>TOTAL</h4></li>
                <li class="last_price"><span>${summ!=null?(summ+50-summ*0.15):(0)} $</span></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>
            <a class="order" href="#">Place Order</a>
            <div class="total-item">
                <h3>OPTIONS</h3>
                <h4>COUPONS</h4>
                <a class="cpns" href="#">Apply Coupons</a>
                <p><a href="#">Log In</a> to use accounts - linked coupons</p>
            </div>
        </div>
        <%--cart left panel end--%>
        <div class="col-md-9 cart-items">
            <h1>My Shopping Bag (${cartSize})</h1>
            <c:forEach var="productInCart" items="${requestScope.productListCart}">

                <%--base cart start--%>
                <div class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="${pageContext.servletContext.contextPath}/resources/images/w${(productInCart.key).id}.jpg"
                                 class="img-responsive" alt=""/>
                        </div>
                        <div class="cart-item-info">
                            <h3>
                                <a href="${pageContext.servletContext.contextPath}/detail?id=${(productInCart.key).id}">${(productInCart.key).name}</a>
                                <span>Article: ${(productInCart.key).id}</span>
                                <div align="right">
                                    <form action="${pageContext.servletContext.contextPath}/cart" method="post">
                                        <input type="hidden" name="productToDelete" value="${(productInCart.key).id}"/>
                                        <input type="hidden" name="deleteProduct" value="ok"/>


                                        <span class="item_price"><input type="submit" value="delete"/></span>
                                    </form>
                                </div>
                            </h3>
                            <ul class="qty">
                                <li>
                                    <div class="quantity-block">
                                        <button class="quantity-arrow-minus" id="imgMinus"
                                                onclick="minus(${(productInCart.key).id})"> -
                                        </button>
                                        <span class="raz"
                                              id="span${(productInCart.key).id}">${productInCart.value}</span>
                                        <button class="quantity-arrow-plus" id="imgPlus"
                                                onclick="plus(${(productInCart.key).id})"> +
                                        </button>
                                    </div>

                                </li>
                                <li><p>Price: ${(productInCart.key).price} $</p></li>
                                <li><p>Short description: ${(productInCart.key).description}</p></li>
                            </ul>

                            <div class="delivery">
                                <p>Service Charges : Rs.100.00</p>
                                <span>Delivered in 2-3 bussiness days</span>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>

                    </div>
                </div>
                <%--base cart end--%>
            </c:forEach>
        </div>

        <div class="clearfix"></div>
    </div>
</div>
<script>
    var number = 0;
    var qtyCart = document.getElementById("simpleCart_quantity").innerHTML;

    function plus(id) {
        var number = document.getElementById("span" + id).innerHTML;
        if (number < 10) {
            number++;
            document.getElementById("span" + id).innerHTML = number;
            qtyCart++;
            document.getElementById("simpleCart_quantity").innerHTML = qtyCart;
            changeQty(id);
        }
    }

    function minus(id) {
        var number = document.getElementById("span" + id).innerHTML;
        if (number > 1) {
            number--;
            document.getElementById("span" + id).innerHTML = number;
            qtyCart--;
            document.getElementById("simpleCart_quantity").innerHTML = qtyCart;
            changeQty(id);
        }
    }

    function changeQty(id) {
        var num = document.getElementById("span" + id).innerHTML;
        $.ajax
        ({
            data: "productId=" + id + "&qnt=" + num,
            type: 'POST',
            url: "${pageContext.servletContext.contextPath}/cart",
            success: function(mytest){
                alert(mytest);  // you will see the data in alert, you can use it    anywhere
            }
        });
    }
</script>
<%--body start--%>
<%@include file="includes/footer.jsp" %>


