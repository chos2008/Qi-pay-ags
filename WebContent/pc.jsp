<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>支付中心</title>
</head>
<body topmargin="0" rightmargin="0" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<div style="width: 100%; height: 35px; line-height: 35px; text-align: center; border: 0px solid silver; border-bottom: 1px solid silver;">
    <label>支付中心</label>
</div>

<div style="width: 100%; height: auto;">
    <div style="width: 100%; height: auto; margin: 0px auto;">
    	<c:choose>
		<c:when test="${error.status != 'success'}">
		<div style="border-radius: 7px 7px 7px 7px; border: 1px solid silver; padding: 2px 3px; margin: 5px 3px;">
	    	<div style="width: 100%; height: 22px; border: 0px solid silver;">
	    		<label>状态 ${error.status}</label>
	    	</div>
	    	<div style="width: 100%; height: auto; border: 0px solid silver;">
	    		<p style="margin: 2px 0px 0px 0px; padding: 0px 0px;">
	                <span style="width: 80px; display: inline-block;">
	                    <label>原因：</label>
	                </span>
	                <span>${error.message}</span>
	            </p>
	    	</div>
    	</div>
		</c:when>
		
		<c:otherwise>
    	<div style="border-radius: 7px 7px 7px 7px; border: 1px solid silver; padding: 2px 3px; margin: 5px 3px;">
	    	<div style="width: 100%; height: 22px; border: 0px solid silver;">
	    		<label>来自 ${taa.name}</label>
	    	</div>
	    	<div style="width: 100%; height: 22px; border: 0px solid silver;">
	    		<p style="margin: 2px 0px 0px 0px; padding: 0px 0px;">
	                <span style="width: 80px; display: inline-block;">
	                    <label>当前账户：</label>
	                </span>
	                <span>${data.tr}</span>
	            </p>
	    	</div>
	    	<div style="width: 100%; height: 22px; border: 0px solid silver;">
	    		<p style="margin: 2px 0px 0px 0px; padding: 0px 0px;">
	                <span style="width: 80px; display: inline-block;">
	                    <label>交易金额：</label>
	                </span>
	                <span>${data.amount}元</span>
	            </p>
	    	</div>
	    	<div style="width: 100%; height: 22px; margin-top: 35px; border: 0px solid silver;">
	    		<p style="margin: 2px 0px 0px 0px; padding: 0px 0px;">
	                <!-- 
	                <span style="width: 80px; display: inline-block;">
	                    <label>账户余额：</label>
	                </span>
	                <span>100000元</span>
	                 -->
	            </p>
	    	</div>
    	</div>
    	
    	<script type="text/javascript">
    	function pay() {
    		//window.location.href = "../../rest/p";
    		document.forms['d'].submit();
        }
    	</script>
    	<div style="border-radius: 7px 7px 7px 7px; border: 1px solid silver; padding: 2px 3px; margin: 5px 3px;">
	    	<div style="width: 100%; height: auto; border: 0px solid silver; border-top: 1px solid silver;">
	    		<div style="width: 100%; height: 20px; line-height: 20px; border: 0px solid silver;">
		    		<label style="font-size: 10px; color: gray;">请选择支付方式：</label>
		    	</div>
		    	<div style="width: 100%; height: auto; border: 0px solid silver; display: inline-block;">
		    		<ul style="list-style-type: none; margin: 0px 0px; padding: 0px 0px;">
		    			<c:forEach items="${tas}" var="variable">
		    			<li style="float: left; margin: 2px 2px;" onclick="javascript: pay();">
		    				<div style="width: 118px; height: 138px;">
		    					<div style="width: 100%; height: 118px; background: url('../../images/ta/icon-alipay-118x118.png') no-repeat; background-position: 0px 0px;">
		    						
		    					</div>
		    					<div style="width: 100%; height: 20px; line-height: 20px; text-align: center; font-size: 10px"><label>${variable.name}</label></div>
		    				</div>
		    			</li>
						</c:forEach>
		    		</ul>
		    	</div>
	    	</div>
    	</div>
    	<form name="d" action="../../rest/p" method="post" enctype="application/x-www-form-urlencoded">
		<c:forEach items="${data}" var="p">
		<input type="hidden" name="${p.key}" value="${p.value}"/>
		</c:forEach>
		</form>
		</c:otherwise>
		</c:choose>
    	<div style="width: 100%; height: auto; border: 0px solid silver; border-top: 1px solid silver;">
    		<label style="font-size: 10px; color: gray;">上海正途网络科技有限公司客服热线: 021-00000000 | 问题反馈</label>
    	</div>
    </div>
</div>
</body>
</html>