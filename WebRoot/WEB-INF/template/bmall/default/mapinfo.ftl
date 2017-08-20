<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script type="text/javascript">
var geocoder,map,marker = null;
var init = function() {
    var center = new qq.maps.LatLng(39.916527,116.397128);
    map = new qq.maps.Map(document.getElementById('container'),{
        center: center,
        zoom: 15
    });
    geocoder = new qq.maps.Geocoder({
        complete : function(result){
            map.setCenter(result.detail.location);
            var marker = new qq.maps.Marker({
                map:map,
                position: result.detail.location
            });
        }
    });
    codeAddress();
}
function codeAddress() {
    var address = document.getElementById("address").value;
    geocoder.getLocation(address);
}
</script>