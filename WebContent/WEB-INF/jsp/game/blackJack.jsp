<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="utf-8"> -->
<title>포인트 까먹기 대작전 - 블랙잭!!, 인생 한 방이지 뭐~!!</title>

<style>
body{
	backImgground-color: white;
	color: black;
	font-size: 18px;
	font-family; Verdana, Geneva, sans-serif;
}
footer {
	display: block;
	font-family: Tahoma, Geneva, sans-serif;
	text-align: left;
	font-style: oblique;
	font-size: 8px;
}
header {
	font-family: Tahoma, Geneva, sans-serif;
	width: 100%;
	display: block;
}
</style>

<script>
var cwidth = 800;
var cheight = 600;
var cardw = 75;
var cardh = 107;
var playerxp = 100;
var playeryp = 300;
var housexp = 500;
var houseyp = 100;
var housetotal = 0;
var playertotal = 0;
var pi = 0;
var hi = 0;
var deck = [];
var playerhand = [];
var househand = [];

// 이미지 변수들
var cbackImg = new Image();		// 카드 뒷면 이미지
cbackImg.src = "../game/blackJack/back-blue.jpg";
var backImg = new Image();		// 배경화면 이미지
backImg.src = "../game/blackJack/background.jpg";
var upImg = new Image();		// 베팅 화살표 위 이미지
upImg.src = "../game/blackJack/up.png";
var downImg = new Image();		// 베팅 화살표 아래 이미지
downImg.src = "../game/blackJack/down.png";
var btnImg = [];				// 버튼 이미지들
for(i = 0; i < 5; i++) {
	btnImg[i] = new Image();
}
btnImg[0].src = "../game/blackJack/start.jpg";
btnImg[1].src = "../game/blackJack/more.jpg";
btnImg[2].src = "../game/blackJack/hold.jpg";
btnImg[3].src = "../game/blackJack/double.jpg";
btnImg[4].src = "../game/blackJack/newgame.jpg";


// 추가 변수
var gameStatus = false;			// 게임중인지 아닌지 상태저장 변수
var dblStatus = false;			// 더블 상태저장 변수
var point = ${pointStr};		// 포인트 변수
var bPoint = point;				// 얼마땄는지 그 차이를 알기 위한 변수
var bet = 0;					// 베팅금액 변수
var betDefault = 50;			// 베팅금액 단위 변수
var posX = 0;					// 마우스 x 좌표 위치
var posY = 0;					// 마우스 y 좌표 위치


// 게임 상태 저장 변수
var READY = 0;					// 게임 시작전 상태, 배팅을 할 수 있다.
var GAME = 1;					// 현재 게임중 상태
var CHOICE = 2;					// 카드를 열어서 more/hold/double 을 선택하는 상태
var END = 3;					// 게임이 끝난 상태
var ALLIN = 4;					// 돈을 모두 잃어서 게임이 더이상 불가능한 상태

window.onresize = function() {window.resizeTo(970, 750); };


function init() {
//	alert("init()!!");
	if(point < 50) {
		alert("포인트가 없어서 게임을 실행할 수 없습니다.")
		gameStatus = ALLIN;
		window.close();
		return;
	}
	gameStatus = READY;
	bet = betDefault;
	ctx = document.getElementById('canvas').getContext('2d');
	ctx.font = "italic 20pt Georgia";
	ctx.fillStyle = "blue";
	buildDeck();	
	
	canvas1 = document.getElementById('canvas');
//	window.addEventListener('keydown', getKey, false);
	canvas.addEventListener("mousedown", getMouse, false); 
	shuffle();
	ctx.drawImage(backImg, 70, 60, 600, 400);
	drawShadow();
	drawTitle();
	drawScore();
	drawPScore();
	drawPoint();
	drawButton();
	dealReady();
}

function getMouse(event) {
//	alert("getMouse()!!");

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

//	alert("posX : " + posX);
//	alert("posY : " + posY);
}

