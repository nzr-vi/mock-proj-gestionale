function addOne() {
    const URL = "/api/articolo";

    let nuovoArticolo = { 
        "descrizione": document.getElementById("post-descrizione").value,
        "prezzo": document.getElementById("post-prezzo").value,
        "categoria": document.getElementById("post-categoria").value,
        "rimanenza": document.getElementById("post-rimanenza").value

    }


    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(nuovoArticolo)
    })
        .then(response => {

            return response.json(); // restituisco il json convertito
        })
       
}

function putOne() {
    const URL = "/api/articolo";

    let putId = document.getElementById("put-id").value
    if(putId < 1) return;

    let editArticolo = {
        "id": putId,
        "descrizione": document.getElementById("put-descrizione").value,
        "prezzo": document.getElementById("put-prezzo").value,
        "categoria": document.getElementById("put-categoria").value,
        "rimanenza": document.getElementById("put-rimanenza").value
    }

    fetch(URL, {
        method: 'PUT',
        headers : {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(editArticolo)
    })
    .then(response => {
        return response.json();
    })
}