<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div>
	<h1>Chart 1</h1>
	<div class="div_filterBar">
		<span>Group By:</span>
		<div id="menu-div-1" class="menu-div">
			<img class="menu-img" src="./res/Hamburger-30.png">
		</div>
		<div id="div_filters_1" class="div_filters">
			<ul class="ul_filter">
				<li onclick="getChart_1(1,'Job Title')"><a>Job Title</a></li>
				<li onclick="getChart_1(2,'Hire Year')"><a>Hire Year</a></li>
				<li onclick="getChart_1(3,'City')"><a>City</a></li>
			</ul>
		</div>
	</div>
	<div class="div_chart" id="chart_1"></div>
	<script>
		$("#menu-div-1").click(function(){
			$("#div_filters_1").toggle();
		});
		$("#div_filters_1").click(function(){
			$(this).hide();
		});
	</script>
</div>