// 마우스 좌표를 가지고 어떤 이벤트인지 처리할 함수
function checkMouse() {
//	alert("GameStatus : " + gameStatus);
	// 베팅 up 이미지를 누른 경우
	if( (posX >= 650 && posX <= 672) && (posY >= 472 && posY <= 494) ) {
//		alert("업 이미지 눌렀다!!");
		if(gameStatus == READY) {
			if(bet <= (point-betDefault) && bet < 1000) {
				bet += betDefault;
//				alert(bet);
			}
		}
	} 
	// 베팅 down 이미지를 누른 경우
	else if( (posX >= 650 && posX <= 672) && (posY >= 500 && posY <= 522) ) {
//		alert("다운 이미지 눌렀다!!");
		if(gameStatus == READY) {
			if( bet >= (betDefault * 2) ) {
				bet -= betDefault;
//				alert(bet);
			}	
		}
	}
	// more 버튼을 누른 경우
	else if( (posX >= 397 && posX <= 489) && (posY >= 530 && posY <= 562) ) {
//		alert("more Btn!!");
		if(gameStatus == CHOICE) {
			dblStatus = false;
			deal();
		}
	}
	// hold 버튼을 누른 경우
	else if( (posX >= 487 && posX <= 569) && (posY >= 530 && posY <= 562) ) {
//		alert("hold Btn!!");
		if(gameStatus == CHOICE) {
			dblStatus = false;
			playerDone();
		}
	}
	// double 버튼을 누른 경우
	else if( (posX >= 575 && posX <= 657) && (posY >= 530 && posY <= 562) ) {
//		alert("double Btn!!");
		if(gameStatus == CHOICE && dblStatus == false) {
			dblStatus = true;
			bet = bet * 2;
			if(bet >= point) {
				bet = point;
			}
		}
	}
	// start 버튼을 누른 경우
	else if( (posX >= 442 && posX <= 524) && (posY >= 566 && posY <= 598) ) {
//		alert("start Btn!!");
		if(gameStatus == READY) {
			dealStart();
		}
	}
	// new game 버튼을 누른 경우
	else if( (posX >= 531 && posX <= 613) && (posY >= 566 && posY <= 598) ) {
//		alert("new game!!");
		if(gameStatus == READY || gameStatus == END) {
			newGame();
		}
	}


	drawBet();
}

// 폰트색상을 세팅하는 함수
function setFontColor(fontColor) {
	ctx.fillStyle = fontColor;
}

// 폰트종류를 세팅하는 함수
function setFontFace(fontFace) {
	ctx.font = fontFace;
}

// 버튼들을 그려주는 함수
function drawButton() {
//	alert("DrawButton!!");
	ctx.clearRect(395, 530, 280, 70);
//	ctx.fillRect(395, 530, 280, 70);	
	for(i = 0; i < 5; i++) {
		// more, hold, double 이미지는 위쪽에 3개 나란히 그리고
		if(i % 4 != 0) {
			if(gameStatus == CHOICE) {
				ctx.drawImage(btnImg[i], (395 + (i-1)*90), 530, 82, 32);
			}
		} 
		// start, new game 이미지는 밑쪽에 2개 나란히 그린다.
		else {
			if(gameStatus == READY) {
				ctx.drawImage(btnImg[i], (440 + (i/4)*90), 565, 82, 32);
			} 
// 			else if(gameStatus == END) {
// 				ctx.drawImage(btnImg[4], (440 + (4/4)*90), 565, 82, 32);
// 				savePoint(point-bPoint);
// 			}
		}
	}
	if(gameStatus == END) {
		ctx.drawImage(btnImg[4], (440 + (4/4)*90), 565, 82, 32);
		savePoint(point-bPoint);
	}
	
}

// 타이틀을 그려주는 함수
function drawTitle() {
	setFontFace("40px SNAP ITC");
	setFontColor("VIOLET");
    ctx.textAlign = "start";
    ctx.fillText("BLACKJACK!!", 10, 40);
}

