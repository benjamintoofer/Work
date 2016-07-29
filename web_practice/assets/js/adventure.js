window.onload = function() {

    init();


}

function init()
{
    add_click_event_images();
}

function add_click_event_images()
{

    var list_of_images = $('.image_container');

    for (var i = 0; i < list_of_images.length; i++) {
        // img.addEventListener("click",test);
        list_of_images[i].addEventListener("click",open_image);
    }
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
    img_to_display.setAttribute("width",new_width);
    img_to_display.setAttribute("height",new_height);

    img_to_display_div.style.margin = "2.5%";

}
function open_overlay()
{
        var elem = $("photo_page");
        var body = document.getElementsByTagName('body');
        elem.style.visibility = "visible";
        body[0].style.overflow = "hidden";
        // console.log(elem.style.visibility);
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
