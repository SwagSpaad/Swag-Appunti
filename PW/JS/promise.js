console.log("Ciao");

const promiseDelay = new Promise(function(resolve, reject){
    setTimeout(function(){
        //resolve("Hai Vinto!")
        reject(new Error("Hai Perso"))
    }, 5000);
})

promiseDelay.then(result => console.log(result))