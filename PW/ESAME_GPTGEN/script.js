fetch("http://localhost:3000/utenti")
    .then(response => {
        if(!response.ok) {
            throw new Error("Errore fetch")
        }
        return response.json()
    })
    .then(data => {
        const ul = document.querySelector('.listaUtenti')
        data.data.forEach(user => {
            const li = document.createElement('li')
            li.innerText = `${user.nome} ${user.cognome}`
            ul.appendChild(li)

            li.addEventListener("click", () => {
                alert(`${user.nome} ${user.cognome} ${user.età}`)
            })
        });
    })

    const bottoneCambia = document.querySelector('.cambiaStile')
    bottoneCambia.addEventListener("click", () => {
        const body = document.querySelector("body")
        body.style.backgroundColor = '#d81b60'
        
        const a = document.querySelectorAll('a')
        a.forEach(elem => {
            elem.style.color = '#ffffff'
        })
    })