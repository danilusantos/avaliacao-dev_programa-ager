<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>

<s:set var="novo" scope="page">${param.novo}</s:set>
<s:property value="#attr.param1"/>	

<a
	href="${novo}"
	class="btn btn-success"> <s:text
		name="label.botao.novo" />
</a>