// 그림자를 세팅하는 함수
function drawShadow() {
	ctx.shadowColor = "rgba(220,220,220,0.8)";
    ctx.shadowOffsetX="5";
    ctx.shadowOffsetY= "5";
    ctx.shadowBlur="5";
}

// 점수 문구를 그려주는 함수
function drawScore() {
	setFontFace("30px Impact");
	
	setFontColor("pink");
    ctx.textAlign = "start";
    ctx.fillText("Com's Score : ", 440, 50);	

   	setFontColor("skyblue");
	ctx.textAlign = "start";
	ctx.fillText("Your Score : ", 80, 490);
}

// 컴퓨터의 점수를 그려주는 함수
function drawHScore() {
	setFontFace("30px Impact");
	setFontColor("pink");
	ctx.clearRect(605, 20, 60, 32);
//	ctx.fillRect(605, 20, 60, 32);
	ctx.fillText(housetotal, 610, 50);
}

// 플레이어의 점수를 그려주는 함수
function drawPScore() {
	setFontFace("30px Impact");
	setFontColor("skyblue");
	ctx.clearRect(230, 460, 60, 32);
//	ctx.fillRect(230, 460, 60, 32);
	ctx.fillText(playertotal, 240, 492);

	if(playertotal > 21) {
		gameOverflow();
	} else if(playertotal == 21) {
		blackJack();
	}
}

// 포인트를 그려주는 함수
function drawPoint() {
	setFontFace("30px Impact");
	setFontColor("olive");
	ctx.clearRect(350, 463, 290, 32);
//	ctx.fillRect(350, 463, 290, 32);
	ctx.fillText("Your Point : ", 360, 490);
	setFontColor("green");
	ctx.fillText(point, 520, 490);
	drawBet();
}

// 베팅금액을 그려주는 함수
function drawBet() {
//	alert("drawBet!!");
	setFontFace("30px Impact");
	setFontColor("olive");
	ctx.clearRect(360, 495, 250, 35);	
	ctx.fillText("Your Bet : ", 360, 520);	
	setFontColor("red");
	ctx.fillText(bet, 490, 520);
	// 게임상태가 READY 여서 베팅이 가능할때만 화살표를 그려라.
	ctx.clearRect(648, 468, 40, 63);
//	ctx.fillRect(648, 468, 40, 63);
	if(gameStatus == READY) {
		ctx.drawImage(upImg, 650, 470, 22, 22);
		ctx.drawImage(downImg, 650, 500, 22, 22);
	}
}

// 게임 준비
function dealReady() {
//	alert("dealReady()");
	gameStatus = READY;
	if(gameStatus == READY) {
		ctx.drawImage(cbackImg, playerxp, playeryp, cardw, cardh);
		playerxp = playerxp + 30;
		ctx.drawImage(cbackImg, playerxp, playeryp, cardw, cardh);
		playerxp = 100;
	}
	drawButton();
}

// 게임 시작
function dealStart() {
//	alert("dealStart()!!");
//	alert("playerxp : " + playerxp);
//	alert("playeryp : " + playeryp);
//	gameStatus = GAME;	
	gameStatus = CHOICE;
	playerhand[pi] = dealFromDeck(1);
	ctx.drawImage(playerhand[pi].picture, playerxp, playeryp, cardw, cardh);
	playerxp = playerxp + 30;
	pi++;
	househand[hi] = dealFromDeck(2);
	ctx.drawImage(cbackImg, housexp, houseyp, cardw, cardh);
	housexp = housexp + 20;
	hi++;
	playertotal = add_up_player();
//	alert("pScore : " + playertotal);	
	drawPScore();

	playerhand[pi] = dealFromDeck(1);
	ctx.drawImage(playerhand[pi].picture, playerxp, playeryp, cardw, cardh);
	playerxp = playerxp + 30;
	pi++;
	househand[hi] = dealFromDeck(2);
	ctx.drawImage(househand[hi].picture, housexp, houseyp, cardw, cardh);
	housexp = housexp + 20;
	hi++;

	playertotal = add_up_player();
//	alert("pScore : " + playertotal);	
	drawButton();
	drawPScore();	
	drawPoint();
}

