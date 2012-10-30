<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../js/jquery.easing.1.3.js"></script>
<title>Sliding Door Effect from Queness</title>
<script>

$(document).ready(function() {

	//Custom settings
	var style_in = 'easeOutBounce';
	var style_out = 'jswing';
	var speed_in = 1000;
	var speed_out = 300;	

	//Calculation for corners
	var neg = Math.round($('.qitem').width() / 2) * (-1);	
	var pos = neg * (-1);	
	var out = pos * 2;
	
	$('.qitem').each(function () {
	
		url = $(this).find('a').attr('href');
		img = $(this).find('img').attr('src');
		alt = $(this).find('img').attr('img');
		
		$('img', this).remove();
		$(this).append('<div class="topLeft"></div><div class="topRight"></div><div class="bottomLeft"></div><div class="bottomRight"></div>');
		$(this).children('div').css('background-image','url('+ img + ')');

		$(this).find('div.topLeft').css({top:0, left:0, width:pos , height:pos});	
		$(this).find('div.topRight').css({top:0, left:pos, width:pos , height:pos});	
		$(this).find('div.bottomLeft').css({bottom:0, left:0, width:pos , height:pos});	
		$(this).find('div.bottomRight').css({bottom:0, left:pos, width:pos , height:pos});	

	}).hover(function () {
	
		$(this).find('div.topLeft').stop(false, true).animate({top:neg, left:neg}, {duration:speed_out, easing:style_out});	
		$(this).find('div.topRight').stop(false, true).animate({top:neg, left:out}, {duration:speed_out, easing:style_out});	
		$(this).find('div.bottomLeft').stop(false, true).animate({bottom:neg, left:neg}, {duration:speed_out, easing:style_out});	
		$(this).find('div.bottomRight').stop(false, true).animate({bottom:neg, left:out}, {duration:speed_out, easing:style_out});	
				
	},
	
	function () {

		$(this).find('div.topLeft').stop(false, true).animate({top:0, left:0}, {duration:speed_in, easing:style_in});	
		$(this).find('div.topRight').stop(false, true).animate({top:0, left:pos}, {duration:speed_in, easing:style_in});	
		$(this).find('div.bottomLeft').stop(false, true).animate({bottom:0, left:0}, {duration:speed_in, easing:style_in});	
		$(this).find('div.bottomRight').stop(false, true).animate({bottom:0, left:pos}, {duration:speed_in, easing:style_in});	
	
	}).click (function () {
		window.location = $(this).find('a').attr('href');	
	});
});
</script>
<style>

body {
	font-family:arial;	
}

.qitem {
	width:256px;
	height:168px;	
	border:7px solid #5599FF;	
	margin:5px 5px 5px 0;
	background: url('../game/bg/bg.gif') no-repeat;	
	
	/* required to hide the image after resized */
	overflow:hidden;
	
	/* for child absolute position */
	position:relative;
	
	/* display div in line */
	float:left;
	cursor:hand; cursor:pointer;
}

	.qitem img {
		border:0;
	
		/* allow javascript moves the img position*/
		position:absolute;
		z-index:200;
	}

	.qitem .caption {
		position:absolute;
		z-index:0;	
		color:#ccc;
		display:block;
	}

		.qitem .caption h4 {
			font-size:12px;
			padding:10px 5px 0 8px;
			margin:0;
			color:#369ead;
		}

		.qitem .caption p {
			font-size:15px;	
			padding:3px 5px 0 8px;
			margin:0;
		}

/* Setting for corners */

.topLeft, .topRight, .bottomLeft, .bottomRight {
	position:absolute;
	background-repeat: no-repeat; 
	float:left;
}

.topLeft {
	background-position: top left; 	
} 

.topRight {
	background-position: top right; 
} 

.bottomLeft {
	background-position: bottom left; 
} 

.bottomRight {
	background-position: bottom right; 
}

