
var removeList = new Array();

function removeOrResetImage(id, name){

    var input = document.getElementById("removeList");

    var btn = document.getElementById("btn"+id);
    var nameBtn = btn.innerText;

    if(nameBtn == "Supprimer") {
        var img = document.getElementById("photo"+id);
        img.style.filter = "grayscale(100%)";
        btn.classList.remove('btn-danger');
        btn.classList.add('btn-warning');
        btn.innerHTML = "Annuler";
        removeList.push(name);
    }else if(nameBtn == "Annuler") {
        var img = document.getElementById("photo"+id);
        img.removeAttribute("style");
        btn.classList.remove('btn-warning');
        btn.classList.add('btn-danger');
        btn.innerHTML = "Supprimer";
        removeList.splice( removeList.indexOf(name), 1 );
    }

    input.value = removeList.toString();
}