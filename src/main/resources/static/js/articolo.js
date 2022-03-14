const URL ="/api/articoli";
const desc ="/desc/";
const pr ="/pr/";
const cat ="/cat/";
let container = document.querySelector('.articolo2');

	function refresh(){
        window.location.reload("Refresh")
      }
      
    fetch(URL).then(e => e.json())
                .then(e => {
                    let output = '<table class="table table-striped">';
                    for (const articoli of e) {
                        output +=  `<tr>
						<td>${articoli.id}</td>
						<td>${articoli.descrizione}</td>
						<td>${articoli.prezzo}</td>
						<td>${articoli.categoria}</td>
						<td>${articoli.rimanenza}</td>
					</tr>`;
                    }
                    output += '</table>'
                    let container2 = document.querySelector('.articolo');
                    container2.innerHTML = output;

                })
//scelta descrizione
 function cambiaFiltro() {
	 var x = document.getElementById("mySelect").value;
	switch(x) {
		case "Descrizione":   
		fetch(URL).then(e => e.json()).then(cat => {
        let htmlSegment = '<select class="form-control" id="tendina" onchange="cambiaDescrizione()">';
        for (const descrizione of cat) {
         htmlSegment += '<option>'+descrizione.descrizione +'</option>';
		}
		htmlSegment += '</select>';
	
        container.innerHTML += htmlSegment;
    }); 
    break; 
    case "Categoria":
    	 fetch(URL).then(e => e.json()).then(cat => {
        let htmlSegment = '<select class="form-control" id="tendina" onchange="cambiaCategoria()">';
        for (const categoria of cat) {
         htmlSegment += '<option>'+categoria.categoria +'</option>';
		}
		htmlSegment += '</select>';
	
		
        container.innerHTML += htmlSegment;
    });    
     break;    
    	}   
    	
 }
    function cambiaDescrizione() {
            let tendina = document.getElementById('tendina');
            let scelta = tendina.value;
            
            fetch(URL + desc + scelta).then(e => e.json())
                .then(e => {
                    let output = '<table class="table table-striped">';
                    for (const articoli of e) {
                        output += `<tr>
						<td>${articoli.id}</td>
						<td>${articoli.descrizione}</td>
						<td>${articoli.prezzo}</td>
						<td>${articoli.categoria}</td>
						<td>${articoli.rimanenza}</td>
					</tr>`;
                    }
                    output += '</table>'
                    let container2 = document.querySelector('.articolo');
                    container2.innerHTML = output;

                })
		 }   
		 
 function cambiaCategoria() {
            let tendina = document.getElementById('tendina');
            let scelta = tendina.value;
            
            fetch(URL + cat + scelta).then(e => e.json())
                .then(e => {
                    let output = '<table class="table table-striped">';
                    for (const articoli of e) {
                        output +=  `<tr>
						<td>${articoli.id}</td>
						<td>${articoli.descrizione}</td>
						<td>${articoli.prezzo}</td>
						<td>${articoli.categoria}</td>
						<td>${articoli.rimanenza}</td>
					</tr>`;
                    }
                    output += '</table>'
                    let container2 = document.querySelector('.articolo');
                    container2.innerHTML = output;

                })
        }