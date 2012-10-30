<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src = "/emn/js/plax.js" type = "text/javascript" ></script>
<style type="text/css">

img#plax-logo {
    left: 130px;
    position: absolute;
    top: 140px;
    z-index: 1;
}
img#plax-sphere-1 {
	left: 140px;
    position: absolute;
    top: 320px;
    z-index: 4;
}
img#plax-sphere-2 {
left: 140px;
    position: absolute;
    top: 270px;
    z-index: 3;
}
img#plax-sphere-3 {
left: 140px;
    position: absolute;
        
    top: 220px;
    z-index: 2;
}

div#uppershell {
   	font-family: Skranji;
	font-size:90px;
	line-height:90px;
	margin:0;
	text-align: center;
	color : #7b68ee;
	text-shadow: 1px 1px 2px #afeeee,
				1px -1px 2px #afeeee,
				5px 8px 8px rgb(0,0,0);
}
div#shell {
    z-index: 1;
}
@font-face{
	font-family: "Skranji";	
	font-size:400px;
	font-style: bold;
	src : url("/pairing/osaka.unicode.ttf") format("truetype");
			
}
div#login {
    left: 790px;
    position: absolute;
    top: 88px;
    z-index: 5;
}
div#audio1 {
    left: 800px;
    position: absolute;
    top: 100px;
    z-index: 6;
}


</style>

<script>

	// id와 pw를 체크하여 둘 다 true인 경우만 true를 리턴하여 submit하도록 한다.
	function checkFrmLogin(){
		
		if(	checkId() & checkPw() ){
			return true;
		}
		return false;
	}

	// ID가 영문자와 숫자인지 확인하여 아니면 false 리턴하고 에러를 표시하는 함수.
	function checkId(){
		var id = document.frmLogin.memberId.value;		
		var spnCautionId = document.getElementById("spnCautionId");
		
		document.frmLogin.memberId.value = id.toLowerCase();
		
		// 값 길이를 확인하고 0이상이면 내용을 검사
		if(checkLength(id, 5, 12)){
			spnCautionId.innerHTML = "";
			if(checkEngChar(id) == false){
				spnCautionId.innerHTML = "<font color='red'size='1px'>아이디에는 영문자와 숫자만 입력 가능합니다.</font>";
				return false;
			}
		}
		else {
			spnCautionId.innerHTML = "<font color='red'size='1px'>값을 5자~12자 이내로 입력하세요.</font>";
			return false;
		}		
		return true;
	}
	
	// PW가 영문자와 숫자, 특수문자인지 확인하여 아니면 false 리턴하고 에러를 표시하는 함수.
	function checkPw(){
		var pw = document.frmLogin.memberPw.value;
		var spnCautionPw = document.getElementById("spnCautionPw");

		// 값 길이를 확인하고 0이상이면 내용을 검사
		if(checkLength(pw, 5, 12)){
			spnCautionPw.innerHTML = "";
			if(checkEngChar(pw) == false && checkSpChar(pw) == false){
				spnCautionPw.innerHTML = "<font color='red'size='1px'>비밀번호에는 영문자와 숫자, 특수기호만 입력 가능합니다.</font>";
				return false;
			}
			else {
				spnCautionPw.innerHTML = "";		
			}
		}
		else {
			spnCautionPw.innerHTML = "<font color='red'size='1px'>값을 5자~12자 이내로 입력하세요.</font>";
			return false;
		}
		
		return true;
	}
	
	if('<s:property value="%{message}" />' == ""){
		//alert("blank");첫 호출
	}
	if('<s:property value="%{message}" />' == "rest"){
		//do rest
		$.ajax({
			type : 'POST',
			url : 'http://203.233.196.177:8281/with/api/members/asd',
			dataType : "json",
			data: {name:"<s:property value="%{memberId}"/>",password:"<s:property value="%{memberPw}"/>"},
			success : function(data) {
				if(data.result){
					//document.location = "../member/UserRegForm.action?memberId=<s:property value="%{memberId}"/>&memberPw=<s:property value="%{memberPw}"/>";
					frmLogin.action = "UserRegForm.action";
					frmLogin.submit();
				}else{
					spnCautionPw.innerHTML = "<font color='red'size='1px'>회원정보가 없습니다</font>";
					frmLogin.memberPw.value= "";
				}
			}
		});
	}
