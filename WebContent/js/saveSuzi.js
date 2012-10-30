	var ctx;
	var thingelem;
	var hiragana;
	var secret;
	var lettersguessed = 0;
	var secretx = 160;
	var secrety = 50;
	var secretwidth = 50;
	var gallowscolor = "brown";

	//var steps = [ drawgallows, drawhead, drawbody, drawrightarm, drawleftarm,drawrightleg, drawleftleg, drawnoose ];
	var cur = 0;

	// 김주환이 만든 변수
	var gameStatus = false;
	var onReady;
	var distance = 500;
	var stage = 0;
	var second = (distance - 143) / 10;
	var score2;
	var score; 

	function drawgallows() {
		if (distance > 143) {
			var img1 = document.getElementById("source");
			var img2 = document.getElementById("frame");
			var img3 = document.getElementById("hint");
			var img4 = document.getElementById("timer");
			ctx.beginPath();
			ctx.fillText("수지를 구하라",10, 100);
			// 픽셀 정리
			ctx.clearRect(0, 0, canvas.width, canvas.height);
			// 컨텍스트 리셋
			ctx.beginPath();
			ctx.drawImage(img1, 0, 60);
			ctx.drawImage(img2, distance, 60);
			ctx.drawImage(img4, 160, 0, 32, 32);

			if(second < 28) {
				ctx.drawImage(img3, 370, 0, 32, 32);
				//hint();
			}
			distance -= 1;
			var limited = "남은 시간 : " + second;
			second2 = (distance - 143) / 10;
			second = Math.floor(second2);
			score2 = 69 + second;
			score = Math.floor(score2);
			ctx.fillText(limited, 190, 25);
		} else if (distance != 0){
			timeover();
		}
	}

	function init() {
		
		ctx = document.getElementById('canvasOne').getContext('2d');
		setgame();
		ctx.font = "bold 20pt Ariel";
	}

	function setgame() {
		//alert('setgame');
		var i;
		var x;
		var y;
		var uniqueid;

		// 게임 상태를 4(시작)로 둠
		onReady = 4;

		var ch = Math.floor(Math.random() * words.length);
		secret = words[ch];
		for (i = 0; i < secret.length; i++) {
			uniqueid = "s" + String(i);
			d = document.createElement('secret');
			d.innerHTML += ("<div class='blanks' id='"+uniqueid+"'> <img src='../game/suziImages/faq.png' width='32' height='32'> </div>");
			document.body.appendChild(d);
			thingelem = document.getElementById(uniqueid);
			x = secretx + secretwidth * i;
			y = secrety;
			thingelem.style.top = String(y) + "px";
			thingelem.style.left = String(x) + "px";
		}
		cur++;
		return false;
	}

	function pickelement(ev) {
		var not = true;
		//var picked = this.textContent; 
		var picked = hiragana;
		var i;
		var j;
		var uniqueid;
		var thingelem;
		var out;
		for (i = 0; i < secret.length; i++) {
			if (picked == secret[i]) {
				id = "s" + String(i);
				document.getElementById(id).textContent = picked;
				not = false;
				lettersguessed++;
				if (lettersguessed == secret.length) {
					ctx.fillStyle = gallowscolor;
					out = "スジさんを助けました。";
					distance = 0;					
					onReady = 1;
					stage++;

					ctx.fillText("点数 : " + score, 430, 25);
					ctx.fillText(out, 100, 380);
					ctx.fillText("다시 하려면 페이지 새로고침을..", 200, 410);
					eventWindowLoaded();
					//dv2 = document.getElementById("dv2");
					//dv2.innerHTML = ("<a href='a004.html'>새로하기</a>");
					for (j = 0; j < hiragana.length; j++) {
						uniqueid = "a" + String(j);
						thingelem = document.getElementById('canvas');
						thingelem.removeEventListener('click', pickelement,false);
					}
				}
			} 
		}
		if(not == true) {
			wrong(not);
		}
	}

	function timeover()
	{
		cur = 10;
		distance = 0;
		score = 0;
		for (i = 0; i < secret.length; i++) 
		{
			id = "s" + String(i);
			document.getElementById(id).textContent = secret[i];
		}
		ctx.fillStyle = gallowscolor;
		out = "졌습니다!";
		distance = 0;
		onReady = 0;
		ctx.clearRect(0, 0, canvas.width, canvas.height);

		ctx.fillText(out, 400, 380);
		ctx.fillText("다시 하려면", 400, 410);
		ctx.fillText("페이지 새로고침을..", 400, 440);
		var img3 = document.getElementById("end");
		ctx.drawImage(img3, 50, 50);
		//eventWindowLoaded();
		//dv2 = document.getElementById("dv2");
		//dv2.innerHTML = ("<a href='a004.html'>새로하기</a>");

	
		for (j = 0; j < hiragana.length; j++) 
		{
			uniqueid = "a" + String(j);
			thingelem = document.getElementById('canvas');
			thingelem.removeEventListener('click', pickelement, false);
		}				
		var id = this.id;
	}

	function wrong(not) {
		if (not) {
			cur++;
			distance -= 50;
			score = score - 15;
			if (cur > (secret.length + 1)) {
				for (i = 0; i < secret.length; i++) {
					id = "s" + String(i);
					document.getElementById(id).textContent = secret[i];
				}
				ctx.fillStyle = gallowscolor;
				out = "졌습니다!";
				distance = 0;
				onReady = 0;
				ctx.fillText(out, 200, 380);
				ctx.fillText("다시 하려면 페이지 새로고침을..", 200, 410);
				eventWindowLoaded();
				dv2 = document.getElementById("dv2");
				dv2.innerHTML = ("<a href='a004.html'>새로하기</a>");
				alert('abcd');
			
				for (j = 0; j < hiragana.length; j++) {
					uniqueid = "a" + String(j);
					thingelem = document.getElementById('canvas');
					thingelem.removeEventListener('click', pickelement, false);
				}				
			}
		}
		var id = this.id;
	}

	function change_bg(e, obj, val) {
		//alert("onReady = " + onReady);
		if (onReady != 0) {
			if (!e)
				var e = window.event;

			if (e.type == 'click') {
				if(cur != 10) {
					hiragana = val;
					addEventListener('click', pickelement, false);
					if (obj.getAttribute('clicked') != '1') {
//					obj.style.backgroundColor = '#d28336';
					obj.style.backgroundColor = '#dddddd';
						obj.setAttribute('clicked', '1');
					} else {
						obj.style.backgroundColor = '#ffffff';
						obj.setAttribute('clicked', '0');
					} 
				}
			}
			
			if (e.type == 'mouseover') {
				obj.style.backgroundColor = '#d28336';
			}
			if (e.type == 'mouseout') {
				obj.style.backgroundColor = '#ffffff';
			}
		}
	}

