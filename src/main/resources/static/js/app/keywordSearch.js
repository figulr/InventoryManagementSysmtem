$('#btn-search').on('click', function(){
    if(!$('#searchValue').val()){
        alert("검색어를 입력해 주세요.");
        return false;
    }
})
