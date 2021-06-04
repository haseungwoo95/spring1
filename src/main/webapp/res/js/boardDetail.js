var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem = document.querySelector('#modal');
var cmtModFrmElem = document.querySelector('#cmtModFrm');

function enterInsCmt(){
	if(window.event.keyCode == 13){
		regCmt();
	}
}
function regCmt() { 
	var cmtVal = cmtFrmElem.cmt.value;
	var param = {
		iboard: cmtListElem.dataset.iboard,
		cmt: cmtVal
	};	
	regAjax(param);
}

function regAjax(param) {
	const init = {
		method: 'POST',
		body: JSON.stringify(param)
		};
		fetch('cmtInsSel', init)
		.then(function(res){
			return res.json();
		})	
		.then(function(myJson){
			console.log(myJson);
			switch(myJson.result){
				case 0:
					alert('등록 실패!');
				break;
				case 1:
					cmtFrmElem.cmt.value='';
					alert('등록 완료!');
					getListAjax();
				break;
			}
		})	
}
//서버 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax() {
	var iboard = cmtListElem.dataset.iboard;
	
	fetch('cmtInsSel?iboard=' + iboard)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson) {
		console.log(myJson);
		
		makeCmtElemList(myJson);
	});
}

function makeCmtElemList(data) {
	cmtListElem.innerHTML = '';
	var tableElem = document.createElement('table');
	var trElemTitle = document.createElement('tr');
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');
	
	thElemCtnt.innerText = '내용';
	thElemWriter.innerText = '작성자';
	thElemRegdate.innerText = '작성일';
	thElemBigo.innerText = '비고';
	
	trElemTitle.append(thElemCtnt);
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);
	
	tableElem.append(trElemTitle);

	cmtListElem.append(tableElem);
	
	var loginUserPk = cmtListElem.dataset.login_user_pk;
	
	data.forEach(function(item){
		var trElemItem = document.createElement('tr');
		var tdElemCtnt = document.createElement('td');
		var tdElemWriter = document.createElement('td');
		var tdElemRegdate = document.createElement('td');
		var tdElemBigo = document.createElement('td');
		
		tdElemCtnt.append(item.cmt);
		tdElemWriter.append(item.writerNm);
		tdElemRegdate.append(item.regdate);
		
		if(parseInt(loginUserPk) === item.iuser){
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');
			
			//삭제
			delBtn.addEventListener('click', function(){
				if(confirm('삭제하시겠습니까?')){
					delAjax(item.icmt);
				}
			});
			//수정
			modBtn.addEventListener('click', function(){
				//댓글 수정 모달창 띄우기
				openModModal(item);
			});
			
			delBtn.innerText = '삭제';
			modBtn.innerText = '수정';
			
			tdElemBigo.append(delBtn);
			tdElemBigo.append(modBtn);
		}
		trElemItem.append(tdElemCtnt);
		trElemItem.append(tdElemWriter);
		trElemItem.append(tdElemRegdate);
		trElemItem.append(tdElemBigo);
		
		tableElem.append(trElemItem);
		
	});
}

function delAjax(icmt){
	fetch('cmtDelUpd?icmt=' + icmt)
	.then(function(res){
		return res.json();
	})
	.then(function(data){ "{result: 0}"
		console.log(data);
		
		switch(data.result){
			case 0:
				alert('댓글 삭제를 실패하였습니다.');
			break;
			case 1:
				alert('댓글 삭제를 성공하였습니다.');
				getListAjax();
			break;
		}
	});
}
function modAjax(){
	var param = {
		icmt: cmtModFrmElem.icmt.value,
		cmt: cmtModFrmElem.cmt.value
	}
	const init = {
		method: 'POST',
		body: JSON.stringify(param)
		};
		fetch('cmtDelUpd', init)
		.then(function(res){
			return res.json();
		})	
		.then(function(myJson){
			console.log(myJson);
			switch(myJson.result){
				case 0:
					alert('댓글 수정 실패!');
				break;
				case 1:
					cmtFrmElem.cmt.value='';
					alert('댓글 수정 완료!');
					closeModModal();
					getListAjax();
				break;
			}
		});	
}
function openModModal({icmt, cmt}){
	cmtModModalElem.className = '';
	
	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.cmt.value = cmt;
}

function closeModModal(){
	cmtModModalElem.className = 'displayNone';
}

//이 파일이 임포트되면 함수 1회 호출
getListAjax();