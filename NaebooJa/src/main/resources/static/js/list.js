$(function(){
    $("[name='pageRows']").change(function(){
        var frm = $("[name='frmPageRows']")
        frm.attr("method", "POST")
        frm.attr("action", "pageRows")
        frm.submit();
    });
});