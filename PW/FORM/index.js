const express = require('express');
const app = express();
const bodyparser = require('body-parser');

app.use(express.static('public'));

app.get("/elabData.html", (req, res)=>{
    const data = req.query;
    let (corso, nome) = req.query;

    console.log(data);
    res.send(`<h1>Dati ricevuti ${nome}</h1>`)
})

app.use(bodyparser.urlencoded());

app.post("/elabData", (req, res)=>{
    const data = req.body;

    console.log(data);
    res.send(`<h1>Dati ricevuti ${nome}</h1>`)
})

app.listen(8080, ()=>{
    console.log("Server in ascolto"); 
})