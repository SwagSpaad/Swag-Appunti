const express = require('express');
const cors = require('cors');
const fs = require('fs');

const app = express();
app.use(cors());

const array = JSON.parse(fs.readFileSync('./dati.json'));

app.get('/articoli', (req, res) => {
    if(!array) {
        res.json({
            status : "Errore",
            message: "Array non trovato" 
        })
    } else {
        res.json({
            status: "Ok",
            message : array
        })
    }
})

app.get('/autori', (req, res) => {
    const autori = []
    array.forEach(element => {
        if(!autori.includes(element.autore)) {
            autori.push(element.autore)
        }        
    });

    if(!autori) {
        res.json({
            status: "Errore",
            message: "Errore nella get /autori"
        })
    } else {
        res.json({
            status: "Ok",
            message: autori
        })
    }
})

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server in ascolto sulla porta ${PORT}`);
})