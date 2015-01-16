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
    <div style="width: 320px; height: auto; margin: 0px auto;">
    	<div style="border-radius: 7px 7px 7px 7px; border: 1px solid silver; padding: 2px 3px; margin: 2px 2px;">
	    	<div style="width: 100%; height: 22px; border: 0px solid silver;">
	    		<label>交易结果</label>
	    	</div>
	    	<c:choose>
			<c:when test="${error.status != 'success'}">
			<div style="border-radius: 7px 7px 7px 7px; border: 1px solid silver; padding: 2px 3px; margin: 2px 2px;">
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
			<script type="text/javascript">
			function forwardTo() {
				window.location.href = '${data.forward}';
			}
			</script>
			<div style="width: 100%; height: 22px; border: 0px solid silver;">
	    		<p style="margin: 2px 0px 0px 0px; padding: 0px 0px;">
	                <span style="width: 80px; display: inline-block;">
	                    <label>当前账户：</label>
	                </span>
	                <span>${data.tr}</span>
	            </p>
	    	</div>
	    	<div style="width: 100%; height: 22px; margin-top: 35px; border: 0px solid silver;">
	    		<p style="margin: 2px 0px 0px 0px; padding: 0px 0px; text-align: center;">
	                <label>交易成功</label>
	            </p>
	    	</div>
	    	<c:if test="${! empty data.forward}">
	    	<div style="width: 100%; height: 22px; margin-top: 35px; border: 0px solid silver;">
	    		<p style="margin: 0px auto; padding: 0px 0px; text-align: center;">
	                <input type="submit" value="返回" onclick="javascript: forwardTo();"/>
	            </p>
	    	</div>
	    	</c:if>
			</c:otherwise>
			</c:choose>
    	</div>
    	<div style="width: 100%; height: auto; border: 0px solid silver; border-top: 1px solid silver;">
    		<label style="font-size: 10px; color: gray;">上海齐思客服热线: 021-00000000 | 问题反馈</label>
    	</div>
    </div>
</div>
</body>
</html>