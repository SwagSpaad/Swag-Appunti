const express = require("express");
const fs = require("fs")

const app = express();

const cors = require("cors");
app.use(cors());

let contatore = 5;

app.get("/counter", (req,res)=>{
    const response = ({
        "counter": contatore,
    });
    res.json(response);
})

app.get("/colors", (req,res)=>{
    const response = ({
        "background": "#882200", 
        "text": "#44DDAA",
    })
    res.json(response);
})

app.post('/increase', (req, res) => {
    contatore++;
    res.json({contatore});
});

// Endpoint POST /decrease
app.post('/decrease', (req, res) => {
    contatore--;
    res.json({contatore});
});

app.listen(3000, () => {
    console.log("Server in ascolto su porta 3000");
})