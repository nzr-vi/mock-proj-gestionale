const URL = "/api/clienti";
const nome = "/nome";
const cognome = "/cognome";
const email = "/email";
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
		case "Nome":
			mapping = "/nome";
			break;

		case "Cognome":
			mapping = "/cognome";
			break;

		case "Email":
			mapping = "/email";
			break;

			default:
			return;
	}

	_div.className = "visible";
	updateFilters(URL + mapping);
	
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
			for (const cliente of e) {
				articles_list.innerHTML += `<tr>
						<td>${cliente.id}</td>
						<td>${cliente.nome}</td>
						<td>${cliente.cognome}</td>
						<td>${cliente.telefono}</td>
						<td>${cliente.email}</td>
						<td>${cliente.indirizzo}</td>
						<td>${cliente.citta}</td>
						<td>${cliente.provincia}</td>
						<td>${cliente.regione}</td>
						<td>${cliente.credito}</td>`
						+(isSuper?`<td>
						<a href="/lista-clienti/${cliente.id}" class="fa fa-pencil" title="modifica">
						</a></td>`:"")+"</tr>";
			}
		})
}

window.addEventListener('DOMContentLoaded', e => {
	list_of_filter_selections = document.getElementById('select_filter_specific');
	articles_list = document.getElementById('articles_list');

	cambiaFiltro();
});
