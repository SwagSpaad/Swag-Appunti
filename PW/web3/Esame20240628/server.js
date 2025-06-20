const express = require("express"); // modulo operazioni di creazione e gestione server
const fs = require("fs"); // modulo gestione dei file e del file system locale
const cors = require ("cors") // modulo per la sicurezza web, permette di comunicare con le pagine web

const app = express(); // creo il server
app.use(cors()); // abilito il cors all'interno del server

const prod_list = JSON.parse(fs.readFileSync("file.json")); // leggo il file json
// il contenuto è un vettore contenente informazioni 
// mi viene restituito un oggetto json, json.parse lo trasforma in un oggetto javascript

app.get("/persone",(req,res)=>{
    if(!prod_list){
        const response={ // costruisco la risposta per il client
            "status":"error",
            "message":"lettura file non avvenuta"
        }
        res.json(response); // questo comando serve a restituire respons al client
    }

    else{
        const response = {
            "status" : "corretto",
            dati : prod_list // in questo caso restituiamo i dati
        }
        res.json(response);
    }
});

app.get("/persone/:id",(req, res)=>{
    const id = req.params.id;
    const product = prod_list.find((item) => item.ID === parseInt(id));
    if(!product)
        {
            const response=({
                "status": "error",
                "message": "ID errato",
            });
            res.json(response);
        }
    else
        {
            const response=({
                "status": "Tutto ok",
                data: product,
            });
            res.json(response);
        }
});

app.listen(3000, ()=> { // apertura del server sulla porta 3000
    console.log("Il server è in ascolto sulla porta 3000"); // se il server viene avviato viene visualizzato il messaggio sulla linea di comando
});