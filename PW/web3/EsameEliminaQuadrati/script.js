let jsonData = [];

window.addEventListener("load", () => {
  fetch("data.json")
    .then(response => response.json())
    .then(data => {
      jsonData = data;
      jsonData.forEach(el => console.log(el));
    })
    .catch(error => console.error("Errore nel caricamento JSON:", error));
});

document.getElementById("titolo").addEventListener("click", () => {
  const main = document.querySelector("main");
  const viewportHeight = window.innerHeight;
  const squareSize = viewportHeight * 0.10; // 10% viewport height

  jsonData.forEach(item => {
    const square = document.createElement("div");
    square.style.width = square.style.height = squareSize + "px";
    square.style.position = "absolute";
    square.style.left = item.pos_orizz + "%";
    square.style.top = item.pos_vert + "%";
    square.style.backgroundColor = item.colore;
    square.style.transform = "translate(-50%, -50%)";

    // Arrow function per eliminare il div al click
    square.addEventListener("click", (e) => {
      e.target.remove();
    });

    main.appendChild(square);
  });
});
