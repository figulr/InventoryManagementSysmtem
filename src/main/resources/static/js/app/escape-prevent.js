var background = {

     const :  listener = (event) => {
        event.preventDefault();
        event.returnValue = '';
    },
    initProtect: function () {
        window.addEventListener('beforeunload', listener);
    },

    offProtect: function(){
        window.removeEventListener('beforeunload', listener);
    }
}

background.initProtect();