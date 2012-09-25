<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.yin.ycontrol.screenShotter.GuiCamera"%>
<% %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>监控-不支持HTML5的浏览器,费流量(建议改用chrome浏览器)</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>

  </body>
<script>
  var ind=1;
  function flash(){
    var iframe=document.getElementById("ifr1"+ind%2);
    if(iframe){
    	iframe.src = "./ImageServlet";
	}else{
		iframe = document.createElement("iframe");
		iframe.src = "./ImageServlet";
		
		iframe.id="ifr1"+ind%2;
		iframe.style.display="none";
		iframe.width=<%=GuiCamera.getWidth()%>;
		iframe.height=<%=GuiCamera.getHeight()%>;
		iframe.scroll="no";
		iframe.style.zIndex=1;
		document.body.appendChild(iframe);
	}
	
	if (iframe.attachEvent){
	    iframe.attachEvent("onload", function(){
	        var oldIfr=document.getElementById("ifr1"+((ind-2)%2));
	        if(oldIfr){
	        	oldIfr.style.display="none";
	        	iframe.style.zIndex=1;
	    	}
			document.getElementById("ifr1"+((ind-1)%2)).style.display="block";
			iframe.style.zIndex=2;
	        flash();
	    });
	} else {
	    iframe.onload = function(){
	        var oldIfr=document.getElementById("ifr1"+((ind-2)%2));
	        if(oldIfr){
	        	oldIfr.style.display="none";
	        	iframe.style.zIndex=1;
	    	}
			this.style.display="block";
			iframe.style.zIndex=2;
	        flash();
	    };
	}
	ind++;

}
  var div4click = document.createElement("div");//控制层
	div4click.style.position="absolute";
	div4click.style.width = <%=GuiCamera.getWidth()%>;
	div4click.style.height = <%=GuiCamera.getHeight()%>;
	div4click.style.top = 0;
	div4click.style.left = 0;
	div4click.style.filter="alpha(opacity=60)";
	div4click.style.zIndex = 10;
	div4click.scrolling="NO";
	div4click.style.display = "block";
	div4click.id="div4click";
	document.body.appendChild(div4click);
flash();
function mousePosition(ev){ 
  if(ev.pageX || ev.pageY){ 
  return {x:ev.pageX, y:ev.pageY}; 
  } 
  return { 
  x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
  y:ev.clientY + document.body.scrollTop - document.body.clientTop 
  }; 
} 
var request;
function mouseMove(ev){ 
  ev = ev || window.event; 
  var mousePos = mousePosition(ev); 
	
	createRequest();
  	var url = "ControlServlet?x="+mousePos.x+"&y="+mousePos.y;
  	request.open("POST", url, true);
  	request.send(null);
}
document.onmousedown = function(ev){
	ev = ev || window.event;
  	var mousePos = mousePosition(ev); 
  	
	createRequest();
  	var url = "ControlServlet?x="+mousePos.x+"&y="+mousePos.y+"&e=mousedown";
  	request.open("POST", url, true);
  	request.send(null);
};
document.onmouseup = function(ev){
	ev = ev || window.event;
  	var mousePos = mousePosition(ev); 
  	
	createRequest();
  	var url = "ControlServlet?x="+mousePos.x+"&y="+mousePos.y+"&e=mouseup";
  	request.open("POST", url, true);
  	request.send(null);
};
document.oncontextmenu = function(ev){
	ev = ev || window.event;
  	var mousePos = mousePosition(ev); 
  	
	createRequest();
  	var url = "ControlServlet?x="+mousePos.x+"&y="+mousePos.y+"&e=rightclick";
  	request.open("POST", url, true);
  	request.send(null);
  	event.returnValue=false;
    event.cancelBubble=true;
    return false;
};
document.onkeydown = function(ev){

	createRequest();
  	var url = "ControlServlet?k="+ev.keyCode+"&e=keydown";
  	request.open("POST", url, true);
  	request.send(null);
  	event.returnValue=false;
    event.cancelBubble=true;
    return false;
};
document.onkeyup = function(ev){
	createRequest();
  	var url = "ControlServlet?k="+ev.keyCode+"&e=keyup";
  	request.open("POST", url, true);
  	request.send(null);
  	event.returnValue=false;
    event.cancelBubble=true;
    return false;
};

function createRequest() {
  try {
    request = new XMLHttpRequest();
  } catch (trymicrosoft) {
    try {
      request = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (othermicrosoft) {
      try {
        request = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (failed) {
        request = false;
      }
    }
  }
  if (!request)
    alert("Error initializing XMLHttpRequest!");
}
</script>
</html>
