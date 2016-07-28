console.log(document.getElementsById('main_page'));
function test(){
    console.log($('main_page'));
}

function $(id)
{
    return document.getElementById(id)
}
// console.log($('main_page'));

$('img_1').addEventListener("click",test)
