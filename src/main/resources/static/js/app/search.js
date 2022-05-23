var main = {
    init : function () {
        var _this = this;
        $('#btn-register').on('click', function(){
            _this.register();
        });
    },

    register : function() {
        var barcode = $('#barcodeId').val();

        var data = {
            barcodeId: barcode,
            productName:$('#productName').val(),
            brand:$('#brand').val(),
            price:$('#price').val(),
            store:$('#store').val(),
            weight:$('#weight').val(),
            unit:$('#unit').val(),
            volumeLong:$('#volumeLong').val(),
            volumeShort:$('#volumeShort').val(),
            volumeHeight:$('#volumeHeight').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/register',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (){
            alert('제품이 등록되었습니다.');
            window.location.href='/product/detail/'+barcode;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();