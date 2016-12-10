<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Salvage, Rebuildable and Clean Title Vehicles for Sale</title>
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap.js"/>"></script>
    <link href="<spring:url value="/site/main.css"/>" rel="stylesheet"/>
    <script>
        $(document).ready(function() {
            $('#searchType').change(function() {
                $("#searchMake").val('All Makes');
                $("#searchModel").val('All Models');
                $('#searchSubmit').click();
            });
            $('#searchMake').change(function() {
                $("#searchModel").val('All Models');
                $('#searchSubmit').click();
            });
            $('#searchModel').change(function() {
                $('#searchSubmit').click();
            });
            $('#searchYearFrom').change(function() {
                $('#searchSubmit').click();
            });
            $('#searchYearTo').change(function() {
                $('#searchSubmit').click();
            });
        });
    </script>
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
<div class="col-md-12">
    <div id="carFinderLeft" class="col-md-2">
        <%@include file="component/searchCarFinderLeft.jsf"%>
    </div>
    <div class="col-md-10">
        <%@include file="component/salesdata.jsf"%>
    </div>
</div>
</body>
</html>