function deal() {
//	alert("deal()!!");
	playerhand[pi] = dealFromDeck(1);
	ctx.drawImage(playerhand[pi].picture, playerxp, playeryp, cardw, cardh);
	playerxp = playerxp + 30;
	pi++;

	playertotal = add_up_player();
//	alert("pScore : " + playertotal);	
	drawPScore();
	
	if(more_to_house()) {
		househand[hi] = dealFromDeck(2);
		ctx.drawImage(househand[hi].picture, housexp, houseyp, cardw, cardh);
		housexp = housexp + 20;
		hi++;
	}
	drawPoint();
}

function more_to_house() {
//	alert("more_to_house()!!");
	var ac = 0;
	var i;
	var sumup = 0;

	for(i = 0; i < hi; i++) {
		sumup += househand[i].value;
		if(househand[i].value == 1) {
			ac++;
		}
	}
	if(ac > 0) {
		if((sumup + 10) <= 21) {
			sumup += 10;
		}
	}
	housetotal = sumup;
	if(sumup < 17) {
		return true;
	} else {
		return false;
	}
}

function dealFromDeck(who) {
//	alert("dealFromDeck()!!");	
	var card;
	var ch = 0;
	
	while((deck[ch].dealt > 0) && (ch < 51)) {
		ch++;
	}
	if(ch >= 51) {
		ctx.fillText("카드가 없습니다. 새로고침 하세요.", 200, 250);
		ch = 51;
	}
	deck[ch].dealt = who;
	card = deck[ch];	
	return card;
}

function buildDeck() {
//	alert("buildDeck()!!");
	var n;
	var si;
	var suitnames = ["c", "h", "s", "d"];
	var i;
	i = 0;
	var picname;
	var nums = ["a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"];
	for(si = 0; si < 4; si++) {
		for(n = 0; n < 13; n++) {
			picname = "../game/blackJack/" + nums[n] + suitnames[si] + ".png";
			deck[i] = new MCard(n+1, suitnames[si], picname);
			i++;
//			alert("Now Card Name is : " + picname);
		}
	}
}

function MCard(n, s, picname) {
	this.num = n;
	if(n > 10) {
		n = 10;
	}
	this.value = n;
	this.suit = s;
	this.picture = new Image();
	this.picture.src = picname;
	this.dealt = 0;
}

function add_up_player() {
//	alert("add_up_player()!!");
	var ac = 0;
	var i;
	var sumup = 0;

	for(i = 0; i < pi; i++) {
		sumup += playerhand[i].value;
		if(playerhand[i].value == 1) {
			ac++;
		}
	}
	if(ac > 0) {
		if((sumup + 10) <= 21) {
			sumup += 10;
		}
	}
	return sumup;
}

function playerDone() {
//	alert("playerDone()!!");
	while(more_to_house()) {
		househand[hi] = dealFromDeck(2);
		ctx.drawImage(cbackImg, housexp, houseyp, cardw, cardh);
		housexp = housexp + 20;
		hi++;
	}
	showHouse();
	checkScore();
	gameStatus = END;
	drawButton();
	drawPoint();
}

function showHouse() {
//	alert("showHouse()!!");
	var i;
	housexp = 500;
	for(i = 0; i < hi; i++) {
		ctx.drawImage(househand[i].picture, housexp, houseyp, cardw, cardh);
		housexp = housexp + 20;
	}
	drawHScore();
	drawPScore();
}

