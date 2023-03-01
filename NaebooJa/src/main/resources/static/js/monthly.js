//// 위에 날짜(년 - 월) 바꿔주기
//function buildDate(date){
//    const result = [];
//    const dateObj = new Date(date);
//    const year = dateObj.getFullYear();
//    const month = dateObj.toLocaleString("en-US", { month: "long" });
//    let row = `
//        <div class="btn prevMonth">
//            <p> < </p>
//        </div>
//        <span id="setYear">${year}</span>
//        <span id="setMonth">${month}</span>
//        <div class="btn nextMonth">
//            <p> > </p>
//        </div>
//    `
//    result.push(row);
//    $("#checkmonth").html(result.join("\n"));
//}

// 해당 월의 거래 내역 출력
function buildTrans(result, date){
    const out = [];
    const dateObj = new Date(date);
    const year = dateObj.getFullYear();
    const month = dateObj.toLocaleString("en-US", { month: "long" });
    let row = `
        <div class="show-duration">
            <div class="daily" onclick="location.href='/transaction/daily'">
                <p>일 별</p>
            </div>
            <div class="monthly">
                <p>월 별</p>
            </div>

            <div>
                <div class="btn prevMonth">
                    <p> < </p>
                </div>
                <span id="setYear">${year}</span>
                <span id="setMonth">${month}</span>
                <div class="btn nextMonth">
                    <p> > </p>
                </div>
            </div>

        </div>

    `
//    setDateList.push(daterow);
//    $("#checkmonth").html(setDateList.join("\n"));
//
//    const out = [];
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

    row += `
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
    $("#monthlyTransaction").html(out.join("\n"));

}  // end buildComment();

document.querySelector(`.prevMonth`).onclick = () => {
    const year = document.getElementById("setYear").innerText;
    const month = parseInt(document.getElementById("setMonth").innerText) -1 ;
    let str = [year, month,1].join('-');
    console.log(str);
    $.ajax({
            url: "/transactionDetail/transacListbyMonth?date=" + str,
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
                    console.log(str);

                    buildTrans(data, str);  // 화면 렌더링

                }
            },
    });
}

document.querySelector(`.nextMonth`).onclick = () => {

    const year = document.getElementById("setYear").innerText;
    const month = parseInt(document.getElementById("setMonth").innerText)+1;
    console.log(year, month);
    let str = [year, month,1].join('-');
    $.ajax({
            url: "/transactionDetail/transacListbyMonth?date=" + str,
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
                    buildTrans(data, str);  // 화면 렌더링

                }
            },
    });
}