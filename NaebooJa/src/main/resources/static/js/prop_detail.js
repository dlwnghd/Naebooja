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


