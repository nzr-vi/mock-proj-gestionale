function editAutore(id) {
	window.location.href="http://localhost:8080/autore?id=" + id;
}

function deleteAutore(id) {
	fetch("http://localhost:8080/autore?id=" + id, {
		method: "DELETE"
	})
	
	.then(data=>{ alert(data); location.reload();})
	.catch(errore => {
		alert(errore);
	})
	
}
