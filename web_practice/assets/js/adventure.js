window.onload = function() {

    init();


}

//Init function
function init()
{
    add_click_open_overlay();
    add_click_close_overlay();
}

// Event Handlers
function add_click_open_overlay()
{

    var list_of_images = $('.image_container');

    for (var i = 0; i < list_of_images.length; i++) {
        // img.addEventListener("click",test);
        list_of_images[i].addEventListener("click",open_image);
    }
}

function add_click_close_overlay()
{
    $('exit_bttn').addEventListener("click",close_overlay);
}
function open_image()
{
    var get_img = this.getElementsByTagName('img')[0];
    var img_src = get_img.getAttribute("src");
    var img_to_display_div = $('displayed_image');
    var img_to_display = $('displayed_image').getElementsByTagName('img')[0];

    open_overlay();

    var scale = window.innerHeight/get_img.naturalHeight * .95;
    var new_width = get_img.naturalWidth * scale;
    var new_height = get_img.naturalHeight * scale;
    // var win_height = window.innerHeight;

    img_to_display.setAttribute("src", img_src);

}
function open_overlay()
{
        var elem = $("photo_page");
        var body = document.getElementsByTagName('body');
        elem.style.visibility = "visible";
        elem.style.opacity = 1;
        body[0].style.overflow = "hidden";
        // console.log(elem.style.visibility);
}

function close_overlay()
{
    // var elem = $("photo_page");
    var body = document.getElementsByTagName('body');
    animate_opacity_close_overlay('photo_page');
    body[0].style.overflow = "auto";
}
function animate_opacity_close_overlay(page_overlay)
{
    var overlay = $(page_overlay);
    var id = setInterval(frame,30);
    var opac = overlay.style.opacity;

    function frame()
    {
        if (opac <= 0) {
            overlay.style.visibility = 'hidden';
            clearInterval(id);
        } else {
            opac -= .07;
            overlay.style.opacity = opac;
        }
    }

}

function test()
{
    console.log("CLICKED!");
}

function $(id)
{
    if(typeof(id) != "string"){
        return null;
    }

    if(id.charAt(0) == '.'){
        return document.getElementsByClassName(id.substr(1,id.length));
    }

    return document.getElementById(id);
}
