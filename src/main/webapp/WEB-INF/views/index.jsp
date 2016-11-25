<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Salvage, Rebuildable and Clean Title Vehicles for Sale</title>
    <meta name="description" content="Find a huge selection of Salvage, Rebuildable and Clean Title Vehicles for Sale."/>
    <meta name="keywords" content="Salvage, Rebuildable, Clean Title, Vehicles for Sale"/>
    <spring:url value="resources/css/bootstrap.min.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <spring:url value="resources/js/bootstrap.js" var="bootstrapJs"/>
    <script src="${bootstrapJs}"></script>
    <spring:url value="/site/main.css" var="siteCss" />
    <link href="${siteCss}" rel="stylesheet" />
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
<%@include file="component/salesdata.jsf"%>
</body>
</html>
