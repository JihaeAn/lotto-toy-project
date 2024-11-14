$(function () {
    searchWinningNum();
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
