const express = require('express')
const cors = require('cors')
const fs = require('fs')

const app = express()

app.use(cors())
const datiJSON = JSON.parse(fs.readFileSync('./dati.json'))

app.get('/tasks', (req, res) => {
    if (!datiJSON) {
        res.json({
            status: "Errore",
            message: "Errore lettura dati"
        })
    } else {
        res.json({
            status: "Ok",
            message: datiJSON
        })
    }
})

app.post('/tasks/complete/:id', (req, res) => {
    const id = req.params.id
    if (!datiJSON) {
        res.json({
            status: "Errore",
            message: "Errore lettura dati"
        })
    } else {
        datiJSON.forEach(element => {
            if (element.id == id) {
                element.completed = true
            }
        });
        res.json({
            status: "Ok",
            message: datiJSON
        })
    }
})

const PORT = 3000
app.listen(PORT, () => {
    console.log(`Server in ascolto sulla porta ${PORT}`)
})