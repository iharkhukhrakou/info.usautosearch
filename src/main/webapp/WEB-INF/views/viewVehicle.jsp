<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Salvage Vehicle Title ${vehicle.year} ${vehicle.make} ${vehicle.modelDetail} For Sale</title>
    <meta name="description" content="${vehicle.year} ${vehicle.make} ${vehicle.modelGroup} for Sale, History report, VIN report, Damage: ${vehicle.damageDescription}, Doc type: ${vehicle.saleTitleState} - ${vehicle.saleTitleType} - Salvage Vehicle Title, Vin ${vehicle.vin}, Lot: ${vehicle.lotNumber}"/>
    <meta name="keywords" content="${vehicle.make} ${vehicle.modelGroup}, vin check, vin report,history report, Salvage Vehicle Title"/>
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap.js"/>"></script>
    <link href="<spring:url value="/site/main.css"/>" rel="stylesheet"/>
    <script src="<spring:url value="/gallery/js/unitegallery.min.js"/>"></script>
    <script src="<spring:url value="/gallery/themes/default/ug-theme-default.js"/>"></script>
    <link href="<spring:url value="/gallery/css/unite-gallery.css"/>" rel="stylesheet"/>
    <link href="<spring:url value="/gallery/themes/default/ug-theme-default.css"/>" rel="stylesheet"/>
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
<div class="col-md-12 lot-content">
    <div class="col-md-4">
        <%@include file="component/gallery.jsf"%>
    </div>
    <div class="col-md-4">
        <div class="lot-details-box">
            <div class="lot-details-header">
                Vehicle Details
            </div>
            <div class="lot-details-content">
                <div class="lot-details-entry">
                    <label>Lot:</label>
                    <span>${vehicle.lotNumber}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Make:</label>
                    <span>${vehicle.make}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Model:</label>
                    <span>${vehicle.modelDetail}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Doc Type:</label>
                    <span>${vehicle.saleTitleState} - ${vehicle.saleTitleType}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Odometer:</label>
                    <span>${vehicle.odometer}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Primary Damage:</label>
                    <span>${vehicle.damageDescription}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Secondary Damage:</label>
                    <span>${vehicle.secondaryDamage}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Est. Retail Value:</label>
                    <span>${vehicle.retailValue}</span>
                </div>
                <div class="lot-details-entry">
                    <label>VIN:</label>
                    <span>${vehicle.vin}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Body Style:</label>
                    <span>${vehicle.bodyStyle}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Color:</label>
                    <span>${vehicle.color}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Engine Type:</label>
                    <span>${vehicle.engine}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Drive:</label>
                    <span>${vehicle.drive}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Cylinders:</label>
                    <span>${vehicle.cylinders}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Fuel:</label>
                    <span>${vehicle.fuelType}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Keys:</label>
                    <span>${vehicle.hasKeys}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Notes:</label>
                    <span>${vehicle.specialNote}</span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="sale-info-box">
            <div class="sale-info-header">
                Sale Information
            </div>
            <div class="sale-info-content">
                <div class="lot-details-entry">
                    <label>Auction Location:</label>
                    <span>${vehicle.yardName}</span>
                </div>
                <div class="lot-details-entry">
                    <label>Sale Date:</label>
                    <span><fmt:formatDate value="${vehicle.saleDate}" pattern="yyyy-MM-dd" /></span>
                </div>
                <div class="sale-info-entry">
                    <a class="btn btn-primary btn-block" href="<c:url value='/to-redirected?lot=${vehicle.lotNumber}-${vehicle.year}-${vehicle.make}-${vehicle.modelGroup}' />" rel="nofollow" target="_blank">Bid Now</a>
                </div>
                <div class="sale-info-entry">
                    <a class="btn btn-success btn-block" href="<c:url value='/to-redirected?vin=${vehicle.vin}' />" rel="nofollow" target="_blank">View VIN report</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
