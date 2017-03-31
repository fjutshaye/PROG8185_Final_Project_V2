<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div>
	<h1>Chart 1</h1>
	<div class="div_filterBar">
		<span>Group By:</span>
		<ul class="ul_filter">
			<li onclick="getChart_1(1,'Job Title')"><a>Job Title</a></li>
			<li onclick="getChart_1(2,'Hire Year')"><a>Hire Year</a></li>
			<li onclick="getChart_1(3,'City')"><a>City</a></li>
		</ul>
	</div>
	<div class="div_chart" id="chart_1"></div>
</div>
