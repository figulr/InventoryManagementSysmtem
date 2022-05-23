  var main = {
    init : function () {
        var _this = this;
        $('#btn-price-update').on('click', function(){
            if(confirm("가격을 수정하시겠습니까?")){
            _this.log();
        }
            else {
                return false;
            }
        });
    },

    log : function() {
        var barcode = $('#barcodeId').val();
        var data = {
            id:$('#id').val(),
            barcodeId: barcode,
            updatedPrice: $('#updatedPrice').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/price/'+barcode,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).always(function (){
            console.log("response 받음");
            alert('가격 수정을 완료했습니다. 제품 정보를 확인하시겠습니까?');
            window.location.href='/product/detail/'+barcode;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    }
};

main.init();