var main = {
    init : function () {
        var _this = this;
        $('#btn-register').on('click', function(){
            _this.register();
        });
        $('#btn-update').on('click', function (){
            _this.update();
        })
    },

    register : function() {
        var data = {
            barcodeId:$('#barcodeId').val(),
            productName:$('#productName').val(),
            brand:$('#brand').val(),
            price:$('#price').val(),
            weight:$('#weight').val(),
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
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    update : function (){
        var data = {
            barcodeId:$('#barcodeId').val(),
            productName:$('#productName').val(),
            brand:$('#brand').val(),
            price:$('#price').val(),
            weight:$('#weight').val(),
            volumeLong:$('#volumeLong').val(),
            volumeShort:$('#volumeShort').val(),
            volumeHeight:$('#volumeHeight').val()
        };
        var id = $('#id').val();

        $.ajax({
            type : 'PUT',
            url : '/api/v1/edit/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('제품 정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();