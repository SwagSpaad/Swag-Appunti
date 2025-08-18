const express = require('express')
const fs = require('fs')
const cors = require('cors')

const app = express()
app.use(cors())
const array = JSON.parse(fs.readFileSync('file.json'))

app.get('/', (req, res) => {
    if(!){
        res.json({
            status:"Errore",
            message: "Errore nella lettura del file"
        })
    }else{
        res.json({
            status: "OK",
            dati: array
        })
    }
})

app.get('/', (req, res) => {
    const id = req.params.id
    const dati = array.find(elem => elem.id == id)
    if(!dati){
        res.json({
            status: "Errore",
            message: "ID non trovato"
        })
    }else{
        res.json({
            status: "OK",
            dati: dati
        })
    }
})

const PORT = 3000
app.listen(PORT, () => {
    console.log(`Server Running in ${PORT}`)
})