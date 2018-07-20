<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>理财列表</title>



<style>

body {
  background: #fff;
  color: #333;
  font-family: "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
  font-size: 0.9em;
  padding: 40px;
}
.wideBox {
  clear: both;
  text-align: center;
  margin-bottom: 50px;
  padding: 10px;
  background: #ebedf2;
  border: 1px solid #333;
  line-height: 80%;
}

#container {
  width: 900px;
  margin: 0 auto;
}

#chart, #chartData {
  border: 1px solid #333;
  background: #ebedf2 url("images/gradient.png") repeat-x 0 0;
}

#chart {
  display: block;
  margin: 0 0 50px 0;
  float: left;
  cursor: pointer;
}

#chartData {
  width: 200px;
  margin: 0 40px 0 0;
  float: right;
  border-collapse: collapse;
  box-shadow: 0 0 1em rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 0 1em rgba(0, 0, 0, 0.5);
  -webkit-box-shadow: 0 0 1em rgba(0, 0, 0, 0.5);
  background-position: 0 -100px;
}

#chartData th, #chartData td {
  padding: 0.5em;
  border: 1px dotted #666;
  text-align: left;
}

#chartData th {
  border-bottom: 2px solid #333;
  text-transform: uppercase;
}

#chartData td {
  cursor: pointer;
}

#chartData td.highlight {
  background: #e8e8e8;
}

#chartData tr:hover td {
  background: #f0f0f0;
}

</style>
<script type="text/javascript" src="../../resources/custom/jquery.min.js"></script>
<script src="../../resources/js/graph.js"></script>
<script>

// Run the code when the DOM is ready
$( pieChart );

