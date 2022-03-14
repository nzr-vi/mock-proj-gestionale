const URL = "/api/articolo";
const desc = "/desc/";
const pr = "/pr/";
const cat = "/cat/";
let list_of_filter_selections = null;
let price_selector = null;
let articles_list = null;

function updateFilters(filter_mapping) {
	fetch(filter_mapping).then(e => e.json()).then(array => {
		list_of_filter_selections.innerHTML = "";
		for (const filter of array) {
			list_of_filter_selections.innerHTML += '<option>' + filter + '</option>';
		}
	});
}

//scelta descrizione
function cambiaFiltro() {
	let x = document.getElementById("select_filter_type").value;
	let mapping;
	const _div = document.getElementById("div_specific_filter");
	switch (x) {
		case "Descrizione":
			mapping = "/descrizione";
			break;

		case "Categoria":
			mapping = "/categoria";
			break;

		case "Prezzo":
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
			articles_list.innerHTML = "";
			let isSuper = document.getElementById('is_supervisor').value;
			/*
										
			*/
			for (const articolo of e) {
				articles_list.innerHTML += `<tr>
						<td>${articolo.id}</td>
						<td>${articolo.descrizione}</td>
						<td>${articolo.prezzo}</td>
						<td>${articolo.categoria}</td>
						<td>${articolo.rimanenza}</td>`
						+(isSuper?`<td>
						<a href="/home/${articolo.id}" class="fa fa-pencil" title="modifica">
						</a></td>`:"")+"</tr>";
			}
		})
}

window.addEventListener('DOMContentLoaded', e => {
	list_of_filter_selections = document.getElementById('select_filter_specific');
	price_selector = document.getElementById('price_selector');
	articles_list = document.getElementById('articles_list');

	cambiaFiltro();
});
