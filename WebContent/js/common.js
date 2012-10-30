
// 문자열을 입력받아 숫자, 영문자가 아니면 false를 리턴한다.
function checkEngChar(checkString){
		for(var index in checkString){
			var temp = checkString[index]; 
			if(temp >= '0' && temp <= '9')
			continue;
		else if(temp >= 'A' && temp <= 'Z')
			continue;
		else if(temp >= 'a' && temp <= 'z')
			continue;
		else
			return false;
	}
	return true;
}

// 문자열을 입력받아 특수문자가 아니면 false를 리턴한다.
function checkSpChar(checkString){
	for(var index in checkString){
		var temp = checkString[index]; 
		if(temp >= '!' && temp <= '/')
			continue;
		else if(temp >= ':' && temp <= '@')
			continue;
		else if(temp >= '[' && temp <= '`')
			continue;
		else if(temp >= '{' && temp <= '~')
			continue;
		else
			return false;
	}
	return true;
}
	

// 문자열의 길이가 0이상이고 min 이상, max 이하일 경우에 true를 리턴한다.
function checkLength(checkString, min, max){
	var checkStringLength = checkString.length;
	if(checkStringLength >= 0 && checkStringLength >= min && checkStringLength <= max){
		return true;
	}
	return false;			
}