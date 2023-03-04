$(function (){

$('input[type="radio"][id="cs_channel_etc"]').on('click', function(){
  var chkValue = $('input[type=radio][id="cs_channel_etc"]:checked').val();
  if(chkValue){
             $('#etc_view').css('display','none');
  }else{
             $('#etc_view').css('display','block');
  }

  });

});
