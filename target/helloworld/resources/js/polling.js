/**
 * Created by aleksandrprendota on 23.04.17.
 */
var flag = 0;
$( document ).ready(function() {
    setInterval(function(){
        console.log("up!");
        $.ajax({ url: "#{cache.flagToUpdate}", success: function(data){
            //Update your dashboard gauge
            console.log("down!");
            if(flag != data){
                flag = data;
                location.reload();
            }
            salesGauge.setValue(data.value);
        }, dataType: "json"});
        console.log(flag);
    }, 3000);
});