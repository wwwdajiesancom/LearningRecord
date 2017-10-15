<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<form id="freightfit" method="post" title="运费设置">
	起送金额:<input class="easyui-numberbox" style="width: 204px;"
		name="sendmoney" required="true">元<br> 
	运费设置:<input
		type="radio" name="is_have" value="0" id="freight_no">免运费 <input
		type="radio" name="is_have" value="1" id="freight_yes">收运费<br>
</form>

