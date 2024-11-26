// 지도 객체를 전역 변수로 선언
let map = null;

$(function (){
    getStoreList();

    // 모달 닫기 버튼 누를 떄
    $(".close-btn").click(function () {
        $("#modal").css("display", "none");
    });

    // .store-btn 클릭 시 주소를 통해 지도 표시
    $(document).on("click", ".store-btn", function() {
        let address = $(this).closest(".store-item").find(".store-address").text();

        // 모달 열기(이거의 위치가 매우 중요. 이렇게 DOM이 준비되었을 때 모달을 띄우고 지도를 초기화해야 함)
        $("#modal").css("display", "flex");
        viewStoreMap(address);
    });
})

function getStoreList() {
    $.ajax({
        url: '/store/get/storeList',
        method: 'GET',
        contentType: 'application/json',
        success: function (data) {

            data.forEach(function (item) {

                const store = `
                    <li class="store-item">
                    <div>
                        <div class="store-name">${item.storeName}</div>
                        <div class="store-address">${item.address}</div>
                    </div>
                        <img class="store-btn" src="/image/location.png" alt="지도 아이콘" />
                    </li>                
                `;

                $(".store-list").append(store);
                $("#store-info").text(`${item.drawNum}회 1등 배출 판매점`)
            })
        },
        error: function (error) {
            console.log("error", error);
        }
    })
}

// 지도 모달로 띄우는 메서드
function viewStoreMap(address) {
    console.log("address", address);

    // 선택한 주소의 위도 경도 받아오기
    $.ajax({
        url: `/api/kakao/search/address/${address}`,
        method: 'GET',
        success: function (data) {
            console.log("data", data)
            let y = data.y;
            let x = data.x;

            // 카카오맵이 로드된 후에 실행
            kakao.maps.load(function() {
                // 지도 컨테이너 설정
                let container = document.getElementById("map");

                // 지도 객체가 존재하면 초기화
                if (map) {
                    map.setCenter(new kakao.maps.LatLng(y, x));  // 위치만 갱신
                    map.setLevel(3);
                } else {
                    // 지도 객체가 없으면 새로 생성
                    let options = {
                        center: new kakao.maps.LatLng(y, x),
                        level: 3
                    };
                    map = new kakao.maps.Map(container, options);
                }

                // 마커 추가
                var marker = new kakao.maps.Marker({
                    position: new kakao.maps.LatLng(y, x)
                });
                marker.setMap(map);

            });
        },
        error: function(error) {
            console.log("error", error);
            alert("주소를 찾을 수 없습니다.");
        }
    });
}


