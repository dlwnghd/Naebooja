// 해당 날짜의 거래 내역 출력
function buildTrans(result){
    const out = [];
    let totals = 0;
    let incomes = 0;
    let outcomes = 0;
    let transfers = 0;

    result.data.forEach(test => {
        if(test.transaction_type == "수입") {incomes += test.money;  totals += test.money}
        if(test.transaction_type == "지출") {outcomes += test.money; totals -= test.money}
        if(test.transaction_type == "이체") {transfers += test.money}
        });


    let total = String(totals).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    let income = String(incomes).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    let outcome = String(outcomes).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    let transfer = String(transfers).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');


    let row = `
    <div class="favorite-area">
                    <div class="transition-type" >
                        <div class="transition-total">
                            <span>통계</span><br>
                            <span class="money">${total}</span>
                        </div>
                        <div class="transition-income">
                            <span>수입</span><br>
                            <span class="money"> + ${income}</span>
                        </div>
                        <div class="transition-outcome">
                            <span>지출</span><br>
                            <span class="money"> - ${outcome}</span>
                        </div>
                        <div class="transition-transfer">
                            <span>이체</span><br>
                            <span class="money">${transfer}</span>
                        </div>
                    </div>

                    <hr>`
        row += `
            <div class="content" id="dailyTransaction">
                                <table class="table table-hover" bgcolor="#F4F4F7">
                                    <thead th="table-success">
                                    <tr>
                                        <th>ID</th>
                                        <th>날짜</th>
                                        <th>계좌</th>
                                        <th>거래</th>
                                        <th>분류</th>
                                        <th>내용</th>
                                        <th>금액</th>
                                    </tr>
                                    </thead>
                                    <tbody>
    `

    result.data.forEach(transaction => {
            let category = transaction.category;
            let id = transaction.id;
            let money = String(transaction.money).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
            let transaction_type = transaction.transaction_type;
            let regdate = transaction.regdate;
            let content = transaction.content;
            let property = transaction.property_id.name;

            row += `
                <tr>
                    <td>${id}</td>
                    <td>${regdate}</td>
                    <td>${property}</td>
                    <td>${transaction_type}</td>
                    <td>${category}</td>
                    <td>${content}</td>
                    <td>${money}</td>
                </tr>`;
        });

        row += `
                    </tbody>
                </table>
            </div>
        `

        out.push(row);
    $("#dailyTransaction").html(out.join("\n"));

}  // end buildComment();

$(function () {
    $("#datepicker").datepicker({
        dateFormat:"yy-mm-dd",
        dayNamesMin:["일","월","화","수","목","금","토"],
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        onSelect:function(d){
            alert(d+" 선택되었습니다");

            var arr=d.split("/");
            var year=arr[0];
            var month=arr[1];
            var day=arr[2];

            $("#year").text(year);
            $("#month").text(month);
            $("#day").text(day);

            //요일 가져오기
            //데이터를 먼저 가져오고 (숫자로 넘어옴)
            var date=new Date($("#datepicker").datepicker({dateFormat:"yy/mm/dd"}).val());
            //일요일 0~
            alert("date:"+date.getDay());

            week=new Array("일","월","화","수","목","금","토");
            $("#mydate").text(week[date.getDay()]);

            $.ajax({
                    url: "/transactionDetail/transacList?date=" + d,
                    type: "GET",
                    cache: false,
                    success: function(data, status, xhr){
                        if(status == "success"){
                            console.log(xhr.responseText);  // response 결과 확인용
                            console.log(data);


                            // 서버쪽 에러 메세지 있는 경우
                            if(data.status !== "OK"){
                                alert(data.status);
                                return;
                            }
                            buildTrans(data);  // 화면 렌더링

                        }
                    },
            });
        }
    });

});
