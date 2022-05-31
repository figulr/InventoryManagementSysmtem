  var main = {
    init : function () {
        var _this = this;
        $('#btn-price-update').on('click', function(){
            var updatedPrice = $('#updatedPrice').val();
            if(updatedPrice > 0){
                if(confirm("가격을 수정하시겠습니까?")) {
                    _this.log(updatedPrice);
                } else {
                    return false;
                }
            } else {
                alert("올바른 가격을 입력해 주세요.")
                return false;
            }
        });
    },

    log : function(updatedPrice) {
        var barcode = $('#barcodeId').val();
        var data = {
            id:$('#id').val(),
            barcodeId: barcode,
            updatedPrice: updatedPrice
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