function pieChart() {

  // Config settings
  var chartSizePercent = 55;                        
  var sliceBorderWidth = 1;                         
  var sliceBorderStyle = "#fff";                    
  var sliceGradientColour = "#ddd";                 
  var maxPullOutDistance = 25;                      
  var pullOutFrameStep = 4;                         
  var pullOutFrameInterval = 40;                    
  var pullOutLabelPadding = 65;                       
  var pullOutLabelFont = "bold 16px 'Trebuchet MS', Verdana, sans-serif";  
  var pullOutValueFont = "bold 12px 'Trebuchet MS', Verdana, sans-serif"; 
  var pullOutValuePrefix = "￥";                     
  var pullOutShadowColour = "rgba( 0, 0, 0, .5 )";  
  var pullOutShadowOffsetX = 5;                     
  var pullOutShadowOffsetY = 5;                     
  var pullOutShadowBlur = 5;                        
  var pullOutBorderWidth = 2;                       
  var pullOutBorderStyle = "#333";                 
  var chartStartAngle = -.5 * Math.PI;              

  // Declare some variables for the chart
  var canvas;                       
  var currentPullOutSlice = -1;     
  var currentPullOutDistance = 0;   
  var animationId = 0;              
  var chartData = [];              
  var chartColours = [];            
  var totalValue = 0;               
  var canvasWidth;                  
  var centreX;                      
  var centreY;                      
  var chartRadius;                  

  // Set things up and draw the chart
  init();


  /**
   * Set up the chart data and colours, as well as the chart and table click handlers,
   * and draw the initial pie chart
   */

  function init() {

    // Get the canvas element in the page
    canvas = document.getElementById('chart');

    // Exit if the browser isn't canvas-capable
    if ( typeof canvas.getContext === 'undefined' ) return;

    // Initialise some properties of the canvas and chart
    canvasWidth = canvas.width;
    canvasHeight = canvas.height;
    centreX = canvasWidth / 2;
    centreY = canvasHeight / 2;
    chartRadius = Math.min( canvasWidth, canvasHeight ) / 2 * ( chartSizePercent / 100 );

    // Grab the data from the table,
    // and assign click handlers to the table data cells
    
    var currentRow = -1;
    var currentCell = 0;

    $('#chartData td').each( function() {
      currentCell++;
      if ( currentCell % 2 != 0 ) {
        currentRow++;
        chartData[currentRow] = [];
        chartData[currentRow]['label'] = $(this).text();
      } else {
       var value = parseFloat($(this).text());
       totalValue += value;
       value = value.toFixed(2);
       chartData[currentRow]['value'] = value;
      }

      // Store the slice index in this cell, and attach a click handler to it
      $(this).data( 'slice', currentRow );
      $(this).click( handleTableClick );

      // Extract and store the cell colour
      if ( rgb = $(this).css('color').match( /rgb\((\d+), (\d+), (\d+)/) ) {
        chartColours[currentRow] = [ rgb[1], rgb[2], rgb[3] ];
      } else if ( hex = $(this).css('color').match(/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/) ) {
        chartColours[currentRow] = [ parseInt(hex[1],16) ,parseInt(hex[2],16), parseInt(hex[3], 16) ];
      } else {
        alert( "Error: Colour could not be determined! Please specify table colours using the format '#xxxxxx'" );
        return;
      }

    } );

    // Now compute and store the start and end angles of each slice in the chart data

    var currentPos = 0; // The current position of the slice in the pie (from 0 to 1)

    for ( var slice in chartData ) {
      chartData[slice]['startAngle'] = 2 * Math.PI * currentPos;
      chartData[slice]['endAngle'] = 2 * Math.PI * ( currentPos + ( chartData[slice]['value'] / totalValue ) );
      currentPos += chartData[slice]['value'] / totalValue;
    }

    // All ready! Now draw the pie chart, and add the click handler to it
    drawChart();
    $('#chart').click ( handleChartClick );
  }


  /**
   * Process mouse clicks in the chart area.
   *
   * If a slice was clicked, toggle it in or out.
   * If the user clicked outside the pie, push any slices back in.
   *
   * @param Event The click event
   */

  function handleChartClick ( clickEvent ) {
    // Get the mouse cursor position at the time of the click, relative to the canvas
    var mouseX = clickEvent.pageX - this.offsetLeft;
    var mouseY = clickEvent.pageY - this.offsetTop;

    // Was the click inside the pie chart?
    var xFromCentre = mouseX - centreX;
    var yFromCentre = mouseY - centreY;
    var distanceFromCentre = Math.sqrt( Math.pow( Math.abs( xFromCentre ), 2 ) + Math.pow( Math.abs( yFromCentre ), 2 ) );

    if ( distanceFromCentre <= chartRadius ) {

      // Yes, the click was inside the chart.
      // Find the slice that was clicked by comparing angles relative to the chart centre.

      var clickAngle = Math.atan2( yFromCentre, xFromCentre ) - chartStartAngle;
      if ( clickAngle < 0 ) clickAngle = 2 * Math.PI + clickAngle;
                  
      for ( var slice in chartData ) {
        if ( clickAngle >= chartData[slice]['startAngle'] && clickAngle <= chartData[slice]['endAngle'] ) {

          // Slice found. Pull it out or push it in, as required.
          toggleSlice ( slice );
          return;
        }
      }
    }

    // User must have clicked outside the pie. Push any pulled-out slice back in.
    pushIn();
  }


  /**
   * Process mouse clicks in the table area.
   *
   * Retrieve the slice number from the jQuery data stored in the
   * clicked table cell, then toggle the slice
   *
   * @param Event The click event
   */

  function handleTableClick ( clickEvent ) {
	  
    var slice = $(this).data('slice');
    toggleSlice ( slice );
  }


  /**
   * Push a slice in or out.
   *
   * If it's already pulled out, push it in. Otherwise, pull it out.
   *
   * @param Number The slice index (between 0 and the number of slices - 1)
   */

  function toggleSlice ( slice ) {
    if ( slice == currentPullOutSlice ) {
      pushIn();
    } else {
      startPullOut ( slice );
    }
  }

 
  function startPullOut ( slice ) {

    // Exit if we're already pulling out this slice
    if ( currentPullOutSlice == slice ) return;

    // Record the slice that we're pulling out, clear any previous animation, then start the animation
    currentPullOutSlice = slice;
    currentPullOutDistance = 0;
    clearInterval( animationId );
    animationId = setInterval( function() { animatePullOut( slice ); }, pullOutFrameInterval );

    // Highlight the corresponding row in the key table
    $('#chartData td').removeClass('highlight');
    var labelCell = $('#chartData td:eq(' + (slice*2) + ')');
    var valueCell = $('#chartData td:eq(' + (slice*2+1) + ')');
    labelCell.addClass('highlight');
    valueCell.addClass('highlight');
  }

 
  /**
   * Draw a frame of the pull-out animation.
   *
   * @param Number The index of the slice being pulled out
   */

  function animatePullOut ( slice ) {

    // Pull the slice out some more
    currentPullOutDistance += pullOutFrameStep;

    // If we've pulled it right out, stop animating
    if ( currentPullOutDistance >= maxPullOutDistance ) {
      clearInterval( animationId );
      return;
    }

    // Draw the frame
    drawChart();
  }

 
  /**
   * Push any pulled-out slice back in.
   *
   * Resets the animation variables and redraws the chart.
   * Also un-highlights all rows in the table.
   */

  function pushIn() {
    currentPullOutSlice = -1;
    currentPullOutDistance = 0;
    clearInterval( animationId );
    drawChart();
    $('#chartData td').removeClass('highlight');
  }
 
 

  function drawChart() {

    // Get a drawing context
    var context = canvas.getContext('2d');
        
    // Clear the canvas, ready for the new frame
    context.clearRect ( 0, 0, canvasWidth, canvasHeight );

    // Draw each slice of the chart, skipping the pull-out slice (if any)
    for ( var slice in chartData ) {
      if ( slice != currentPullOutSlice ) drawSlice( context, slice );
    }

    // If there's a pull-out slice in effect, draw it.
    // (We draw the pull-out slice last so its drop shadow doesn't get painted over.)
    if ( currentPullOutSlice != -1 ) drawSlice( context, currentPullOutSlice );
  }



  function drawSlice ( context, slice ) {

    // Compute the adjusted start and end angles for the slice
    var startAngle = chartData[slice]['startAngle']  + chartStartAngle;
    var endAngle = chartData[slice]['endAngle']  + chartStartAngle;
      
    if ( slice == currentPullOutSlice ) {

      // We're pulling (or have pulled) this slice out.
      // Offset it from the pie centre, draw the text label,
      // and add a drop shadow.

      var midAngle = (startAngle + endAngle) / 2;
      var actualPullOutDistance = currentPullOutDistance * easeOut( currentPullOutDistance/maxPullOutDistance, .8 );
      startX = centreX + Math.cos(midAngle) * actualPullOutDistance;
      startY = centreY + Math.sin(midAngle) * actualPullOutDistance;
      context.fillStyle = 'rgb(' + chartColours[slice].join(',') + ')';
      context.textAlign = "center";
      context.font = pullOutLabelFont;
      context.fillText( chartData[slice]['label'], centreX + Math.cos(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ), centreY + Math.sin(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ) );
      context.font = pullOutValueFont;
      context.fillText( pullOutValuePrefix + chartData[slice]['value'] + " (" + ( parseInt( chartData[slice]['value'] / totalValue * 100 + .5 ) ) +  "%)", centreX + Math.cos(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ), centreY + Math.sin(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ) + 20 );
      context.shadowOffsetX = pullOutShadowOffsetX;
      context.shadowOffsetY = pullOutShadowOffsetY;
      context.shadowBlur = pullOutShadowBlur;

    } else {

      // This slice isn't pulled out, so draw it from the pie centre
      startX = centreX;
      startY = centreY;
    }

    // Set up the gradient fill for the slice
    var sliceGradient = context.createLinearGradient( 0, 0, canvasWidth*.75, canvasHeight*.75 );
    sliceGradient.addColorStop( 0, sliceGradientColour );
    sliceGradient.addColorStop( 1, 'rgb(' + chartColours[slice].join(',') + ')' );

    // Draw the slice
    context.beginPath();
    context.moveTo( startX, startY );
    context.arc( startX, startY, chartRadius, startAngle, endAngle, false );
    context.lineTo( startX, startY );
    context.closePath();
    context.fillStyle = sliceGradient;
    context.shadowColor = ( slice == currentPullOutSlice ) ? pullOutShadowColour : "rgba( 0, 0, 0, 0 )";
    context.fill();
    context.shadowColor = "rgba( 0, 0, 0, 0 )";

    // Style the slice border appropriately
    if ( slice == currentPullOutSlice ) {
      context.lineWidth = pullOutBorderWidth;
      context.strokeStyle = pullOutBorderStyle;
    } else {
      context.lineWidth = sliceBorderWidth;
      context.strokeStyle = sliceBorderStyle;
    }

    // Draw the slice border
    context.stroke();
  }

  function easeOut( ratio, power ) {
    return ( Math.pow ( 1 - ratio, power ) + 1 );
  }
  
  

};

</script>

		<style type="text/css">
			.but{
				height: 40px;
				width: 130px;
				font-size: 16px;
				background-color:#FFFFFF; 
				float: left;
				line-height: 40px;
				cursor: pointer;
				border: 1px solid #cacaca;
			}
			
			.active{
				background-color: #ff5584;
				font-weight: bolder;
				color: #FFFFFF;
			}
			
			.kv-content{
				border: 1px solid #cacaca;
			}
			
			.container{
				height: 80px;
			}
			
			a{
				color: #1da02b;
    			text-decoration: none;
			}
		</style>
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/bar.css">		
</head>
<body>

<div id="container">
  		<div  style="height: 80px;position:relative;">
			<div align="center" id="user_options">
			<div style="float: left;font-size: 18px;height: 40px;width: 200px;line-height: 40px;cursor: pointer;"></div>
				<div class="but active" >月度收益报表</div>
				<div class="but" >年度收益报表</div>
				<div class="but" >我的财务报表</div>
				<div style="clear: both;"></div>
			</div>
		</div>
      
      <div id="monthReport"  style="display:none;">
         <canvas id="chart" width="640" height="500" style="background:#fff;"></canvas>
		  <table id="chartData" >
		     <tr>
		      <th>理财平台</th><th>总收益（￥）</th>
		     </tr> 
		
		    <tr style="color: #0DA068;">
		      <td>陆金所</td><td>1862.12</td>
		    </tr>
		
		    <tr style="color: #194E9C">
		      <td>恒信易贷</td><td>1316.00</td>
		    </tr>
		
		    <tr style="color: #ED9C13">
		      <td>翼龙贷</td><td>712.49</td>
		    </tr>
		
		    <tr style="color: #FF0000">
		      <td>拍拍贷</td><td>3236.27</td>
		    </tr>
		
		    <tr style="color: #057249">
		      <td>你我贷</td><td>6122.06</td>
		    </tr>
		
		    <tr style="color: #5F91DC">
		      <td>点融网</td><td>128.11</td>
		    </tr>
		
		    <tr style="color: #F88E5D">
		      <td>久富</td><td>245.55</td>
		    </tr>
		    <tr style="color: #A88E5D">
		      <td>懒财网</td><td>2245.55</td>
		    </tr>
		  </table>
      </div>
	  
	  <div id="licaiReport" >
	      <div id="wrapper" style="position:relative;left:200px;margin-top:-5px;">
		        <div class="chart">
		            <h3>收益（元）</h3>
		            <table id="data-table" border="1" cellpadding="10" cellspacing="0" >
		                <thead>
		                    <tr>
		                        <td>&nbsp;</td>
		                        <th scope="col"></th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr>
		                        <th scope="row">1月</th>
		                        <td>2600</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">2月</th>
		                        <td>2450</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">3月</th>
		                        <td>1600</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">4月</th>
		                        <td>2000</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">5月</th>
		                        <td>2600</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">6月</th>
		                        <td>3600</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">7月</th>
		                        <td>4600</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">8月</th>
		                        <td>2780</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">9月</th>
		                        <td>2300</td>
		                    </tr>
		                    <tr>
		                        <th scope="row">10月</th>
		                        <td>3000</td>
		                    </tr>
		                    <tr>
		                        <th scope="row"></th>
		                        <td>2400</td>
		                    </tr>
		                     <tr>
		                        <th scope="row"></th>
		                        <td>5000</td>
		                    </tr> 
		                </tbody>
		            </table>
		        </div>
		    </div>
	     
	  </div>



  
</div>

<script type="text/javascript">

  $(function(){
	  $(".but").click(function(){
		  
		  $(this).addClass("active").siblings().removeClass("active");
	  });
  });
  
</script>
</body>
</html>
