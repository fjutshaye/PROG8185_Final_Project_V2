<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div>
	<h1>Chart 4</h1>
	<div class="div_filterBar">
		<span>Select Filter: </span>
		<div id="menu-div-4" class="menu-div">
			<img class="menu-img" src="./res/Hamburger-30.png">
		</div>
		<div id="div_filters_4" class="div_filters">
		<ul class="ul_filter">
			<li onclick="getChart_4(1, 'Value($)')"><a>Amount of Sales</a></li>
			<li onclick="getChart_4(2, 'Count')"><a>Number of Sales</a></li>
			<li onclick="getChart_4(3, 'Value($)')"><a>Average per Purchase</a></li>
		</ul>
	</div>
	<div class="div_chart" id="chart_4"></div>
	<script>
		$("#menu-div-4").click(function(){
			$("#div_filters_4").toggle();
		});
		$("#div_filters_4").click(function(){
			if($("#menu-div-4").css('display') == 'inline-block')
				$(this).hide();
		});
	</script>
</div>