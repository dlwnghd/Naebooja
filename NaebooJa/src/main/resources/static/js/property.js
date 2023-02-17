let today = new Date();

let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜
let day = today.getDay();  // 요일

console.log("년도 : "+year);
console.log("월 : "+month);
console.log("날짜 : "+date);
console.log("요일 : "+day);