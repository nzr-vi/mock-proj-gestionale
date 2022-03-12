function addOne() {
    console.log("addOne-fetch");
    const URL = "/api/articolo";

    // creo un nuovo studente json da aggiungere
    // recupero i dati dai tag input
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