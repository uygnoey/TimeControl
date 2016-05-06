
<%@page import="com.team01.dao.ILoginDAO"%>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/layout.js"></script>

<script type="text/javascript" src="js/jquery.popupWindow.js" ></script>

<script type="text/javascript">

      function loginCheck()
      {
         var email = document.getElementById("email").value;
         var password = document.getElementById("password").value;
         
         //var utime = document.getElementById("timeId");
         //var time = utime.options[utime.selectedIndex].value;
         //alert(time);
         
         if(email=="" && password=="")
            {
               alert("이메일(E-mail), 비밀번호(Password)를 입력해주세요.");
            }
         
         else if(email=="")
            {
               alert("이메일(E-mail)을 입력해주세요.");
            }
         
         else if(password=="")
            {
               alert("비밀번호(Password)를 입력해주세요.");
            }
         else 
            {
/*             
             var style="";      
            var l_winHeight = $(window).height()/3;
            var l_winWidth = $(window).width()/2;
            style="left="+l_winWidth+",top="+l_winHeight+", width=350,height=150,status=no,toolbar=no, menubar=no, location=no, resizable=no";
              window.name="parent";   
              window.open('loginpopup.htm','child',style);
             */
             loginform.submit();
            }
         
      }


   $(document).ready(function() {
      $(".memberInsertPopup").popupWindow({
         
         centerScreen:1,
         
         windowURL : 'memberinsertform.htm',
         windowName : 'memberinsertform.htm'
      });
   });
   
   
   
</script>

</head>

<body class="jui">
<div id="top" class="top" >
      <%-- 사이트 로고 표시 --%>
      
   </div>



<div id="category" class="category">
   
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<div id="login">
<form action="login.htm" method="post" id="loginform">
<table style="width:1200px;">
   <tr>
   <td><img src="img/login2.png" width="60" height="60"/></td>
   <td><input type="email" style="width:250px; height:50px;" id="email" name="email" placeholder="E-mail"/></td>
   <td rowspan="4" style="height:100px"><img src="img\logo.PNG"></td>
   </tr>
   
   <tr></tr>
   
   <tr>
   <td><img src="img/login1.png" width="60" height="60"></td>
   <td><input type="password"   style="width:250px; height:50px;" id="password" name="password" placeholder="Password"
      width="300" height="50"/></td>
   </tr>
   
   <tr>
   <td><h3><b>Time-out&nbsp;</b></h3></td> 
   <td><select id="timeId"  style="width:250px; height:40px;"  name="timeId">
      <option value="1" selected="selected" >없음</option>
      <option value="2">15 minutes</option>
      <option value="3">30 minutes</option>
      <option value="4">1 hour</option>
      <option value="5">2 hours</option>
      </select>
   </td>
   </tr>
</table>
<table style="width:360px">
<tr>
<td style="align:right;width:320px">
<input type="button" id="join"  value="회원가입" class="memberInsertPopup postSubmit btn btn-black-gray btn-flat btn-rect"/>
</td>
<td>
<input type="button" id="loginBtn" value="로그인" onclick="loginCheck()" class="postSubmit btn btn-black-gray btn-flat btn-rect"/>
</td>
</tr>
<tr>
<td >

<a href='find.htm' style="font-size:10px" >이메일 / 비밀번호를 잊으셨나요?</a>
</td>
</tr>
</table>
</form>
</div>
</div>
</body>
</html>