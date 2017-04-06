<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div>
	<h1>Chart 2</h1>
	<div class="div_filterBar">
		<span>Group By:</span>
		<div id="menu-div-2" class="menu-div">
			<img class="menu-img" src="./res/Hamburger-30.png">
		</div>
		<div id="div_filters_2" class="div_filters">
		<ul class="ul_filter">
			<li onclick="getChart_2(1,'Tracks Group By Price')"><a>Track Price</a></li>
			<li onclick="getChart_2(2,'Tracks Group By Genre (Top 5)')"><a>Genre</a></li>
			<li onclick="getChart_2(3,'Tracks Group By Artist (Top 5)')"><a>Artist</a></li>
		</ul>
	</div>
	<div class="div_chart" id="chart_2"></div>
	<script>
		$("#menu-div-2").click(function(){
			$("#div_filters_2").toggle();
		});
		$("#div_filters_2").click(function(){
			$(this).hide();
		});
	</script>
</div>