var main = {
    init: function () {
        var _this = this;
        $('#btn-price-update').on('click', function () {
            var currentPrice = $('#price').val();
            var updatedPrice = $('#updatedPrice').val();
            if (updatedPrice > 0 && currentPrice != updatedPrice) {
                if (confirm("가격을 수정하시겠습니까?")) {
                    _this.log(updatedPrice);
                } else {
                    return false;
                }
            } else {
                alert("가격을 다시 입력해 주세요.")
                return false;
            }
        });
    },

    log: function (updatedPrice) {
        var barcode = $('#barcodeId').val();
        var data = {
            id: $('#id').val(),
            name: document.getElementById('user').innerText,
            barcodeId: barcode,
            updatedPrice: updatedPrice
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/price/' + barcode,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('가격 수정을 완료했습니다. 제품 정보를 확인하시겠습니까?');
            window.location.href = '/product/detail/' + barcode;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
};

main.init();