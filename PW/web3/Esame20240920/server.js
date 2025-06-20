const express = require("express");
const fs = require("fs")

const app = express();

const cors = require("cors");
app.use(cors());

const array = JSON.parse(fs.readFileSync("file.json")); //per leggere il file

app.get("/task",(req,res)=>{
    if(!array) {
        const response=({
            "status": "error",
            "message": "Product not found",
        });
        res.json(response);
    }
    else
    {
        const response=({
            "status": "Tutto ok",
            data: array,
        });
        res.json(response);
    }
})

app.post("/tasks/complete/:id", (req, res) => {
    const ID = parseInt(req.params.id); // prende l'id dall'url
    const activity = array.find((item) => item.id === ID);

    if (!activity) {
        // se l'attivita non esiste
        const response = {
            "status": "error",
            "message": "ID errato"
        };
        res.json(response);
    } 

    activity.completed = true;  // Imposto il valore booleano true

    // Scrivo l'array aggiornato nel file JSON
    fs.writeFileSync('file.json', JSON.stringify(array, null, 2), (err) => {
        if (err) {
            const response = {
                "status": "error",
                "message": "Errore nella scrittura del file"
            };
            return res.json(response);
        }
    });

    res.json({
        status: "Tutto ok",
        data: array,
    })
    
});

app.listen(3000, () => {
    console.log("Server in ascolto su porta 3000");
})