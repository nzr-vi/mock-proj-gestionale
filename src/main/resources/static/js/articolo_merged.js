const URL = "/api/articolo";
const desc = "/desc/";
const pr = "/pr/";
const cat = "/cat/";
let list_of_filter_selections = null;
let price_selector = null;
let desc_selector = null;
let articles_list = null;
let last_fetch_url = URL;
let row_template = null;

function delArticolo(id){
	fetch(URL+"?id="+encodeURIComponent(id),{
		method:'DELETE'
	}).then(e => {
		if(e.ok){

			alert("deleted "+id);
			loadTableFromUrl(last_fetch_url);
		}
	});
}

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
	const _div = document.getElementById("div_specific_filter");
	switch (x) {
		case "Descrizione":
			desc_selector.className = "visible";
			_div.className = "invisible";
			price_selector.className = "invisible"
			return;

		case "Categoria":
			_div.className = "visible";
			price_selector.className = "invisible"
			desc_selector.className = "invisible"
			updateFilters(URL + "/categoria");
			return;
			
		case "Prezzo":
			_div.className = "invisible";
			price_selector.className = "visible"
			desc_selector.className = "invisible"
		return;
	}
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

function filterByDesc(){
	let startWith = document.getElementById("input_begin_with").value;
	loadTableFromUrl(URL+"/des_bw/"+startWith);
}

function loadTableFromUrl(urlToFetch) {
	fetch(urlToFetch).then(e => e.json())
		.then(e => {
			articles_list.innerHTML = "";
			let isSuper = document.getElementById('is_supervisor').value;
			/*
										
			*/
			for (const articolo of e) {
				let newNode = document.createElement('tr');
				
				newNode.innerHTML = row_template
					.replaceAll("{{id}}",articolo.id)
					.replaceAll("{{descrizione}}",articolo.descrizione)
					.replaceAll("{{prezzo}}",articolo.prezzo)
					.replaceAll("{{categoria}}",articolo.categoria)
					.replaceAll("{{rimanenza}}",articolo.rimanenza);

				articles_list.appendChild(newNode);
			}
		});
	last_fetch_url = urlToFetch;
}

window.addEventListener('DOMContentLoaded', e => {
	list_of_filter_selections = document.getElementById('select_filter_specific');
	price_selector = document.getElementById('price_selector');
	desc_selector = document.getElementById('desc_selector');
	articles_list = document.getElementById('articles_list');
	let row_template_tr = document.getElementById("row_template");
	row_template = row_template_tr.innerHTML;
	row_template_tr.remove();
	cambiaFiltro();
});
