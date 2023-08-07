

 function validar(){
	 const formulario = document.getElementById('fmrContato');
	 const campoNome = document.getElementById('nome');
	 const campoFone = document.getElementById('fone');
	 const campoIdcon = document.getElementById('id');
	
	 if(campoNome.value === ""){
		 alert("Campo nome é obrigatório");
		 campoNome.focus();
		 return false;
	 }
	  if(campoFone.value === ""){
		 alert("Campo fone é obrigatório");
		 campoFone.focus();
		 return false;
	 }
	 
	 if(campoIdcon!==null){
		 if(campoIdcon === ""){
			 alert("Erro: esse dado não tem referência");
			 return false;
		 }
	 }

	 if(document.getElementById('_method')){
		 const campoEmail = document.getElementById('email');
		 const dados = {
			 idcon: campoIdcon.value,
			 nome: campoNome.value,
			 fone: campoFone.value,
			 email: campoEmail.value
		 }
		 atualizarContato(dados);
	 }else {
		formulario.submit(); 
	 }
 }
 
  function atualizarContato(dados) {
            fetch(`update`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(dados)
            })
            .then(response => {

                    // Atualização bem-sucedida, faça algo se necessário
                    window.location.href = "main";
            })
            .catch(error => {
                // Trate o erro se necessário
                alert("Erro na requisição");
            });
        }
        
 function exluirContato(id) {
            fetch(`delete?id=`+id, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
            })
            .then(response => {

                    // Atualização bem-sucedida, faça algo se necessário
                    window.location.href = "main";
            })
            .catch(error => {
                // Trate o erro se necessário
                alert("Erro na requisição");
            });
        }