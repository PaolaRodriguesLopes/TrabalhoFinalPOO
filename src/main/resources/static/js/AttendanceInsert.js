
let listDatetime = [];

$(document).ready (() => {
    const selectProfessional = document.getElementById ('selectProfessional');
    selectProfessional.addEventListener ('change', () => {
       
        const id = $(selectProfessional).val ();
        if (id !== undefined && id !== null && id !== '') {
            listDatetime = [];
            listAllByProfessional (id);
        }
    });

    const inputDatetime = document.getElementById ('inputDatetime');
    inputDatetime.addEventListener ('focusout', function () {
        let value = $(inputDatetime).val ();
        if (value !== null && value !== '' && (value.length === 16 || value.length === 19)) {
            value = value.replace ('T', ' ');
            if (!checkIfIsValidDate (value)) {
                alert ('DATA/HORA EM USO! TENTE UM ATENDIMENTO PARA DEPOIS DE 30 MIN');
            }
        }
    });
    
    const form = document.getElementById ('form');
    const buttonSchedule = document.getElementById ('buttonSchedule');
    const buttonSubmit = document.getElementById ('buttonSubmit');

    buttonSchedule.addEventListener ('click', function () {
        if (form.checkValidity ()) {
            let value = $(inputDatetime).val ();
            if (value !== null && value !== '' && (value.length === 16 || value.length === 19)) {
                value = value.replace ('T', ' ');
                if (!checkIfIsValidDate (value)) {
                    alert ('DATA/HORA EM USO! TENTE UM ATENDIMENTO PARA DEPOIS DE 30 MIN');
                }
                else {
                    form.submit ();
                }
            }
        }
        else {
            alert ('PREENCHA O FORM');
            buttonSubmit.click ();
        }
    });

});

function checkIfIsValidDate (value) {
    for (let datetime of listDatetime) {
        if (value.length === 16) {
            let lastIndexOf = datetime.lastIndexOf (':');
            datetime = datetime.substring (0, lastIndexOf);
        }

        let future = new Date (datetime);
        future = new Date (future.getTime() + (30 * 60000));
        let toDate = new Date (value);
        if (datetime === value || toDate >= new Date (datetime) && toDate <= future) {
            return false;
        }
    }

    return true;
}

function onSubmit (ev) {
    ev.preventDefault ();
    alert ('SUBMIT');
    return false;
}