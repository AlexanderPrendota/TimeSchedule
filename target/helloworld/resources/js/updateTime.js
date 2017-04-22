/**
 * Created by aleksandrprendota on 22.04.17.
 */


function clockTick() {
    var currentTime = new Date(),
        month = currentTime.getMonth() + 1,
        day = currentTime.getDate(),
        year = currentTime.getFullYear(),
        hours = currentTime.getHours(),
        minutes = currentTime.getMinutes(),
        seconds = currentTime.getSeconds();
    if (minutes < 10){
        minutes = "0" + minutes;
    }
    if (seconds < 10){
        seconds = "0" + seconds;
    }
    var text = month + "/" + day + "/" + year + ' ' + hours + ':' + minutes + ':' + seconds;
    document.getElementById('date').innerHTML = text;
    setInterval(clockTick, 1000);
}

