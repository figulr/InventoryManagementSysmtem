var main = {
    init : function () {
        var _this = this;
        $('#btn-update').on('click', function(){
            _this.update();
        });
    },

    update : function() {
        var id = $('#id').val();
        var barcode = $('#barcodeId').val();
        var data = {
            id: id,
            barcodeId: barcode,
            productName:$('#productName').val(),
            weight:$('#weight').val(),
            volumeLong :$('#volumeLong').val(),
            volumeShort : $('#volumeShort').val(),
            volumeHeight : $('#volumeHeight').val()
        };

        $.ajax({
            type : 'PUT',
            url : '/api/v1/update/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (){
            alert('제품 정보가 성공적으로 수정되었습니다.');
            window.location.href='/product/detail/'+barcode;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    }
};

main.init();