// 임시 데이터
const data = [
  { date: '2023-02-03', content: '테스트1' },
  { date: '2023-02-03', content: '테스트2' },
  { date: '2023-02-15', content: '테스트3' },
  { date: '2023-02-26', content: '테스트4' },
  { date: '2023-02-21', content: '테스트5' },
];

// 데이터 가공
const calendarList = data.reduce(
  (acc, v) => 
    ({ ...acc, [v.date]: [...(acc[v.date] || []), v.content] })
  , {}
);

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
  
  document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
  document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;
}

const date = new Date();

makeCalendar(date);

// 이전달 이동
document.querySelector(`.prevDay`).onclick = () => {
makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
}

// 다음달 이동
document.querySelector(`.nextDay`).onclick = () => {
makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
}