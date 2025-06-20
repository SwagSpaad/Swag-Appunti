const express = require("express"); // modulo operazioni di creazione e gestione server
const fs = require("fs"); // modulo gestione dei file e del file system locale
const cors = require ("cors") // modulo per la sicurezza web, permette di comunicare con le pagine web

const app = express(); // creo il server
app.use(cors()); // abilito il cors all'interno del server

const prod_list = JSON.parse(fs.readFileSync("file.json")); // leggo il file json
// il contenuto è un vettore contenente informazioni 
// mi viene restituito un oggetto json, json.parse lo trasforma in un oggetto javascript

app.get("/articoli",(req,res)=>{
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

app.get("/autori", (req,res)=>{
    const autori = [];
    prod_list.forEach(element => {
        if(!autori.includes(element.autore)){
            autori.push(element.autore);
        }
    });
    const response = {
        "status" : "corretto",
        dati : autori
    }
    res.json(response);
});
const PORT = 3000
app.listen(PORT, ()=> { // apertura del server sulla porta 3000
    console.log(`Il server è in ascolto sulla porta ${PORT}`); // se il server viene avviato viene visualizzato il messaggio sulla linea di comando
});