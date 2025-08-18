const express = require('express')
const cors = require('cors')
const fs = require('fs')

const app = express()

app.use(cors())

let contatore = 5;

app.get('/counter', (req, res) => {
    res.json({
        "counter": contatore
    })
})

app.get('/colors', (req, res) => {
    res.json({
        background: "#882200",
        text: "#44DDAA"
    })
})

app.post('/increase', (req, res) => {
    contatore++
    res.json({
        counter: contatore
    })
})

app.post('/decrease', (req, res) => {
    contatore--
    res.json({
        counter: contatore
    })
})

const PORT = 3000
app.listen(PORT, () => {
    console.log(`Server in ascolto sulla porta ${PORT}`)
})