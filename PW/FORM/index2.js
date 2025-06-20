const express = require('express');
const app = express();

app.use(express.json());

const PORT = 3000;



app.post("/data", (req, res)=>{
    const data = req.body
    console.log(data);
        
    res.json({
        status: "success",
        message: "Dati ricevuti!"
    })
})

app.listen(PORT, ()=>{
    console.log(`Server in ascolto sulla porta ${PORT}`);
})