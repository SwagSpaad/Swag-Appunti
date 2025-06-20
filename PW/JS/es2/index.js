const express = require('express');
const app = express();

const PORT  = 8080;

app.get("/", (req, res) => {
    res.send("Home");
})

app.get("/about", (req, res) => {
    res.send("About");
})

app.get("/contact", (req, res) => {
    res.set("Content-type", "text/plain");
    res.send("Contatti");
})

app.post("/contact", (req, res) => {
    res.send("Contatti POST");
})

/*
app.get("/users/:id/aula/:aula", (req, res) => {
    console.log(req.params);
    res.send("User");
})
*/

//creo il router
const routerUser = express.Router();

//il router gestisce a partire dal path /users/login
routerUser.post("/login",(req, res) =>{
    res.send("POST Login");
})

//il router gestisce a partire dal path /users/logout
routerUser.get("/logout",(req, res) =>{
    res.send("GET Logout");
})

//tutto ciò che va sul path users viene gestito da routerUsers
app.use("/users", routerUser)



app.listen(PORT, (err) => {
    console.log(`Server in ascolto sulla porta ${PORT}`);
})