$("label").click(function(e) {
    var color = e.target.innerHTML.toLowerCase();
    var products = $(".card");
    for(var i = 0; i< products.length; i++) {
        products[i].style.display = 'none';
    }
    if (color != 'reset') {
        var colorElmnts = $('.' + color);
        for(var a = 0; a < colorElmnts.length; a++) {
            colorElmnts[a].style.display = 'block';
        }
    } else {
        var products = $(".card");
        for(var i = 0; i< products.length; i++) {
            products[i].style.display = 'block';
        }
    }

});