// 임시 데이터
const out = [];
// 데이터 가공
const calendarList = out.reduce(
                    (acc, v) =>
                      ({ ...acc, [v.date]: [...(acc[v.date] || []), v.money] })
                    , {}
                  );
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

const makeCalendar = (date, out) => {

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
    
    htmlDummy += `
      <div>
        ${i}
        <p>
          ${calendarList[date]?.join('</p><p>') || ''}
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
                console.log(data);
                console.log(str);

                // 서버쪽 에러 메세지 있는 경우
                if(data.status !== "OK"){
                    alert(data.status);
                        return;
                }
                const out = [];
                data.data.forEach(function(transaction){
                    out.push({
                        date: toStringByFormatting(new Date(transaction.regdate)),
                        money: transaction.money
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
                    htmlDummy += `
                      <div>
                        ${i}
                        <p>
                          ${calendarList[date]?.join('</p><p>') || ''}
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
}