var main = {
    init: function () {
        $('#btn-search').on('keyup', function(key){
            if(key.keyCode == 13) {
                var barcode = $('#searchBarcode').val();
                if(Number(barcode) == 0 | !barcode){
                    alert("바코드를 입력해주세요.");
                    $('#searchBarcode').val('');
                    $('#searchBarcode').focus();
                } else{
                    _this.search();
                }
            }
        });

    },

    search : function() {
        let form = document.createElement('form');
        var barcode = $('#search-barcodeId').val();
        if(!barcode){
            alert("바코드를 입력하세요.");
            return false;
        }
        form.action = "/product/detail/" + barcode;
        form.method = 'GET';

        document.body.append(form);

        form.submit()
    }
}

main.init();
