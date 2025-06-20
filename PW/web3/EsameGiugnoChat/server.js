const express = require('express');
const fs = require('fs');
const app = express();
const cors = require('cors');

app.use(cors());
const PORT = 3000;

// Endpoint: GET /persone
app.get('/persone', (req, res) => {
  fs.readFile('persone.json', (err, data) => {
    if (err) return res.status(500).send('Errore server');
    res.json(JSON.parse(data));
  });
});

// Endpoint: GET /persone/:id
app.get('/persone/:id', (req, res) => {
  const id = parseInt(req.params.id);
  fs.readFile('persone.json', (err, data) => {
    if (err) return res.status(500).send('Errore server');
    const persone = JSON.parse(data);
    const persona = persone.find(p => p.id === id);
    if (!persona) return res.status(404).send('Utente non trovato');
    res.json(persona);
  });
});

app.listen(PORT, () => {
  console.log(`Server avviato su http://localhost:${PORT}`);
});
