$( function() {
        $( "#datepicker" ).datepicker({});
        $( "#ui-datepicker-div" ).click(function(){
            console.log("here");
//            var date =new Date($( "#datepicker" ).datepicker({}).val());
             var date=new Date($("#datepicker").datepicker({dateFormat:"yy/mm/dd"}).val());
            console.log(date);
        });
});

//console.log(document.getElementByClassName(".ui-datepicker-div > table > tbody"));
//
//document.querySelector("#ui-datepicker-div > table > tbody > tr:nth-child(1) > td:nth-child(4)")

