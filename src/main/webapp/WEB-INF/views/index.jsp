<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Salvage, Rebuildable and Clean Title Vehicles for Sale</title>
    <meta name="description" content="Find a huge selection of Salvage, Rebuildable and Clean Title Vehicles for Sale."/>
    <meta name="keywords" content="Salvage, Rebuildable, Clean Title, Vehicles for Sale"/>
    <meta name="yandex-verification" content="d28f4c35216230f9" />
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
    <link href="<c:url value="/site/main.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/site/jquery.bxslider.css"/>" rel="stylesheet"/>
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-87991067-1', 'auto');
        ga('send', 'pageview');

    </script>
</head>
<body>
<%@include file="component/navigationbar.jsf"%>
<%@include file="component/searchCarFinder.jsf"%>
<div class="row-fluid">
    <div class="about-box">
    <div class="header">
        <h2>ABOUT USAUTOSEARCH INFO CAR AUCTION</h2>
    </div>
    <div class="body">
        <p>
            There are thousands of damaged cars for sale. Salvage title cars are either affected by nature or have met collision. Bidders can place a bid and win vehicles at low prices. Every week, online car auction sites put up thousands of salvage title cars for sale. Interested buyers can bid from any corner of the world. The types of car brands include BMW, Audi, Volkswagen, Honda, Hyundai to choose from. Buyers can collect details about cars using the VIN, or the Vehicle Identification Number. On the auction page there are vehicle details including damage type, loss type, and engine size to list a few. Bidders can select a vehicle of their choice, refer to the details, check out the images taken from different angles, before placing a bid.
        </p>
    </div>
    </div>
</div>
<%@include file="component/type-box.jsf"%>
</body>
</html>
