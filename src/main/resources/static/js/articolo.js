const URL = "/api/articolo";
const desc = "/desc/";
const pr = "/pr/";
const cat = "/cat/";
let list_of_filter_selections = document.getElementById('selections_list');

//scelta descrizione
function cambiaFiltro() {
	var x = document.getElementById("mySelect").value;
	switch (x) {
		case "Descrizione":{

            const mapping = "/descrizione";
            
			fetch(URL+mapping).then(e => e.json()).then(cat => {
				list_of_filter_selections.innerHTML = "";
                let htmlSegment = '<select class="form-control" id="tendina" onchange="cambiaDescrizione()">';
				for (const descrizione of cat) {
                    htmlSegment += '<option>' + descrizione + '</option>';
				}
				htmlSegment += '</select>';

				list_of_filter_selections.innerHTML += htmlSegment;
			});
			break;
            }
		case "Categoria":
            {
            const mapping = "/categoria";
			fetch(URL+mapping).then(e => e.json()).then(cat => {
                list_of_filter_selections.innerHTML = "";
				let htmlSegment = '<select class="form-control" id="tendina" onchange="cambiaCategoria()">';
				for (const categoria of cat) {
					htmlSegment += '<option>' + categoria + '</option>';
				}
				htmlSegment += '</select>';


				list_of_filter_selections.innerHTML += htmlSegment;
			});
			break;
        }
	}
}

function cambiaDescrizione() {
	let tendina = document.getElementById('tendina');
	let scelta = tendina.value;

    loadTableFromUrl(URL + desc + scelta);
}

function cambiaCategoria() {
	let scelta = tendina.value;
    loadTableFromUrl(URL + cat + scelta);
}

function filterByPrice(){
	const input_min =  document.getElementById('min_price');
	let price_url = `${URL}/price_range?min=${}	`
	loadTableFromUrl();
}

function loadTableFromUrl(urlToFetch){
    fetch(urlToFetch).then(e => e.json())
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

function refresh() {
    window.location.reload("Refresh");
}

window.addEventListener('DOMContentLoaded',e=>{

    loadTableFromUrl(URL);
});
