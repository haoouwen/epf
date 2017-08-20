(function($) {
    $.fn.menu = function(b) {
		var c,items,httpAdress;
		b = jQuery.extend({Speed: 220,autostart: 1,autohide: 1},b);
		c = $(this);
        items = c.children("ul").parent("li").children("p");
        httpAdress = window.location;
        items.addClass("inactive");
        function _item() {
            var a = $(this);
			if (b.autohide) {
                a.parent().parent().find(".active").parent("li").children("ul").slideUp(b.Speed / 1.2, 
                function() {
                    $(this).parent("li").children("p").removeAttr("class");
                    $(this).parent("li").children("p").attr("class", "inactive")
                })
            }
            if (a.attr("class") == "inactive") {
                a.parent("li").children("ul").slideDown(b.Speed, 
                function() {
                    a.removeAttr("class");
                    a.addClass("active")
                })
            }
            if (a.attr("class") == "active") {
                a.removeAttr("class");
                a.addClass("inactive");
                a.parent("li").children("ul").slideUp(b.Speed)
            }
        }
        items.unbind('click').click(_item);
    }
})(jQuery);