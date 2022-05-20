  var main = {
    init : function () {
        var _this = this;
        $('#btn-stock-in').on('click', function(){
            var inStock = Number($('#inStock').val());
            var stockSub = Number($('#stockSub').val());
            _this.log(inStock, stockSub);
        });
        $('#btn-stock-out').on('click', function(){
            var inStock = Number($('#inStock').val());
            var stockSub = Number($('#stockSub').val());
            if(inStock  >= stockSub) {
                _this.log(inStock, stockSub);
            } else {
                alert("재고가 부족해 출고 명령할 수 없습니다.");
            }
        });
    },

    log : function(inStock, stockSub) {
        console.log("함수진입");
        var barcode = $('#barcodeId').val();
        var stockAdd = Number($('#stockAdd').val());

        var data = {
            barcodeId: barcode,
            inStock:inStock+stockAdd-stockSub,
            stockAdd:stockAdd,
            stockSub:stockSub,
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/stock/'+barcode,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (){
            alert('재고 반영이 완료되었습니다.');
            window.location.href='/product/detail/'+barcode;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    }
};

main.init();