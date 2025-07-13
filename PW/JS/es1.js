/*
let names = ['mario', 'giovanna', 'pippo']
modificare l'array per:
1. convertire i nomi in maiuscolo
2. aggiungere "Dr." prima del nome
3. calcolare chi ha il nome più lungo
*/

let names = ['mario', 'giovanna', 'pippo'];

console.log(names.map((item) => item.charAt(0).toUpperCase() + item.slice(1)));
console.log(names.map((item) => "Dr. " + item.charAt(0).toUpperCase() + item.slice(1)))

let nomePiuLungo = function(arr) {
    let arrLen = arr.map(item => item.length);
    let longest = [arrLen[0], 0];
    for (let i in arrLen) {
        longest[0] >= arrLen[i] ? longest : longest = [arrLen[i], i];
    }
    console.log("il nome più lungo è " + arr[longest[1]]);
}

nomePiuLungo(names);
