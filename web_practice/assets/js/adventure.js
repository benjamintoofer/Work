window.onload = function() {

    init();


}
//Global variables
var current_image_index;
var list_of_images;
var timeout;

//Init function
function init()
{
    //Add Event Listeners
    add_click_open_overlay();
    add_click_close_overlay();
    add_move_mouse_overlay();
    add_click_next_image();
}

// Event Handlers
function add_click_next_image(){
    $('right_bttn').addEventListener("click",next_image);
}
function add_click_open_overlay()
{

    list_of_images = $('.image_container');

    for (var i = 0; i < list_of_images.length; i++) {
        // img.addEventListener("click",test);
        list_of_images[i].addEventListener("click",open_image);
    }
}

function add_click_close_overlay()
{
    $('exit_bttn').addEventListener("click",close_overlay);
}
function add_move_mouse_overlay()
{
    // $('photo_page').addEventListener("mousemove",show_button);
    $('photo_page').addEventListener("mousemove",show_button);
}

function next_image()
{
    console.log(current_image_index);
    var curr = $('displayed_image_container');
    var prev = $('prev_image_container');

    curr.setAttribute('class','move_right');
    var cont_new = $('.move_right');
    var duration = Number(window.getComputedStyle(curr).transitionDuration.split("s")[0]);
    console.log(window.getComputedStyle(cont_new[0]).transitionDuration);
    console.log(duration);
    clearTimeout(timeout);
    timeout = setTimeout(function(){
        curr.setAttribute('class','');
    }, 1000 * duration);
}

function show_button(event)
{
    var bttn;
    var other_bttn;
    //Right Side
    if(event.clientX > window.innerWidth/2){
        bttn = $("right_bttn");
        other_bttn = $("left_bttn");
    }else{
        bttn = $("left_bttn");
        other_bttn = $("right_bttn");
    }

    bttn.setAttribute('class','bttn arrow_bttn mouse_move');
    other_bttn.setAttribute('class','bttn arrow_bttn');
    clearTimeout(timeout);
    timeout = setTimeout(function(){
        hide_button(bttn);
    }, 600);
}

function hide_button(bttn){
    var bttn;
    bttn.setAttribute('class','bttn arrow_bttn');
}

function open_image()
{
    var get_img = this.getElementsByTagName('img')[0];
    current_image_index = Number(this.id.split('_')[1]) - 1;
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
    var opac = Number(overlay.style.opacity);

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
