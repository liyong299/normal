<%@taglib prefix="e" uri="org.topteam/easyui" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<e:templateOverride name="title">登录</e:templateOverride>
<e:templateOverride name="head">
	<style>
		body{margin: 0; padding: 0; color:#a3a3a3; font-family:"微软雅黑",Arial, Helvetica, sans-serif; font-size:14px; }
		.index_login{height: 570px;;width:100%;min-width:1100px;background: url(../resources/images/index_bglogin.jpg)  no-repeat left top;}
		.index_1px{height: 570px;;width:100%; background: url(../resources/images/index_1px.gif) repeat-x;}
		.index_c{height: 451px;width:1100px; margin:0 auto; background: url(../resources/images/index_center.png)  no-repeat left top;}
		.index_login_div{ float:right; margin-top:100px; margin-right:0px;width:323px; height:245px; background-image: url(../resources/images/login.png); background-repeat:no-repeat; padding-top:100px; padding-left:75px;}
		.index_foot{height:30px; margin:0 auto; width:100%; text-align:center;margin-top:20px;}
		.index_logo{height:60px; margin:0 auto; width:1000px;}
		.index_login_center{ position:absolute;top:60px;width:425px; height:451px; background-image: url(img/index_center.png); background-repeat:no-repeat; margin-left:300px;}
		.index_name{width:248px; height:37px; float:left; margin:0 auto;}
		.index_pass{width:248px; height:37px; float:left; margin:0 auto; margin-top:15px; margin-bottom:15px;}
		.input_t{width:235px; height:33px; border:1px solid #a3a3a3;padding-left:10px;line-height:33px}
		.button {width: 248px;  height: 35px;  display: block;  background: #51a8c2;  line-height: 35px;  color: #fff;  text-align: center;  border: none;  cursor: pointer;  float:left;}
	</style>
</e:templateOverride>
<e:templateOverride name="body">
	<div class="index_logo"><img src="../resources/images/index_login.jpg" width="311" height="59" border="0" alt="深圳市兜有电子商务科技有限公司"></div>
    <div class="index_1px">
     <div class="index_login">
       <div class="index_c">
            <div class="index_login_div">
                <form action="" method="post" name="form1" >          
                    <div class="index_name"><input type="text" class="input_t" placeholder="用户名"  id="name"></div>
                    <div class="index_pass"><input type="password" class="input_t" placeholder="密码" id="password"></div>
                    <div><a id="btn-save" class="button">登      录</a></div>
                </form>
            </div>
      </div>
     </div>
     </div>
     <div class="index_foot">© 2014  深圳市兜有电子商务科技有限公司</div>
</e:templateOverride>
<e:templateOverride name="script">
	<script>
     	$(function(){
     		$("#btn-save").click(function(){
     			var isok = true;
     			var account = {
     					name: $("#name").val(),
     					password: $("#password").val()
     			};
     			
     			if(account.name == ""){
     				Tips.Error("请输入用户名");
     				isok = false;
     			}
     			if(account.password == ""){
     				Tips.Error("请输入密码");
     				isok = false;
     			}
     			
     			if(!isok){
    	        	return false;
    	        }
     			
     			$.ajaxRequest({
     		   		url:"verify.do",
     		   		para: account,
     				success: function(result){
     					if(result.isSuccess){
     						window.location = "index.html";
     					}
     					else{
     						Tips.Error(result.message);
     					}
     				}
     		     });
     		});
     		
     		 $("#name, #password").live("keydown", function (e) {
                 if (e.keyCode == 13) {
                     $("#btn-save").click();
                 }
             });

     	});
     </script>
</e:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>