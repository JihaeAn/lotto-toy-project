// 사용자 당첨 내역을 확인하기 위한 회차 (최초 변수 선언)
let drawNum;

$(function (){
    // 이 메서드 안에서 getUserWinningRecord()도 돎
    getLatestLottery();
    getDrawNumList();
})

// 번호 생성 메서드
function generateNumbers() {
    // id가 generated-numbers인 태그를 가지고 온다.
    // 추후 부모 div가 될 거임
    const numbersContainer = document.getElementById('generated-numbers');
    // 번호 재 생성 시 원래 값을 지워주기 위함
    numbersContainer.innerHTML = '';

    // 번호 중복 안되도록 Set 사용하고 랜덤 숫자 넣어줌
    const numbers = new Set();
    while(numbers.size < 6) {
        numbers.add(Math.floor(Math.random() * 45) + 1);
    }

    // 정렬
    const sortedNumbers = Array.from(numbers).sort((a, b) => a - b);
    // 서버로 보낼 데이터(객체) 생성 (lottoNum1 이런식으로 key 값 보내주기 위해서 만듦. 여기서 키값은 서버에서 필드 값임)
    const lottoNums = {}

    sortedNumbers.forEach((number, index) => {
        setTimeout(() => {
            // div 태그 생성
            const div = document.createElement('div');
            // div의 class 이름을 number로 설정
            div.className = 'number';
            // div의 text 값에 number 설정
            div.textContent = number;
            // 서버에 보낼 데이터 가공 ex) lottoNum1 : 12
            lottoNums[`lottoNum${index + 1}`] = number;
            // 숫자에 따른 색상 설정
            div.classList.add(getColorClass(number));

            // numbersContainer(generated-numbers가 id인 태그)라는 변수에 자식 태그 설정
            numbersContainer.appendChild(div);

            // 비동기 방식이기 때문에 sendNumbersToServer 메서드를 밖으로 뺴면
            // 모든 숫자가 forEach 돌기 전에 sendNumbersToServer 실행이 되므로
            // 현재 인덱스를 확인 후 마지막 순번일 때 sendNumbersToServer 메서드를 실행하게 함

            // (인덱스가 마지막인 경우)마지막 숫자를 추가한 후 서버로 전송
            if (index === sortedNumbers.length - 1) {
                sendNumbersToServer(lottoNums);
            }
        }, index * 200);
    });
}

// 생성된 번호 DB 저장 메서드
function sendNumbersToServer(lottoNums) {
    console.log("number : ", lottoNums);
    fetch('/user-lotto/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(lottoNums)
    })
}

// 번호 색상 입히는 메서드
function getColorClass(number) {
    if (number <= 10) return 'yellow';
    if (number <= 20) return 'blue';
    if (number <= 30) return 'red';
    return 'gray';
}

// 최근 당첨 번호 가져오는 메서드
function getLatestLottery() {

    $.ajax({
        url: '/lotto/get/latest-lottery',
        method: 'GET',
        contentType: 'application/json',
        success: function(data) {
            console.log(data); // 값 확인
            const prize = new Intl.NumberFormat().format(data.firstPrizeAmount);

            // 받아온 데이터로 HTML 요소 업데이트
            $("#drawNum").html(`<strong>${data.drawNum}회 당첨결과</strong> (${data.crtDt} 추첨)`);
            $("#winningNum1").text(data.winningNum1);
            $("#winningNum2").text(data.winningNum2);
            $("#winningNum3").text(data.winningNum3);
            $("#winningNum4").text(data.winningNum4);
            $("#winningNum5").text(data.winningNum5);
            $("#winningNum6").text(data.winningNum6);
            $("#bonusNum").text(data.bonusNum);
            $("#firstPrizeAmount").text(`1등 당첨금: ${prize}원`);

            drawNum = data.drawNum;

            // async await 실패해서 여기다 넣어서 해결했음
            getUserWinningRecord(drawNum);
        },
        error: function(error) {
            console.error("오류 발생:", error);
        }
    });
}

// 사용자 당첨 내역 가져오는 메서드
async function getUserWinningRecord(drawNum) {

    const list = await $.ajax({
        url: `/user-lotto/get?drawNum=${drawNum}`,
        method: 'GET',
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
    }
    });

    // 각 user-record를 추가할 부모 user-record-info 가져오기
    const container = $(".user-record-info");
    // const container = document.querySelector(".user-record-info");

    for (let data of list) {

        // 감싸는 div 생성
        const userRecordDiv = document.createElement('div');
        userRecordDiv.className = 'user-record';

        userRecordDiv.innerHTML = `
        <p><strong>${data.drawNum}회 ${data.lottoRank}등</strong> by ${data.userSeq} (${data.crtDt})</p>
        <div class="user-numbers">
            <div class="number user-number yellow">${data.lottoNum1}</div>
            <div class="number user-number yellow">${data.lottoNum2}</div>
            <div class="number user-number yellow">${data.lottoNum3}</div>
            <div class="number user-number blue">${data.lottoNum4}</div>
            <div class="number user-number red">${data.lottoNum5}</div>
            <div class="number user-number gray">${data.lottoNum6}</div>
        </div>
    `;
        container.append(userRecordDiv);
    }
}

async function getDrawNumList() {

    // selectBox에 쓸 회차 목록 가져오기
    await $.ajax({
        url: `/drawNum/get/list`,
        method: 'GET',
        contentType: 'application/json',
        success: function (data) {
            const selectBox = $("#draw-selection");
            selectBox.empty();

            data.forEach(function (drawNum) {
                const option = $('<option></option>').val(drawNum).text(drawNum);
                selectBox.append(option);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    })

    // 사용자가 selectBox를 통해 drawNum 값을 변경했을 때
    $("#draw-selection").change(async function() {

        // 원래 있던 데이터는 지워야 함
        const container = $(".user-record-info");
        await container.empty();

        drawNum = $(this).val(); // 선택된 값으로 drawNum 업데이트
        await getUserWinningRecord(drawNum); // 변경된 drawNum으로 다시 호출
    });
}
