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
    $.ajax({
        url: "/propertyDetail/propList?id=" + property_id,
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

function buildPropTrans(result){
    const out = [];

    console.log(result);

    let row = `
    <div class="detail-header">
                <div>
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
                </div>
                <div class="de-first">
                    <p>${result.propName}</p>
                </div>
                <div class="de-second">
                    <p><</p>
                    <p>2023.02</p>
                    <p>></p>
                </div>
            </div>
            <div class="use-month">
                <p>사용기간 : 23.02.01 ~ 23.02.28</p>
            </div>
            <div class="property-card">
                <div class="in_money prop-type">
                    <span>입금</span>
                    <p>0</p>
                </div>
                <div class="out_money prop-type">
                    <span>출금</span>
                    <p>0</p>
                </div>
                <div class="transfer_money prop-type">
                    <span>이체</span>
                    <p>0</p>
                </div>
                <div class="rest_money prop-type">
                    <span>누적잔액</span>
                    <p>0</p>
                </div>
            </div>
            <div>
    <table class="table table-hover">
                    <thead class="table-success transac-head" style="display: block;">
                    <th>
                        <div>
                            <p>15</p>
                            <p>수요일</p>
                            <p>2023.02</p>
                        </div>
                    </th>
                    <th>2000</th>
                    <th>12000</th>
                    </thead>
                    <tbody style="display: block;
                        overflow-y: scroll;
                        height: 275px;">
    `

    result.data.forEach(transaction => {
        let category = transaction.category;
        let id = transaction.id;
        let money = transaction.money;
        let transaction_type = transaction.transaction_type;
        let regdate = transaction.regDate;

        row += `
            <tr>
                <td>${category}</td>
                <td>
                   <div>
                        <p>${money}</p>
                        <p>${transaction_type}</p>
                    </div>
                </td>
                <td>${regdate}</td>
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