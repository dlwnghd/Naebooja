$(function(){
    // TODO
    // 검증코드가 있어야 한다.

    // [추가] 버튼 누르면 될 첨부파일 추가 가능
    var i = 0;
    $("#btnAdd").click(function(){
       $("#files").append(`
               <div class="input-group mb-2">
                   <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
                   <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
               </div>`);
       i++;
    });

    // Summernote 추가
    $('#content').summernote({
        height: 300,
         lang: "ko-KR"
       });
});