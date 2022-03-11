	function saveAutore(isUpdate){
	
		fetch("http://localhost:8080/api/" + (isUpdate?"update_autore":"create_autore"), {
			method: "POST",
			headers: new Headers({ "Content-Type" : "application/json" }),
			body: JSON.stringify(
				{
					id: document.getElementById('id').value,
					nome: document.getElementById('nome').value,
					cognome: document.getElementById('cognome').value,
					nazionalita: document.getElementById('nazionalita').value
				}		
			)
		
		}).then(response => {
				document.location.href = "http://localhost:8080/autore/list"
		})
	}