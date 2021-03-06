window.onload = function() {

    init();


}
//Global variables
var current_image_index;
var prev_image_index;
var next_image_index;
var list_of_images;
var timeout;

//Init function
function init()
{
    //Add Event Listeners
    add_click_open_overlay();
    add_click_close_overlay();
    add_move_mouse_overlay();
    add_click_scroll_gallery();
    add_click_prev_image();
    add_click_next_image();
}

// Event Handlers
function image_scroll(event)
{
        if(event.clientX > window.innerWidth/2){
            prev_image();
        }else{
            next_image();
        }
}
function add_click_scroll_gallery()
{
    $('click_screen').addEventListener('click',image_scroll);
}
function add_click_prev_image(){
    $('right_bttn').addEventListener("click",prev_image);
}
function add_click_next_image(){
    $('left_bttn').addEventListener("click",next_image);
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
var timeout_2
function prev_image()
{

    var curr = $('displayed_image_container');
    var prev = $('prev_image_container');

    curr.setAttribute('class','move_right');
    prev.setAttribute('class','move_right');
    var cont_new = $('.move_right');
    var duration = Number(window.getComputedStyle(curr).transitionDuration.split("s")[0]);

    clearTimeout(timeout_2);
    timeout_2 = setTimeout(function(){

        current_image_index = ((current_image_index - 1) + list_of_images.length) % list_of_images.length;
        prev_image_index = ((current_image_index - 1) + list_of_images.length) % list_of_images.length;
        next_image_index = ((current_image_index + 1) + list_of_images.length) % list_of_images.length;

        load_prev_image(prev_image_index);
        load_current_image(current_image_index);
        load_next_image(next_image_index);

        curr.setAttribute('class','');
        prev.setAttribute('class','');
    }, 1000 * duration);
}

function next_image()
{
    var curr = $('displayed_image_container');
    var next = $('next_image_container');

    curr.setAttribute('class','move_left');
    next.setAttribute('class','move_left');
    var cont_new = $('.move_left');
    var duration = Number(window.getComputedStyle(curr).transitionDuration.split("s")[0]);

    clearTimeout(timeout_2);
    timeout_2 = setTimeout(function(){

        current_image_index = ((current_image_index + 1) + list_of_images.length) % list_of_images.length;
        prev_image_index = ((current_image_index - 1) + list_of_images.length) % list_of_images.length;
        next_image_index = ((current_image_index + 1) + list_of_images.length) % list_of_images.length;
        
        load_prev_image(prev_image_index);
        load_current_image(current_image_index);
        load_next_image(next_image_index);

        curr.setAttribute('class','');
        next.setAttribute('class','');
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
    //Current image info
    current_image_index = Number(this.id.split('_')[1]) - 1;
    prev_image_index = ((current_image_index - 1) + list_of_images.length) % list_of_images.length;
    next_image_index = ((current_image_index + 1) + list_of_images.length) % list_of_images.length;

    load_current_image(current_image_index);
    load_prev_image(prev_image_index);
    load_next_image(next_image_index);

    open_overlay();


}
function load_current_image(index)
{
    var current_image = list_of_images[index];
    var get_curr_img = current_image.getElementsByTagName('img')[0];
    var img_src = get_curr_img.getAttribute("src");
    var img_to_display_div = $('displayed_image');
    var img_to_display = img_to_display_div.getElementsByTagName('img')[0];

    img_to_display.setAttribute("src", img_src);
}
function load_prev_image(index)
{
    // var prev_image_index = ((current_image_index - 1) + list_of_images.length) % list_of_images.length;
    var prev_image = list_of_images[index];
    var get_prev_img = prev_image.getElementsByTagName('img')[0];
    var prev_img_src = get_prev_img.getAttribute("src");
    var prev_img_to_display_div = $('prev_image');
    var prev_img_to_display = prev_img_to_display_div.getElementsByTagName('img')[0];

    prev_img_to_display.setAttribute("src", prev_img_src);
}

function load_next_image(index)
{
    // var prev_image_index = ((current_image_index - 1) + list_of_images.length) % list_of_images.length;
    var next_image = list_of_images[index];
    var get_next_img = next_image.getElementsByTagName('img')[0];
    var next_img_src = get_next_img.getAttribute("src");
    var next_img_to_display_div = $('next_image');
    var next_img_to_display = next_img_to_display_div.getElementsByTagName('img')[0];

    next_img_to_display.setAttribute("src", next_img_src);
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
