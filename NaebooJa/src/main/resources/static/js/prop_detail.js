$(function(){
   // 자산 [삭제] 버튼
   $(".btnDel").click(function(){
   console.log(this.value);
        let answer = confirm("삭제하시겠습니까?");
        if(answer){
            $("form[name='"+this.value+"']").submit();
        }
   });
});

// 자산 거래내역 닫기 버튼
function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
}

// 자산 거래내역 열기 버튼
function openNav(property_id) {
  document.getElementById("mySidebar").style.width = "900px";
    let today = new Date();
    let year = today.getFullYear(); // 년도
    let month = today.getMonth() + 1;  // 월
    let date = today.getDate();  // 날짜
    let day = today.getDay();  // 요일

    $.ajax({
        url: "/propertyDetail/propList?id=" + property_id + "&date=" + year + '-' + month + '-' + date,
        type: "GET",
        cache: false,
        success: function(data, status, xhr){
            if(status == "success"){
                console.log(xhr.responseText);  // response 결과 확인용

                // 서버쪽 에러 메세지 있는 경우
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }

                buildPropTrans(data);  // 화면 렌더링
            }
        },
    });
}

// 자산 내역창 출력
function buildPropTrans(result){
    const out = [];

    let date = new Date(result.date);

    let year = date.getFullYear(); // 년도
    let month = date.getMonth()+1;  // 월

    // 특정월의 첫째 날
    let firstDate = new Date(date.getFullYear(), date.getMonth(), 1);
    let firstDateString = firstDate.getFullYear() + '-' + ('0' + (firstDate.getMonth() + 1)).slice(-2) + '-' + ('0' + firstDate.getDate()).slice(-2);

    // 특정월의 마지막 날
    let lastDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    let lastDateString = lastDate.getFullYear() + '-' + ('0' + (lastDate.getMonth() + 1)).slice(-2) + '-' + ('0' + lastDate.getDate()).slice(-2);

    let incomes = 0;
    let outcomes = 0;
    let transfers = 0;

    result.data.forEach(test => {
    if(test.transaction_type == "수입") {incomes += test.money}
    if(test.transaction_type == "지출") {outcomes += test.money}
    if(test.transaction_type == "이체") {transfers += test.money}
    });

    // 1,000 반점 찍기
    let income = String(incomes).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    let outcome = String(outcomes).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    let transfer = String(transfers).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');

    let row = `
    <div class="detail-header">
                <div>
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
                </div>
                <div class="de-first">
                    <p>${result.propName}</p>
                </div>
                <div class="de-second">
                    <p onclick="changeMonth(${result.propNum}, '${year}-${month-1}-1')">⬅️</p>
                    <p>${year}.${month}</p>
                    <p onclick="changeMonth(${result.propNum}, '${year}-${month+1}-1')">➡️</p>
                </div>
            </div>
            <div class="use-month">
                <p>사용기간 : ${firstDateString} ~ ${lastDateString}</p>
            </div>
            <div class="property-card">
                <div class="in_money prop-type">
                    <span>수입</span>
                    <p>${income}원</p>
                </div>
                <div class="out_money prop-type">
                    <span>지출</span>
                    <p>${outcome}원</p>
                </div>
                <div class="transfer_money prop-type">
                    <span>이체</span>
                    <p>${transfer}원</p>
                </div>
            </div>
            <div>
    <table class="table table-hover">
                    <tbody style="display: block;
                        overflow-y: scroll;
                        height: 275px;">
    `

    // 첫번째 날 세팅을 위한 날짜 0
    let DefaultDate = 0;

    result.data.forEach(transaction => {
        let category = transaction.category;
        let id = transaction.id;
        let money = String(transaction.money).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        let transaction_type = transaction.transaction_type;
        let regdate = transaction.regDate;

        // 거래시간 구하기
        const dateObject = new Date(regdate);
        const timeString = dateObject.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit', hour12: true});

        // 거래 일자 구하기
        const dayString = dateObject.getDate();

        row += `
            <tr>
                <td>${dayString} / ${timeString}</td>
                <td>${category}</td>
                <td>
                   <div>
                        <p>${money}</p>
                        <p>${transaction_type}</p>
                    </div>
                </td>
            </tr>
            </div>
            `;
    });

    row += `
            </tbody>
        </table>
    `

    out.push(row);

    $("#mySidebar").html(out.join("\n"));
}  // end buildComment();

// 이전 월 자산 거래내역 버튼
function changeMonth(property_id, date) {
    $.ajax({
            url: "/propertyDetail/propList?id=" + property_id + "&date=" + date,
            type: "GET",
            cache: false,
            success: function(data, status, xhr){
                if(status == "success"){
                    console.log(xhr.responseText);  // response 결과 확인용

                    // 서버쪽 에러 메세지 있는 경우
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }

                    buildPropTrans(data);  // 화면 렌더링
                }
            },
        });
}