</script>
</head>
<body>
  <div id="shell" style="margin: 0px auto; left:0px; top:0px; backgroundcolor:red;">
     <img src="/emn/img/characters.png" width="964" height="363" id="plax-sphere-1" >
     <img src="/emn/img/other-graphics6.png" width="964" height="363" id="plax-sphere-2" >
     <img src="/emn/img/clouds.png" width="964" height="363" id="plax-sphere-3" >
     <img src="/emn/img/title3.jpg"  id="plax-logo" >
   </div>
<div id = login>
<s:if test="#session.member == null">
	<form name="frmLogin" method="post" class="form-inlin" action="UserLoginAction.action" onsubmit="return checkFrmLogin();">
	<span id="spnCautionId"></span><span id="spnCautionPw"></span><br/>
	<input type="text" class="span6" name="memberId" value="${memberId}" onchange="checkId();" maxlength="12" autofocus placeholder="ID" />
	<input type="password" class="span6" name="memberPw" onchange="checkPw();" maxLength="12" placeholder="Password" value="${memberPw}" /><br/>
	<div class="pull-right"><button type="submit" class="btn btn-primary">로그인</button></div>
	</form>
</s:if>
</div>
<div id = "audio1">
	<img src="/emn/img/audioOn.png" width="30px" height="30px" onclick="bgmTogle(this);">
	<audio id="bgmPlayer" autoplay loop>
		<source src = "/emn/mp3/bgaaa.ogg" type="audio/ogg"></source>
		<source src = "/emn/mp3/bgaaa.mp3" type="audio/mpeg"></source>
		<source src = "/emn/mp3/bgaaa.wav" type="audio/wav"></source>
	</audio>
</div>

<!-- 화면 위치 재설정용 -->
<script type="text/javascript">
	function initPlax(){
	var plaxSphere1 = document.getElementById("plax-sphere-1");
	var plaxSphere2 = document.getElementById("plax-sphere-2");
	var plaxSphere3 = document.getElementById("plax-sphere-3");
	var plaxLogo = document.getElementById("plax-logo");
	var divLogin = document.getElementById("login");
	var divAudio1 = document.getElementById("audio1");
	
	var centerPosition = document.body.clientWidth / 2;
	var middlePosition = document.body.clientHeight / 2;
	//alert("center = " + centerPosition + ", middle = " + middlePosition);
	
	plaxSphere1.style.left = (centerPosition - 482) + "px"; 
	plaxSphere2.style.left = (centerPosition - 482) + "px";
	plaxSphere3.style.left = (centerPosition - 482) + "px";
	plaxLogo.style.left = (centerPosition - 482) + "px";
	divLogin.style.left = (centerPosition + 150) + "px";
	divAudio1.style.left = (centerPosition - 470) + "px";
	}
	
	function bgmTogle(btnEl){
		var bgmPlayer = document.getElementById("bgmPlayer");
		if(bgmPlayer.paused){
			btnEl.src="/emn/img/audioOn.png";
			bgmPlayer.play();
		}
		else {
		btnEl.src="/emn/img/audioOff.png";
		bgmPlayer.pause();
		}
				
	}
	
	initPlax();
</script>


<script type="text/javascript">
      $(document).ready(function () {        
        $('#plax-sphere-1').plaxify({"xRange":40,"yRange":0})
        $('#plax-logo').plaxify({"xRange":20,"yRange":20})
        $('#plax-sphere-2').plaxify({"xRange":10,"yRange":0})
        $('#plax-sphere-3').plaxify({"xRange":40,"yRange":0,"invert":true})
        $.plax.enable()
      })
</script>

</body>
</html>