<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div>
	<h1>Chart 3</h1>
	<div class="div_filterBar">
		<span style="display:inline-block">Select Year</span>
		<select name="year" id="select_year"></select>
		<script>
			initSelect();
			$("#select_year").change(function(){
				var year = $("#select_year").val();
				getChart_3(year);
			});
		</script>
	</div>
	<div class="div_chart" id="chart_3"></div>
</div>