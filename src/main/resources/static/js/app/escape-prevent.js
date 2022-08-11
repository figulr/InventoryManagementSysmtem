var main = {
    initProtect: function () {
        window.addEventListener('beforeunload', (event)=>{
            event.preventDefault();
            event.returnValue = '';
            // confirm("페이지를 나가겠습니까?");
        })
    }
}

main.initProtect();