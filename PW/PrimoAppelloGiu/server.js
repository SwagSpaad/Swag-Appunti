const express = require('express')
const cors = require('cors')
const fs = require('fs')

const app = express();
app.use(cors())

const dati = JSON.parse(fs.readFileSync('./dati.json'))

app.get('/persone', (req, res) => {
    if(!dati) {
        res.json({
            status: "Errore",
            message: "Errore nella lettura dei dati"
        })
    } else {
        res.json({
            status: "Ok",
            message: dati
        })
    }
})

app.get('/persone/:id', (req, res) => {
    const id = req.params.id
    dati.forEach(element => {
        if (element.id == id) {
            res.json({
                status: "Ok",
                message: element
            })
        }
    });

    res.json({
        status: "Errore",
        message: "ID non trovato"
    })
})

const PORT = 3000
app.listen(PORT, () => {
    console.log(`Server in ascolto sulla porta ${PORT}`)
})