.clear {
	clear:both;	
}
</style>
<script type="text/javascript">
function openGame() {
	if(arguments[0] == '1'){
		window.open('../game/before_pairing.action', 'game', 'width=940 height=800 menubar=no scrollbars=no left=0 top=0');	
	}
	else if(arguments[0] == '2'){
		window.open('../game/blackJack.action', 'game', 'width=970, height=750, toolbar=no, location=no, status=no, resizable=no, menubar=no, scrollbars=no, left=0, top=0');	
	}	
	else if(arguments[0] == '3'){
		window.open('../game/saveSuzi.action', 'game', 'width=1024 height=768 menubar=no scrollbars=no left=0 top=0 ');	
	}
	else if(arguments[0] == '4'){
		window.open('../game/playMaze.action', 'game', 'width=1000 height=920 menubar=no scrollbars=no left=0 top=0 ');	
	}
}
/////////////////////////////////////////////////
var restUrl = "http://203.233.196.177:8281/with/api";
window.onload = function(){
	likeInit();	
};
function likeInit() {
	getLike(294);
	getLike(295);
	getLike(296);
	getLike(297);
	//getLike(298);
}
function getLike(){
	var bubbleId = "bubbleID" + arguments[0];
	$.ajax({
		type : 'GET',
		url : restUrl + '/likes/' + arguments[0],
		dataType : "json",
		success : function(data) {
			document.getElementById(bubbleId).innerHTML = ""+data.count;
		}
	});
}
function doLike(){
	var arg0 = arguments[0];
	
	$.ajax({
		type : 'POST',
		url : restUrl + '/likes',
		dataType : "json",
		data: {post_no:arg0, developer:"rest3", user:"<s:property value="#session.member.memberId"/>"},
		success : function(data) {
			if(data.result == false){
				if(confirm("이미 추천하셨습니다. 추천을 취소하시겠습니까?")){
					deleteLike(arg0);
				}
			}
			document.getElementById("bubbleID"+arg0).innerHTML = ""+data.count;
		}
	});
}
function deleteLike(){
	var arg0 = arguments[0];
	$.ajax({
		type : 'POST',
		url : restUrl + '/likes',
		dataType : "json",
		data: {post_no:arg0, user:"<s:property value="#session.member.memberId"/>",_method:"DELETE"},
		success : function(data) {
			document.getElementById("bubbleID"+arg0).innerHTML = ""+data.count;
		}
	});
}
</script>
</head>

<body>
<h4>원하는 게임을 고르세요.</h4>
<!-- 현재 내 포인트 : <b><font size="5" color="red"> ${pointStr} </font></b> -->
<hr/>
<div class="qitem">
	<a href="javascript:openGame('1');"><img src="../game/bg/4000.png" alt="사천성 게임" title="" width="256" height="256"/></a>
	<span class="caption"><h2>사천성</h2><p>카드 2쌍을 뒤집어서 같은 뜻의 단어와 그림을 맞추는 게임.<br/><br/>빨리 맞출수록 더욱 많은 포인트가!</p></span>
</div>
<div class="qitem">
	<a href="javascript:openGame('2');"><img src="../game/bg/bjack.png" alt="블랙잭 게임" title="" width="256" height="256"/></a>
	<span class="caption"><h2>Black Jack</h2><p>카드를 받아서 더한 값이 21(블랙잭)에 가장 가까운 점수를 가진 사람이 이기는 카드 게임.<br/><br/>로또보다 강력하다!</p></span>
</div>
<div class="clear">
<table width="530" border="0">
	<tr align="center">
		<td>
			<p>사천성</p>
			<div class="post-meta-like-comment">
				<button class="btn btn-mini" type="button" onClick='doLike(294)'><i class="icon-thumbs-up"></i>Like</button>
				<button class="btn btn-mini-bubble" type="button"><div id="bubbleID294"></div></button>
			</div>
		</td>
		<td>
			<p>블랙잭</p>
			<div class="post-meta-like-comment">
				<button class="btn btn-mini" type="button" onClick='doLike(295)'><i class="icon-thumbs-up"></i>Like</button>
				<button class="btn btn-mini-bubble" type="button"><div id="bubbleID295"></div></button>
			</div>
		</td>
	</tr>
</table>
<br/>
</div>
<div class="qitem">
	<a href="javascript:openGame('3');"><img src="../game/bg/suzibg.png" alt="수지 구하기" title="" width="256" height="256"/></a>
	<span class="caption"><h2>수지 구하기</h2><p>제한 시간내에 물음표(?)로 감추어진 단어를 오십음도표를 클릭해서 수지짜응~을 구하라.<br/><br/>전구 아이콘을 클릭 하면 힌트가!!</p></span>
</div>

<div class="qitem">
	<a href="javascript:openGame('4');"><img src="../game/bg/miro.png" alt="미로 찾기" title="" width="256" height="256"/></a>
	<span class="caption"><h2>단어맞추기 미로</h2><p>미로 안에서 키보드의 화살표 키를 이용해서 단어를 순서대로 찾아가는 게임.<br/><br/>일정 시간 동안 못찾으면 힌트가!!</p></span>
</div>
<div class="clear">
<table width="530" border="0">
	<tr align="center">
		<td>
			<p>수지 구하기</p>
			<div class="post-meta-like-comment">
				<button class="btn btn-mini" type="button" onClick='doLike(296)'><i class="icon-thumbs-up"></i>Like</button>
				<button class="btn btn-mini-bubble" type="button"><div id="bubbleID296"></div></button>
			</div>
		</td>
		<td>
			<p>단어 맞추기 미로</p>
			<div class="post-meta-like-comment">
				<button class="btn btn-mini" type="button" onClick='doLike(297)'><i class="icon-thumbs-up"></i>Like</button>
				<button class="btn btn-mini-bubble" type="button"><div id="bubbleID297"></div></button>
			</div>
		</td>
	</tr>
</table>
</div>

</body>
</html>
