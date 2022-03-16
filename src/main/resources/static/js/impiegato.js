const URL = "/api/impiegato";
const rl = "/rl/";
const pr = "/pr/";
const cat = "/nm/";
let list_of_filter_selections = null;
let price_selector = null;
let impiegati_list = null;

function updateFilters(filter_mapping) {
	fetch(filter_mapping).then(e => e.json()).then(array => {
		list_of_filter_selections.innerHTML = "";
		for (const filter of array) {
			list_of_filter_selections.innerHTML += '<option>' + filter + '</option>';
		}
	});
}

function cambiaFiltro() {
	let x = document.getElementById("select_filter_type").value;
	let mapping;
	const _div = document.getElementById("div_specific_filter");
	switch (x) {
		case "Nome":
			mapping = "/nome";
			break;
			
		case "Cognome":
			mapping = "/cognome";
			break;
			
		case "Ruolo":
			mapping = "/ruolo";
			break;

		case "Stipendio":
			_div.className = "invisible";
			price_selector.className = "visible"

			defalut:
			return;
	}

	price_selector.className = "invisible"
	_div.className = "visible";
	updateFilters(URL + mapping);
}

function filterByPrice() {
	const input_min = document.getElementById('min_price').value;
	const input_max = document.getElementById('max_price').value;
	let price_url = `${URL}/price_range?min=${encodeURIComponent(input_min)}&max=${encodeURIComponent(input_max)}`
	loadTableFromUrl(price_url);
}

function applicaFiltro() {
	let desc = document.getElementById("select_filter_type").value.toLowerCase().substring(0,3);
	let scelta = list_of_filter_selections.value;
	loadTableFromUrl(URL +"/"+ desc +"/"+ scelta);
}

function loadTableFromUrl(urlToFetch) {
	fetch(urlToFetch).then(e => e.json())
		.then(e => {
			impiegati_list.innerHTML = "";

			for (const impiegato of e) {
				impiegati_list.innerHTML += `<tr>
						<td>${impiegato.id}</td>
						<td>${impiegato.nome}</td>
						<td>${impiegato.cognome}</td>
						<td>${impiegato.ruolo}</td>
						<td>${impiegato.riferimento}</td>
						<td>${impiegato.stipendio}</td>`
						+(`<td>
						<a href="/impiegato/${impiegato.id}" class="fa fa-pencil" title="modifica">
						</a></td>`+"")+"</tr>";
			}
		})
}

window.addEventListener('DOMContentLoaded', e => {
	list_of_filter_selections = document.getElementById('select_filter_specific');
	price_selector = document.getElementById('price_selector');
	impiegati_list = document.getElementById('impiegati_list');

	cambiaFiltro();
});
