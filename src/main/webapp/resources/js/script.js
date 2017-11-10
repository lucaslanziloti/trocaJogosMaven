//aplica máscara de data
function mascaraCalendario() {
    $('.calendario input').mask("99/99/9999");
};

function aplicarMascara(src, mask, forbidden) {

    if (window.event.keyCode == forbidden.charCodeAt(0)) {
        return false;
    }

    var i = src.value.length;
    var saida = mask.substring(0, 1);
    var texto = mask.substring(i)
    if (texto.substring(0, 1) != saida) {
        src.value += texto.substring(0, 1);
    }
};

function aplicaMascara(src, mask, e) {

    $(src).attr('maxlength', mask.length);

    if (e.keyCode === 8) {
        return;
    }
    var i = $(src).val().length;
    var saida = mask.substring(0, 1);
    var cursor = mask.substring(i);

    if (cursor.substring(0, 1) !== saida) {
        cursor.substring(0, 1);
        $(src).val();
        $(src).val($(src).val() + cursor.substring(0, 1));
    }
};