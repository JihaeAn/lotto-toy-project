$(function () {
    searchWinningNum();
    getMostNumStats();
})

// 역대 당첨 번호 검색
function searchWinningNum(drawNum) {

    $('#searchForm').on('submit', function(event) {
        event.preventDefault(); // 폼의 기본 제출 동작을 방지

        const drawNum = $('#drawNum').val();
        console.log("drawNum 선택했는지, ", drawNum);
        if (drawNum === null || drawNum === 0 || drawNum === "") {
            alert('회차를 입력해주세요.');
            return false;
        }

        $.ajax({
            url: `/lotto/get/lottery?drawNum=${drawNum}`,
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {

                // 아직 추첨하지 않은 회차를 입력했을 경우
                if (data === "") {
                    alert('아직 추첨하지 않은 회차입니다.');
                    $("#drawNum").val("").focus(); // 지우고 포커스
                    return false;
                }
                console.log("역대 당첨 번호 검색 ", data)

                // 받아온 데이터로 HTML 요소 업데이트
                $("#drawNumInfo").html(`<strong>${data.drawNum}회 당첨결과</strong> (${data.crtDt} 추첨)`);
                $("#winningNum1").text(data.winningNum1);
                $("#winningNum2").text(data.winningNum2);
                $("#winningNum3").text(data.winningNum3);
                $("#winningNum4").text(data.winningNum4);
                $("#winningNum5").text(data.winningNum5);
                $("#winningNum6").text(data.winningNum6);
                $("#bonusNum").text(data.bonusNum);
            },
            error: function (error) {
                console.log("오류 발생 ", error);
            }
        });
    });
}

// 제일 많이 나온 숫자 통계 가져오기
function getMostNumStats() {
    $.ajax({
        url: '/lotto/get/Stats',
        type: 'GET',
        contentType: 'application/json',
        success: function (data) {

            data.forEach((list, index) => {
                if (index === 0) {
                    $("#number1").text(list.number);
                    $("#count1").text(list.count + "회");
                }
                if (index === 1) {
                    $("#number2").text(list.number);
                    $("#count2").text(list.count + "회");
                }
                if (index === 2) {
                    $("#number3").text(list.number);
                    $("#count3").text(list.count + "회");
                }
                if (index === 3) {
                    $("#number4").text(list.number);
                    $("#count4").text(list.count + "회");
                }
                if (index === 4) {
                    $("#number5").text(list.number);
                    $("#count5").text(list.count + "회");
                }
                if (index === 5) {
                    $("#number6").text(list.number);
                    $("#count6").text(list.count + "회");
                }
            })
        },
        error: function (error) {
            console.log("오류 발생 ", error);
        }
    });
}

