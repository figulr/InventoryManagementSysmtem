var main = {
    init: function () {
        var _this = this;
        $('#btn-stock-in').on('click', function () {
            var inStock = Number($('#inStock').val());
            var stockAdd = Number($('#stockAdd').val());
            var stockSub = 0;
            if (stockAdd > 0) {
                if (confirm(stockAdd + "개 입고하시겠습니까?")) {
                    _this.log(inStock, stockAdd, stockSub);
                } else {
                    return false;
                }
            } else {
                alert("입고수량을 다시 입력해주십시오.");
                return false;
            }
        });
        $('#btn-stock-out').on('click', function () {
            var inStock = Number($('#inStock').val());
            var stockAdd = 0;
            var stockSub = Number($('#stockSub').val());
            if (stockSub > 0) {
                if (inStock >= stockSub) {
                    if (confirm(stockSub + "개 출고하시겠습니까?")) {
                        _this.log(inStock, stockAdd, stockSub);
                    } else {
                        return false;
                    }
                } else {
                    alert("재고가 부족해 출고 명령할 수 없습니다.");
                }
            } else {
                alert("출고수량을 다시 입력해주십시오.");
                return false;
            }
        });
    },

    log: function (inStock, stockAdd, stockSub) {
        var barcode = $('#barcodeId').val();

        var data = {
            barcodeId: barcode,
            inStock: inStock + stockAdd - stockSub,
            stockAdd: stockAdd,
            stockSub: stockSub,
            name: document.getElementById('user').innerText
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/stock/' + barcode,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('재고 반영이 완료되었습니다.');
            window.location.href = '/product/detail/' + barcode;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
};

main.init();