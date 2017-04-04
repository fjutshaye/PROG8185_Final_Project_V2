/**
 * 
 */
var url = "MyServlet";
var data_chart_1;
var data_chart_2;
var data_chart_3;
var data_chart_4;
var yearList;

function initSelect(){
	$.get(url + "?requestId=requestYearList", function(data, status){
		//alert(data + status);
		yearList = data;
		for(i=0; i<yearList.length; i++){
			$("#select_year").append($('<option>', {
				value: yearList[i],
				text: yearList[i].toString()
			}));
		}
	});
}

function getChart_1(filterNum, subTitle){
	$.get(url + "?chartNum=" + 1 + "&filterNum=" + filterNum, function(data, status){
		//alert(data + status);
		data_chart_1 = data.valueOf().myArrayList;
		draw_chart_1(data_chart_1, subTitle);
	});
}
function draw_chart_1(myData, subTitle){
	Highcharts.chart('chart_1', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'The Numbers of Employees'
	    },
	    subtitle: {
	        text: 'Group by: ' + subTitle
	    },
	    xAxis: {
	        type: 'category',
	        labels: {
	            rotation: -45,
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Numbers'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: 'Number of Employee(s): <b>{point.y:.0f}</b>'
	    },
	    series: [{
	        name: 'Population',
	        data: myData,
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y:.0f}', // one decimal
	            y: 10, // 10 pixels down from the top
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});
}
function getChart_2(filterNum, subTitile){
	$.get(url + "?chartNum=" + 2 + "&filterNum=" + filterNum, function(data, status){
		//alert(data + status);
		data_chart_2 = data.valueOf().myArrayList;
		draw_chart_2(data_chart_2, subTitile);
	});
}
function draw_chart_2(myData, subTitle){

	Highcharts.chart('chart_2', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Tracks Statistics'
	    },
	    subtitle: {
	        text: subTitle
	    },
	    xAxis: {
	        type: 'category',
	        labels: {
	            rotation: -45,
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Count'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: 'Number of Tracks: <b>{point.y:.0f}</b>'
	    },
	    series: [{
	        name: 'Population',
	        data: myData,
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y:.0f}', // one decimal
	            y: 10, // 10 pixels down from the top
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});
}
function getChart_3(year){
	$.get(url + "?chartNum=" + 3 + "&filterNum=" + 1 + "&year_c3=" + year, function(data, status){
		//alert(data + status);
		data_chart_3 = data;
		draw_chart_3(data_chart_3, year);
	});
}
function draw_chart_3(myData, subTitle){

	Highcharts.chart('chart_3', {
        title: {
            text: 'Sale Amount'
        },
        subtitle: {
        	text: subTitle
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Total Sale ($)'
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },
        series: [{
            name: 'Sale Amount',
            data: myData
        }]
    });
}
function getChart_4(filterNum, yText){
	$.get(url + "?chartNum=" + 4 + "&filterNum=" + filterNum, function(data, status){
		//alert(data + status);
		data_chart_4 = data;
		draw_chart_4(data_chart_4, data_chart_4[2], yText);
	});
}
function draw_chart_4(mydata, subTitle, yText){
	Highcharts.chart('chart_4',{
		title:{
			text: "Sale Stats"
		},
		subtitle:{
			text: subTitle
		},
		xAxis:{
			categories: mydata[0]
		},
		yAxis:{
			title: {
	            text: yText
	        }
		},
		legend:{
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle'
		},
		series: [{
			name: mydata[2],
			data: mydata[1]
		}]
	})
}