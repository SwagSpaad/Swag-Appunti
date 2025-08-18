const express = require('express')
const cors = require('cors')
const fs = require('fs')

const app = express()
app.use(cors())

const datiJSON = JSON.parse(fs.readFileSync("./dati.json"))

app.get('/utenti', (req, res) => {
    if (!datiJSON) {
        res.json({
            status: "Errore",
            message: "Errore lettura file"
        })
    } else {
        res.json({
            status: "Ok",
            data: datiJSON
        })
    }
})

app.get('/utenti/:id', (req, res) => {
    const id = req.params.id
    if (!datiJSON) {
        res.json({
            status: "Errore",
            message: "Errore lettura file"
        })
    } else {
        datiJSON.forEach(element => {
            if (element.id == id) {
                res.json({
                    status: "Ok",
                    message: element
                })
            }
        });

        res.json({
            status: "Errore",
            message: "ID non presente"
        })
    }
})


const PORT = 3000
app.listen(PORT, () => {
    console.log(`Server in ascolto sulla porta ${PORT}`)
})