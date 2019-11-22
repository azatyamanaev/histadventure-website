var fun = null;
$(document).ready(function f(id) {
    $(id).click(function () {
        $.get('event', function (data) {
            $('#data').text('hello');
            var value = $(id).attr("value");
            window.location.href='/event';
        })
    })
    fun = this;
})

function submit(id) {
    fun(id)
}
