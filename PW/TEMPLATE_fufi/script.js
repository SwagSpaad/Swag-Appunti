fetch("http://localhost:3000/")
    .then(response => {
        if(!response.ok){
            throw new Error("Errore nella lettura del file");   
        }
        return response.json()
    })
    .then(data =>{
        const = document.querySelector("")
        
    })
        