window.onload = function () {
    var girişMenu = document.getElementById('buttons');
    var exit = document.getElementById('exit');
    girişMenu.addEventListener("click", open);
    exit.addEventListener("click", exits);

};

function open() {
    var girişSayfasi = document.getElementById('girisSayfasi');
    girişSayfasi.style.display = "flex";
}


function exits() {
    var girişSayfasi = document.getElementById('girisSayfasi');
    girişSayfasi.style.display = "none";
}


