const express = require('express')
const cors = require('cors')
const fs = require('fs')

const app = express()
app.use(cors())
const datiJSON = JSON.parse(fs.readFileSync('dati.json'))

app.get('/items', (req, res) => {
    if(!datiJSON) {
        res.json({
            status: "Errore",
            message: "Errore nella lettura del file"
        }) 
    } else {
        res.json({
            status: "Ok",
            data: datiJSON
        })
    }
})

app.get('/items-complete', (req, res) => {
    const completed = []
    datiJSON.forEach(element => {
        if(element.completato == true) {
            completed.push(element)
        }
    });

    if(completed.length > 0) {
        res.json({
            status: "Ok",
            data: completed
        })
    } else {
        res.json({
            status: "Errore",
            message: "Nessun attività completata"
        })
    }
})

const PORT = 3000
app.listen(PORT, () => {
    console.log(`Server in ascolto sulla porta ${PORT}`)
})