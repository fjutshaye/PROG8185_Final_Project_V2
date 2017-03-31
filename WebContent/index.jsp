<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/style_charts.css" />
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="./js/getCharts.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tao's Final Project</title>
</head>
<body>
<%-- 	<jsp:include page="./data_loader/data_loader.jsp" />

	<!-- chart 1 - Products Information -->
	<div class="div_chart" id="chart_products"></div>
	
	<!-- chart 2 - Profit Group by Product -->
	<div class="div_chart" id="char_profitbyproduct"></div>
		
	<!-- draw charts -->
	<script src="./js/draw_charts.js"></script> --%>
	
	<jsp:include page="./chart1/chart1.jsp" />
	<jsp:include page="./chart2/chart2.jsp" />
	<jsp:include page="./chart3/chart3.jsp" />
</body>
</html>