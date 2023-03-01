// 임시 데이터
const out = [];

//if (transactions != []){
//    transactions.data.forEach(function(transaction){
//        var money = transaction.money;
//        if (transaction.transaction_type == "수입"){
//            money = '+' + money;
//        }
//        else if (transaction.transaction_type == "지출"){
//            money = '-' + money;
//        }
//        else if (transaction.transaction_type == "이체 "){
//            money = money
//        }
//        out.push({
//            date: toStringByFormatting(new Date(transaction.regdate)),
//            money: money
//        });
//    });
//}

// 데이터 가공
const calendarList = out.reduce(
                    (acc, v) =>
                      ({ ...acc, [v.date]: [...(acc[v.date] || []), v.money] })
                    , {}
                  );

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
            <hr>
        </div>`
                    out.push(row);
    $("#calendarTransaction").html(out.join("\n"));
}

function leftPad(value) {
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}
function toStringByFormatting(source, delimiter = '-') {
    if(!(source instanceof Date)){
        return '';
    } else{
        const year = source.getFullYear();
        const month = leftPad(source.getMonth() + 1);
        const day = leftPad(source.getDate());

        return [year, month, day].join(delimiter);
    }
}

// pad method
Number.prototype.pad = function() {
  return this > 9 ? this : '0' + this;
}

const makeCalendar = (date) => {
  const currentYear = new Date(date).getFullYear();
  const currentMonth = new Date(date).getMonth() + 1;

  const firstDay = new Date(date.setDate(1)).getDay();
  const lastDay = new Date(currentYear, currentMonth, 0).getDate();

  const limitDay = firstDay + lastDay;
  const nextDay = Math.ceil(limitDay / 7) * 7;

  let htmlDummy = '';

  for (let i = 0; i < firstDay; i++) {
    htmlDummy += `<div class="noColor"></div>`;
  }

  for (let i = 1; i <= lastDay; i++) {
    const date = `${currentYear}-${currentMonth.pad()}-${i.pad()}`
    console.log(date);
    console.log(calendarList[date]);
    var transfer = 0;
    var income = 0;
    var outcome = 0;
    if( calendarList[date]){
        calendarList[date].forEach( test => {
                                if( Number.isInteger(test)){
                                    transfer += test;
                                }
                                else if (test[0] == "+"){
                                    income += parseInt(test.slice(1));
                                }
                                else if (test[0]== "-"){
                                    outcome += parseInt(test.slice(1));
                                }
                            });
    }

    htmlDummy += `
      <div>
        ${i}
        <p>
          ${transfer}<br>
          + ${income}<br>
          - ${outcome}<br>
        </p>
      </div>
    `;
  }

  for (let i = limitDay; i < nextDay; i++) {
    htmlDummy += `<div class="noColor"></div>`;
  }
  console.log("********************");
  console.log(calendarList);

  document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
  document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;
}


const date = new Date();

makeCalendar(date, []);

// 이전달 이동
document.querySelector(`.prevDay`).onclick = () => {
    makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    let str = [year, month, day].join('-');
    $.ajax({
        url: "/transactionDetail/transacListbyMonth?date=" + str,
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
                const out = [];
                data.data.forEach(function(transaction){
                    var money = transaction.money;
                    if (transaction.transaction_type == "수입"){
                        money = '+' + money;
                    }
                    else if (transaction.transaction_type == "지출"){
                        money = '-' + money;
                    }
                    else if (transaction.transaction_type == "이체 "){
                        money = money
                    }
                    out.push({
                        date: toStringByFormatting(new Date(transaction.regdate)),
                        money: money
                    });
                });
                const calendarList = out.reduce(
                                    (acc, v) =>
                                      ({ ...acc, [v.date]: [...(acc[v.date]
                                      || []), v.money] })
                                    , {}
                                  );
                buildTrans(data);
                const makeCalendar = (date) => {

                  const currentYear = new Date(date).getFullYear();
                  const currentMonth = new Date(date).getMonth() + 1;

                  const firstDay = new Date(date.setDate(1)).getDay();
                  const lastDay = new Date(currentYear, currentMonth, 0).getDate();

                  const limitDay = firstDay + lastDay;
                  const nextDay = Math.ceil(limitDay / 7) * 7;

                  let htmlDummy = '';

                  for (let i = 0; i < firstDay; i++) {
                    htmlDummy += `<div class="noColor"></div>`;
                  }

                  for (let i = 1; i <= lastDay; i++) {
                    const date = `${currentYear}-${currentMonth.pad()}-${i.pad()}`
                    console.log(date);
                    console.log(calendarList[date]);
                    var transfer = 0;
                    var income = 0;
                    var outcome = 0;
                    if( calendarList[date]){
                        calendarList[date].forEach( test => {
                                                if( Number.isInteger(test)){
                                                    transfer += test;
                                                }
                                                else if (test[0] == "+"){
                                                    income += parseInt(test.slice(1));
                                                }
                                                else if (test[0]== "-"){
                                                    outcome += parseInt(test.slice(1));
                                                }
                                            });
                    }

                    htmlDummy += `
                      <div>
                        ${i}
                        <p>
                          ${transfer}<br>
                          + ${income}<br>
                          - ${outcome}<br>
                        </p>
                      </div>
                    `;
                  }

                  for (let i = limitDay; i < nextDay; i++) {
                    htmlDummy += `<div class="noColor"></div>`;
                  }
                  console.log("********************");
                  console.log(calendarList);

                  document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
                  document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;
                }

                makeCalendar(new Date(date.setMonth(date.getMonth())));

            }
        },
    });
}

// 다음달 이동
document.querySelector(`.nextDay`).onclick = () => {
makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    let str = [year, month, day].join('-');
    $.ajax({
        url: "/transactionDetail/transacListbyMonth?date=" + str,
        type: "GET",
        cache: false,
        success: function(data, status, xhr){
            if(status == "success"){
                console.log(xhr.responseText);  // response 결과 확인용
                console.log(data);
                console.log(str);

                // 서버쪽 에러 메세지 있는 경우
                if(data.status !== "OK"){
                    alert(data.status);
                        return;
                }
                const out = [];
                data.data.forEach(function(transaction){
                    var money = transaction.money;
                    if (transaction.transaction_type == "수입"){
                        money = '+' + money;
                    }
                    else if (transaction.transaction_type == "지출"){
                        money = '-' + money;
                    }
                    else if (transaction.transaction_type == "이체 "){
                        money = money
                    }
                    out.push({
                        date: toStringByFormatting(new Date(transaction.regdate)),
                        money: money
                    });
                });
                console.log("-----out------");
                console.log(out);
                const calendarList = out.reduce(
                                    (acc, v) =>
                                      ({ ...acc, [v.date]: [...(acc[v.date]
                                      || []), v.money] })
                                    , {}
                                  );
                buildTrans(data);
                const makeCalendar = (date) => {

                  const currentYear = new Date(date).getFullYear();
                  const currentMonth = new Date(date).getMonth() + 1;

                  const firstDay = new Date(date.setDate(1)).getDay();
                  const lastDay = new Date(currentYear, currentMonth, 0).getDate();

                  const limitDay = firstDay + lastDay;
                  const nextDay = Math.ceil(limitDay / 7) * 7;

                  let htmlDummy = '';

                  for (let i = 0; i < firstDay; i++) {
                    htmlDummy += `<div class="noColor"></div>`;
                  }

                  for (let i = 1; i <= lastDay; i++) {
                    const date = `${currentYear}-${currentMonth.pad()}-${i.pad()}`
                    console.log(date);
                    console.log(calendarList[date]);
                    var transfer = 0;
                    var income = 0;
                    var outcome = 0;
                    if( calendarList[date]){
                        calendarList[date].forEach( test => {
                                                if( Number.isInteger(test)){
                                                    transfer += test;
                                                }
                                                else if (test[0] == "+"){
                                                    income += parseInt(test.slice(1));
                                                }
                                                else if (test[0]== "-"){
                                                    outcome += parseInt(test.slice(1));
                                                }
                                            });
                    }

                    htmlDummy += `
                      <div>
                        ${i}
                        <p>
                          ${transfer}<br>
                          + ${income}<br>
                          - ${outcome}<br>
                        </p>
                      </div>
                    `;
                  }

                  for (let i = limitDay; i < nextDay; i++) {
                    htmlDummy += `<div class="noColor"></div>`;
                  }
                  console.log("********************");
                  console.log(calendarList);

                  document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
                  document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;
                }

                makeCalendar(new Date(date.setMonth(date.getMonth())));

            }
        },
    });
}