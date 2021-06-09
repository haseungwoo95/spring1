var frmElem = document.querySelector('#frm');
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm;
var chkUidResultElem = frmElem.querySelector('#chkUidResult');
var btnChkIdElem = frmElem.btnChkId; //중복ID체크 버튼
btnChkIdElem.addEventListener('click', function(){
	idChkAjax(uidElem.value);
});

function idChkAjax(uid){
	console.log(uid);
	
	
	fetch('/user/idChk?uid=' + uid)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		switch(myJson.result){
		case 0:
		chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.';
		break;
		case 1:
		chkUidResultElem.innerText = '이 아이디는 사용할 수 없습니다.';
		break;
		}
	})
}


function frmChk() {
	//이상이 생기면 return false;	
	var uidVal = uidElem.value; //2자 이하면 리턴 false
	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	var unmVal = unmElem.value;
	
	if(uidVal.length < 3){
		if(uidVal.length == 0){
			alert('아이디를 작성해 주세요');
		} else{
			alert('아이디는 3자 이상 작성해 주세요');
		}
		return false;
	}
		
	if(upwVal.length < 4){
		if(upwVal.length == 0){
			alert('비밀번호를 작성해 주세요');
		} else{
			alert('비밀번호는 4자 이상 작성해 주세요');
		}
		return false;
	} else if(upwVal !== chkUpwVal){
		alert('비빌번호를 확인해 주세요');
		return false;
	}
	
	if(unmVal.length < 2){
		alert('이름은 2자 이상 작성해 주세요');
	}
}