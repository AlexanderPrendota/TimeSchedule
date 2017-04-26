/**
 * Created by aleksandrprendota on 23.04.17.
 */
$( document ).ready(function() {
    var check = 0;
    setInterval(function(){
        var flag = "#{cache.flagToUpdate}";
        console.log(flag);
        if (check != flag){
            check = flag;
            location.reload();
        }
    }, 5000);
});