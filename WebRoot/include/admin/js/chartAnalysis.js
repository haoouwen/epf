
  /***柱状图*参数说明
  *columnId:用于展示的DIV
  *title：图形标题
  *subtitle:图形副标题
  *xAxisData：X轴数据
  *yAxisText：Y轴标题
  *paraTip：悬浮提示
  *paraName：
  *seriesData：Y轴数据
  **/
  function columnbasic(columnId,title,subtitle,xAxisData,yAxisText,paraTip,paraName,seriesData){
     var chart; 
           chart = new Highcharts.Chart({
           chart: {
               renderTo: columnId,
               type: 'column'
           },
           title: {
               text: title
           },
           subtitle: {
               text: subtitle
           },
           xAxis: {
               categories: xAxisData
           },
           yAxis: {
               min: 0,
               title: {
                   text: yAxisText
               }
           },
           legend: {
               layout: 'vertical',
               backgroundColor: '#FFFFFF',
               align: 'left',
               verticalAlign: 'top',
               x: 100,
               y: 70,
               floating: true,
               shadow: true
           },
           tooltip: {
               formatter: function() {
                   return ''+
                       this.x +': '+ this.y +paraTip;
               }
           },
           plotOptions: {
               column: {
                   pointPadding: 0.2,
                   borderWidth: 0
               }
           },
           series: [{
               name: paraName,
               data:seriesData
           }]
       });
  }
/***饼状图*参数说明
  *
  *title：图形标题
  *subtitle:图形副标题
  *xAxisData：X轴数据
  *yAxisText：Y轴标题
  *paraTip：悬浮提示
  *paraName：
  *seriesData：Y轴数据
**/
function piebasic(title,subtitle,xAxisData,yAxisText,paraTip,paraName,seriesData){
       var chart;
       chart = new Highcharts.Chart({
       chart: {
           renderTo: 'container_pie',
           plotBackgroundColor: null,
           plotBorderWidth: null,
           plotShadow: false
       },
       title: {
           text: title
       },
       tooltip: {
           formatter: function() {
               return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
           }
       },
       plotOptions: {
           pie: {
               allowPointSelect: true,
               cursor: 'pointer',
               dataLabels: {
                   enabled: true,
                   color: '#000000',
                   connectorColor: '#000000',
                   formatter: function() {
                       return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                   }
               }
           }
       },
       series: [{
           type: 'pie',
           name: 'Browser share',
           data: seriesData
       }]
      });

}

/***折线图*参数说明
  *
  *title：图形标题
  *subtitle:图形副标题
  *xAxisData：X轴数据
  *yAxisText：Y轴标题
  *paraTip：悬浮提示
  *paraName：
  *seriesData：Y轴数据
**/
function linebasic(lineId,title,subtitle,xAxisData,yAxisText,paraTip,paraName,seriesData){

    var chart;
    chart = new Highcharts.Chart({
        chart: {
            renderTo: lineId,
            type: 'line',
            marginRight: 130,
            marginBottom: 25
        },
        title: {
            text: title,
            x: -20 //center
        },
        subtitle: {
            text: subtitle,
            x: -20
        },
        xAxis: {
            categories: xAxisData
        },
        yAxis: {
            title: {
                text: paraTip
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    this.x +': '+ this.y +paraTip;
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        },
        series: [{
            name: paraName,
            data: seriesData
        }]
    });

}

    
