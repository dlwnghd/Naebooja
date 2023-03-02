
// 통계 그래프 깔기(지출)
$.ajax({
    url: "/statisticDetail/statList",
    type: "GET",
    cache: false,
    success: function(data, status, xhr){
        if(status == "success"){
//            console.log(xhr.responseText);  // response 결과 확인용
            // 서버쪽 에러 메세지 있는 경우
            if(data.status !== "OK"){
                alert(data.status);
                return;
            }
            console.log(data);

            let outcomeCategoryList = {};

            data.data.forEach(test => {
                // 지출 카테고리에 있다면
                if(!(test.category in outcomeCategoryList)){
                    outcomeCategoryList[test.category] = test.money;
                }else {
                    outcomeCategoryList[test.category] += test.money;
                }
            });

            const arr = Object.entries(outcomeCategoryList).map(([key, value]) => [key, value, false]);
            console.log(arr);

            // 그래프 만들기
            Highcharts.chart("graph_container", {
              title: {
                text: "지출 내역",
              },

              series: [
                {
                  type: "pie",
                  allowPointSelect: true,
                  keys: ["name", "y", "selected", "sliced"],
                  data: arr,
                  showInLegend: true,
                },
              ],
            }).setSize(600, 600);
        }
    },
});

// 통계 그래프 깔기(수입)
$.ajax({
    url: "/statisticDetail/statList_income",
    type: "GET",
    cache: false,
    success: function(data, status, xhr){
        if(status == "success"){
//            console.log(xhr.responseText);  // response 결과 확인용
            // 서버쪽 에러 메세지 있는 경우
            if(data.status !== "OK"){
                alert(data.status);
                return;
            }
            console.log(data);

            let incomeCategoryList = {};

            data.data.forEach(test => {
                // 수입 카테고리에 없다면
                if(!(test.category in incomeCategoryList)){
                    incomeCategoryList[test.category] = test.money;
                }else {
                    incomeCategoryList[test.category] += test.money;
                }
            });

            const arr = Object.entries(incomeCategoryList).map(([key, value]) => [key, value, false]);
            console.log(arr);

            Highcharts.chart("graph_container_test", {
                          title: {
                            text: "수입 내역",
                          },

                          series: [
                            {
                              type: "pie",
                              allowPointSelect: true,
                              keys: ["name", "y", "selected", "sliced"],
                              data: arr,
                              showInLegend: true,
                            },
                          ],
                        }).setSize(600, 600);
        }
    },
});