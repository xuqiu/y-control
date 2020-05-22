<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.yin.ycontrol.screenShotter.GuiCamera" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">
  <title>监控-支持HTML5的浏览器</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="./js/jquery-1.4.4.min.js"></script>
</head>
<body>
<canvas id="myCanvas" width="<%=GuiCamera.getWidth()%>" height="<%=GuiCamera.getHeight()%>"></canvas>
</body>
<script>
    //检查浏览器是否支持HTML5,不支持的话跳转到旧页面,比较费流量
    var myCanvas = document.getElementById("myCanvas");
    if (!myCanvas.getContext) {
        alert("你使用的浏览器比较老,不支持画布功能. 使用本工具远程控制时比较耗费流量,对网络环境要求高,画面不流畅. 建议更换支持HTML5的浏览器,推荐chrome!");
        window.location.href = "./old.jsp";
    }


    var c = document.getElementById("myCanvas");
    var cxt = c.getContext("2d");
    var now = new Date();
    var exitTime = new Date();
    var frame = 0;//帧数
    var lastframe = 0;//最新帧数
    function flash() {
        //获取下一帧数
        getFrame();
        var img = new Image();
        img.src = "./ImageServlet?min=" + frame + "&max=" + lastframe;
        frame = lastframe;
        img.onload = function () {
            cxt.drawImage(img, 0, 0);
            delete img;
            window.setTimeout(flash, 200);
        };
    }

    flash();

    function getFrame() {
        $.ajax({
            async: false,
            type: "post",
            url: "./ImageServlet?lastframe=1",
            cache: false,
            success: function (msg) {
                lastframe = msg;
            }
        });
    }

    //以下是控制部分的代码
    function mousePosition(ev) {
        if (ev.pageX || ev.pageY) {
            return {x: ev.pageX, y: ev.pageY};
        }
        return {
            x: ev.clientX + document.body.scrollLeft - document.body.clientLeft,
            y: ev.clientY + document.body.scrollTop - document.body.clientTop
        };
    }

    var request;

    function mouseMove(ev) {
        ev = ev || window.event;
        var mousePos = mousePosition(ev);

        createRequest();
        var url = "ControlServlet?x=" + mousePos.x + "&y=" + mousePos.y;
        request.open("POST", url, true);
        request.send(null);
    }

    document.onmousedown = function (ev) {
        ev = ev || window.event;
        var mousePos = mousePosition(ev);

        createRequest();
        var url = "ControlServlet?x=" + mousePos.x + "&y=" + mousePos.y + "&e=mousedown";
        request.open("POST", url, true);
        request.send(null);
    };
    document.onmouseup = function (ev) {
        ev = ev || window.event;
        var mousePos = mousePosition(ev);

        createRequest();
        var url = "ControlServlet?x=" + mousePos.x + "&y=" + mousePos.y + "&e=mouseup";
        request.open("POST", url, true);
        request.send(null);
    };
    document.oncontextmenu = function (ev) {
        ev = ev || window.event;
        var mousePos = mousePosition(ev);

        createRequest();
        var url = "ControlServlet?x=" + mousePos.x + "&y=" + mousePos.y + "&e=rightclick";
        request.open("POST", url, true);
        request.send(null);
        event.returnValue = false;
        event.cancelBubble = true;
        return false;
    };
    document.onkeydown = function (ev) {

        createRequest();
        var url = "ControlServlet?k=" + ev.keyCode + "&e=keydown";
        request.open("POST", url, true);
        request.send(null);
        event.returnValue = false;
        event.cancelBubble = true;
        return false;
    };
    document.onkeyup = function (ev) {
        createRequest();
        var url = "ControlServlet?k=" + ev.keyCode + "&e=keyup";
        request.open("POST", url, true);
        request.send(null);
        event.returnValue = false;
        event.cancelBubble = true;
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
