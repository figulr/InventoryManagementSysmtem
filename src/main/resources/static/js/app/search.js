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
        form.action = "/product/detail/" + barcode;
        form.method = 'GET';

        document.body.append(form);

        form.submit();
    }
}

main.init();
