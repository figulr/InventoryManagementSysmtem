var main = {
    init: function () {
        var _this = this;
        $('#btn-search').on('click', function () {
            _this.search();
        });
    },

    search : function() {
        let form = document.createElement('form');
        var barcode = $('#barcodeId').val();
        if(!barcode){
            alert("바코드를 입력하세요.");
            return false;
        }
        form.action = "/product/detail/" + barcode;
        form.method = 'GET';

        document.body.append(form);

        form.submit();
    }
}

main.init();
