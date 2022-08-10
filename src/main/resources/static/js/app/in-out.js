var i = 0;
var barcodeList = [];
var main = {
    init : function () {
        var _this = this;
        $('#btn-in').on('click', function(){
            _this.intoListing();
        });
        $('#btn-in-complete').on('click', function(){
            _this.intoOperation();
        })
    },

    intoListing : function() {
        var barcode = $('#searchBarcode').val();
        var checked = false;
        if( i > 0) {
            for (j = 0; j < barcodeList.length; j++) {
                    if(barcode==barcodeList[j]){
                        k = j+1;
                        $('#'+k+'-number').attr("value", String(Number($('#'+k+'-number').val())+1));
                        checked = true;
                    } else {
                        continue;
                    }
            }
        }
        if (!checked) {
                barcodeList.push(barcode);
                $.ajax({
                    type: 'GET',
                    url: '/api/v1/in/' + barcode,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                }).done(function (response) {
                    let product = {
                        "id": response.id,
                        "barcodeId": response.barcodeId,
                        "productName": response.productName,
                        "brand": response.brand,
                        "price": response.price
                    }
                    let row = `
                        <tr>
                            <td>${i+1}</td>
                            <td>${product.id}</td>
                            <td id="${i+1}-barcode">${product.barcodeId}</td>
                            <td><a th:href="'/product/detail/'+${product.barcodeId}" target='_blank'>${product.productName}</a></td>
                            <td>${product.brand}</td>
                            <td>${product.price}</td>
                            <td>
                                <input type="number" id="${i+1}-number" value="1" min="1">
                            </td>
                        </tr>
                        `
                    $('.table').append(row);
                    i++;
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                })
            }
    },

    intoOperation : function (){
        var data = {
            barcodeId:$('#searchBarcode').val(),
        };
        $.ajax({
            type : 'GET',
            url : '/api/v1/in/'+barcode,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response){
            alert('제품정보를 불러왔습니다.');
            alert(response);
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();