
function listAllByProfessional (id) {
    try {
        let request = new XMLHttpRequest ();
        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest ();
        }
        else if (window.ActiveXObject) {
            request = new ActiveXObject ('Microsoft.XMLHTTP');
        }

        request.onreadystatechange = function (){

            if (request.status === 200 && request.readyState === 4) {
                let responseText = request.responseText;
                let listAttendances = JSON.parse (responseText);
                if (listAttendances !== null) {
                    listAttendances.forEach ((attendance) => {
                        if (attendance.status !== "Cancelado" && listDatetime.indexOf (attendance.datetime) === -1) {
                            listDatetime.push (attendance.datetime);
                        }
                    });

                   console.log ('bla bla bla');
                }
            }
        };

        request.open ('GET', `attendancesByProfessionalAjax?id=${id}`, true);
        request.send ();
    } 
    catch (error) {
        console.log ('error', error);
    }
}