// 여기서 부터 canvas에 동영상 재생하기 위한 부분
var videoElement;
var videoDiv;
var cnt = 0;
// 동영상 불러오기
function eventWindowLoaded() {
	if (cnt != 1 && cur > (secret.length + 1)) {
		alert('수지를 구하지 못했습니다!');
	} else if(cnt != 1 && (lettersguessed == secret.length)) {
		alert('당신은 수지를 구했습니다!');
	}
	if(cnt == 0) 
	{
		videoElement = document.createElement("video");
		videoDiv = document.createElement('div');
		document.body.appendChild(videoDiv);
		videoDiv.appendChild(videoElement);
		videoDiv.setAttribute("style", "display:none;");
		var videoType = supportedVideoFormat(videoElement);
		if (videoType == "") 
		{
			alert("no video support");
			return;
		}
		if(onReady == 0) 
		{
			videoElement.setAttribute("src", "../game/suziImages/muirbeach." + videoType);
			videoElement.addEventListener("canplaythrough",videoLoaded,false);	
			cnt++;
		} 
		else if(onReady == 1)
		{
			videoElement.setAttribute("src", "../game/suziImages/suzi1." + videoType);
			videoElement.addEventListener("canplaythrough",videoLoaded,false);
			cnt++;
		}
	}
}

function supportedVideoFormat(video) {
	var returnExtension = "";
	if (video.canPlayType("video/webm") =="probably" || video.canPlayType("video/webm") == "maybe") {
		returnExtension = "webm";
	} else if(video.canPlayType("video/mp4") == "probably" || video.canPlayType("video/mp4") == "maybe") {
		returnExtension = "mp4";
	} else if(video.canPlayType("video/ogg") =="probably" || video.canPlayType("video/ogg") == "maybe") {
		returnExtension = "ogg";
	} 
	
	return returnExtension;
	
}



function canvasSupport () {
  	return Modernizr.canvas;
}


function videoLoaded(event) {

	canvasApp();

}



function canvasApp() {
	
  if (!canvasSupport()) {
			 return;
  		}

  function  drawScreen () {
		//Background		
		context.fillStyle = '#ffffaa';
		context.fillRect(0, 50, theCanvas.width, theCanvas.height - 100);
		//Box
		context.strokeStyle = '#000000'; 
		context.strokeRect(5, 55, theCanvas.width-10, theCanvas.height-110);
		//video
		context.drawImage(videoElement , 50, 100);			
	}
	
	var theCanvas = document.getElementById('canvas');
	var context = theCanvas.getContext('2d');
	videoElement.play();
	
	setInterval(drawScreen, 33);	
	
}
function hint() {
	canvas.addEventListener("mousedown", getMouse, false); 
	getMouse();

}

function getMouse(event) {

	if(event == null) {
		event = canvas.event
	}

	// Firefox 브라우저 용
    if (event.layerX || event.layerX == 0) { 
        posX = event.layerX;
		posY = event.layerY;
	}
	// Opera 브라우저 용
	else if (event.offsetX || event.offsetX == 0) {
	   posX = event.offsetX;
	   posY = event.offsetY;
	}

	checkMouse();

	alert("posX : " + posX);
	alert("posY : " + posY);
}

function checkMouse() {
	alert("체크마우스 들어옴");
	if( (posX >= 32 && posX <= 64) && (posY >= 32 && posY <= 64) ) 
	{
		alert('좌표클릭');
	} 
}
window.onload = setInterval(drawgallows, 100);
window.onload = init();