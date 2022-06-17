  var main = {
    init : function () {
        var _this = this;
        $('#btn-stock-in').on('click', function(){
            var inStock = Number($('#inStock').val());
            var stockAdd = Number($('#stockAdd').val());
            var stockSub = Number($('#stockSub').val());

            if(confirm(stockAdd+"개 입고하시겠습니까?")){
                _this.log(inStock, stockAdd, stockSub);
            } else {return false;}
        });
        $('#btn-stock-out').on('click', function(){
            var inStock = Number($('#inStock').val());
            var stockAdd = Number($('#stockAdd').val());
            var stockSub = Number($('#stockSub').val());
            if(inStock  >= stockSub) {
                if(confirm(stockSub+"개 출고하시겠습니까?")){
                    _this.log(inStock, stockAdd, stockSub);
                } else {
                    return false;
                }
            } else {
                alert("재고가 부족해 출고 명령할 수 없습니다.");
            }
        });
    },

    log : function(inStock, stockAdd, stockSub) {
        console.log("함수진입");
        var barcode = $('#barcodeId').val();

        var data = {
            barcodeId: barcode,
            inStock:inStock+stockAdd-stockSub,
            stockAdd:stockAdd,
            stockSub:stockSub,
            name:$('#user').val()
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