function checkScore() {
//	alert("checkScore()!!");
	playertotal = add_up_player();
	if(playertotal > 21) {
		if(housetotal > 21) {
			ctx.fillText("무승부!!", 80, 550);
			ctx.fillText("사용자와 딜러가 21점을 초과함.", 80, 580);
			minusPoint();
		} else {
			ctx.fillText("패배!!", 80, 550);
			ctx.fillText("사용자가 21점을 초과함.", 80, 580);
			minusPoint();
		}
	} else if(housetotal > 21) {
		ctx.fillText("승리!!", 80, 550);
		ctx.fillText("딜러가 21점을 초과함.", 80, 580);
		plusPoint();
	} else if(playertotal >= housetotal) {
		if(playertotal > housetotal) {
			ctx.fillText("승리!!", 80, 550);
			plusPoint();
		} else {
			ctx.fillText("무승부!!", 80, 550);
			minusPoint();
		}
	} else if(housetotal <= 21) {
		ctx.fillText("패배!!", 80, 550);
		minusPoint();
	} else {
		ctx.fillText("승리!!", 80, 550);
		ctx.fillText("딜러가 21점을 초과함", 80, 580);
		plusPoint();
	}	
}

function plusPoint() {
//	alert("plusPoint : " + bet);
	point += bet;
	bet = betDefault;
	drawPoint();
	drawBet();
}

function minusPoint() {
//	alert("minusPoint : " + bet);
	point -= bet;
	bet = betDefault;
	if(point <= 0) {
		point = 0;
		alert("포인트가 모두 소모되어 게임이 종료되었습니다.");
		gameStatus = ALLIN;
		savePoint(point-bPoint);
		return;
	}
	drawPoint();
	drawBet();
}

function newGame() {
//	alert("newGame()!!");
	if(point < betDefault) {
		alert("포인트가 없어서 게임을 실행할 수 없습니다.")
		alert("게임을 종료합니다!!")
		gameStatus = ALLIN;
		window.close();
		return;
	}
	ctx.clearRect(0, 0, cwidth, cheight);
	ctx.drawImage(backImg, 70, 60, 600, 400);
	pi = 0;
	hi = 0;
	playerxp = 100;
	housexp = 500;
	housetotal = 0;
	playertotal = 0;
	bet = betDefault;
	bPoint = point;
	dblStatus = false;
	shuffle();
	drawShadow();
	drawTitle();
	drawScore();
	drawPScore();
	drawPoint();
	drawButton();
	dealReady();
}

function blackJack() {
//	alert("blackJack()!!");
	setFontFace("25px Impact");
	ctx.textAlign = "start";
	ctx.fillText("와우!! BLACKJACK!!", 80, 550);
	ctx.fillText("축하합니다~!!", 80, 580);
	plusPoint();
	gameStatus = END;	
	drawButton();
}

function gameOverflow() {
//	alert("gameOverflow()!!");
	gameStatus = END;
	ctx.textAlign = "start";
	ctx.fillText("패배, 21초과!!", 80, 550);
	minusPoint();	
	drawButton();
}

function shuffle() {
//	alert("shuffle()!!");
	var i = deck.length - 1;
	var s;

	while(i > 0) {
		s = Math.floor(Math.random() * (i + 1));
		swapInDeck(s, i);
		i--;
	}
}

function swapInDeck(j, k) {
	var hold = new MCard(deck[j].num, deck[j].suit, deck[j].picture.src);
	deck[j] = deck[k];
	deck[k] = hold;
}

// 포인트 저장하기 (ajax)
function savePoint(point){
	//ajax call
	new net.ContentLoader("game_savePoint.action", "POST", onRead, onError, "point=" + point);
}

// 포인트 저장했을 때 실행 될 함수 (ajax)
function onRead(){		
}

// 포인트 저장에 실패했을 때 실행 될 함수 (ajax)
function onError(){
	alert("error:"+this.req.getAllResponseHeaders);
}

window.addEventListener("load", init, true);
</script>
</head>

<body>
<header> 	
</header>
<table border="1">
<tr>
	<td>
		<canvas id="canvas" width="750" height="600">
		이 브라우저는 HTML5의 canvas 요소를 지원하지 않습니다.
		다른 최신 버전의 브라우저에서 실행해 주세요.
		</canvas>
	</td>
</tr>
</table>

<footer>
만든이 : Kei
</footer>
</body>
</html>


