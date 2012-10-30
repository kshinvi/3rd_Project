<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div id="dv2">
	<table border="1" width="400">
		<tr class="alt">
<!-- 			<td align="center">No</td>
			<td align="center">상세 내용</td> -->
			<td align="center">포인트</td>
<!-- 			<td align="center">획득 날짜</td> -->
		</tr>
		<s:iterator value="list">
			<tr>
				<%-- <td align="center"><s:property value="pointPk" /></td>
				<td align="center"><s:property value="detail" /></td> --%>
				<td align="right"><s:if test='%{point > 0}'>
						<font color="blue">+<s:property value="point" /></font>
					</s:if> <s:elseif test='%{point == 0 }'>
						<s:property value="point" />
					</s:elseif> <s:else>
						<font color="red"><s:property value="point" /></font>
					</s:else></td>
				<%-- <td align="center"><s:property value="regdate" /></td> --%>
			</tr>
		</s:iterator>
	</table>
	</div>