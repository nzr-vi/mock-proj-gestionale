 // Popup aggiungi/modifica
 function openForm() {
    document.getElementById("myForm").style.display = "block";
  }
  
  function closeForm() {
    document.getElementById("myForm").style.display = "none";
  }

  // Magia nera di Gian
  function deleteEditore(id) {
      fetch("http://localhost:8080/api/editore/" + id, {
        method: "DELETE"
      })
      .then(response => {
        response.text().then(x => alert(x));
        location.reload();
      })
      .catch(errore => {
        alert(errore);
      })
  }

  function findEditore(id) {
    fetch("http://localhost:8080/api/editore/" + id)
    .then(response => response.json())
    .then(editore => {
      console.log(editore);
      document.getElementById("idInput").value = editore.id;
      document.getElementById("nomeInput").value = editore.nome;
      document.getElementById("contattoInput").value = editore.contatto;
      document.getElementById("formAddEdit").action = "/lista-editori/modify-editore";
      document.getElementById("titleAdd").textContent = "Modifica Editore";
      document.getElementById("editButton").value = "Modifica";
    })
    .catch(errore => {
      alert(errore);
    })
  }