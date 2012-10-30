<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>포인트 관리 메뉴</title>
<script>
	// 일자별 포인트 현황 가져오기 (ajax)
	function getPointResult() {

		var date = document.Fm1.month.value;
		if (date == "" || date == null) {
			alert('날짜를 선택하세요.');
			return false;
		}
		//alert(date);
		//ajax call
		new net.ContentLoader("PointResult.action", "POST", onRead, onError,
				"regDate=" + date);
	}
	
	// 포인트 저장했을 때 실행 될 함수 (ajax)
	function onRead() {
		var result = document.getElementById("dv1");
		var resultVal = this.req.responseText;
		var arrs = resultVal.split(",");
		
		if(arrs[1] > 0) {
			arrs[1] = "<font color='blue'>+" + arrs[1] + "</font>";
		} else {
			arrs[1] = "<font color='red'>" + arrs[1] + "</font>";
		}
		
		result.innerHTML = "";
		result.innerHTML += "<table border='1' width='400'><tr class='alt'><td>일자</td><td>포인트 현황</td><td>총 게임횟수</td></tr><tr><td>" + arrs[0] + "</td><td>" + arrs[1] + "</td><td>" + arrs[2] + "</td></tr></table>";
	}

	// 해당 인원의 모든 포인트 기록 조회하기
	function getAllPoint() {
		var date = 0;
		document.getElementById("dv2").style.display="";
		//new net.ContentLoader("PointAllResult.action", "POST", onRead2, onError,"");
	}
	
	function onRead2(data) {
		var resultVal2 = this.req.responseText;
		var arrs = resultVal2.split(",");
		var result2 = document.getElementById("dv2");
		//var resultVal = this.req.responseText;
		result2.innerHTML = data;
	}
	// 포인트 저장에 실패했을 때 실행 될 함수 (ajax)
	function onError() {
		alert("error : " + this.req.getAllResponseHeaders);
	}
</script>
<style type="text/css">
table {
	width: 500px;
	border-collapse: collapse;
	text-align: center;
	font-family: 'Trebuchet MS';
}

td,th {
	font-size: 10pt;
	border: 1px solid #98bf21;
	height: 30px;
}

th {
	background-color: #A7C942;
	color: #ffffff;
	font-family: Georgia;
}

tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}

font.uline {
	text-decoration: underline;
}
</style>
</head>
<body>
	<font class="uline" color="green"> <s:property
			value="#session.member.memberName" />
	</font>님의 현재 포인트 :
	<font color="blue">${pointStr}</font>점
	<hr />
	게임별 조회
	<br />
	<table border="1" width="200">
		<tr class="alt">
			<td align="center">게임명</td>
			<td align="center">포인트 현황</td>
		</tr>
		<s:iterator value="detailList">
			<tr>
				<td align="center"><s:property value="detail" /></td>
				<td align="right"><s:if test='%{sum > 0}'>
						<font color="blue">+<s:property value="sum" /></font>
					</s:if> <s:elseif test='%{sum == null }'>
						0
					</s:elseif> <s:else>
						<font color="red"><s:property value="sum" /></font>
					</s:else></td>
			</tr>
		</s:iterator>
	</table>
	<hr />
	날짜별 조회
	<form name="Fm1" method="post">
		<input type="date" name="month" value="" /> 
		<input type="button" value="조회" onClick="return getPointResult();" />
	</form>
	<div id="dv1">
		<%-- <table border="1" width="400">
			<tr class="alt">
				<td>일자</td>
				<td>포인트 현황</td>
				<td>총 게임횟수</td>
			</tr>

			<s:iterator value="regDateList">
				<tr>
					<td align="center"><s:property value="regdate" /></td>
					<td align="right"><s:if test='%{sum > 0}'>
							<font color="blue">+<s:property value="sum" /></font>
						</s:if> <s:elseif test='%{sum == 0 }'>
							<s:property value="sum" />
						</s:elseif> <s:else>
							<font color="red"><s:property value="sum" /></font>
						</s:else></td>
					<td align="right"><s:property value="count" /></td>
				</tr>
			</s:iterator>

		</table> --%>
	</div>
	<hr />
	
		<form name="Fm2" method="post">
		전체 포인트
		<input type="button" value="조회" onClick="return getAllPoint();" />
	</form>
	<div id="dv2" style="display: none;">
	<table border="1" width="400">
		<tr class="alt">
			<td align="center">No</td>
			<td align="center">상세 내용</td>
			<td align="center">포인트</td>
			<td align="center">획득 날짜</td>
		</tr>
		<s:iterator value="list">
			<tr>
				<td align="center"><s:property value="pointPk" /></td>
				<td align="center"><s:property value="detail" /></td>
				<td align="right"><s:if test='%{point > 0}'>
					<font color="blue">+<s:property value="point" /></font>
					</s:if> <s:elseif test='%{point == 0 }'>
						<s:property value="point" />
					</s:elseif> <s:else>
						<font color="red"><s:property value="point" /></font>
					</s:else></td>
				<td align="center"><s:property value="regdate" /></td>
			</tr>
		</s:iterator>
	</table>
	</div>
</body>
</html>