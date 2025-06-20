window.addEventListener("load", () => {
  fetch("http://localhost:3000/persone")
    .then(res => res.json())
    .then(data => {
      const lista = document.getElementById("lista-persone");
      data.forEach(persona => {
        const li = document.createElement("li");
        li.textContent = `${persona.nome} ${persona.cognome}`;
        li.style.cursor = "pointer";
        li.addEventListener("click", () => {
          alert(`${persona.nome} ${persona.cognome}, età: ${persona.età}`);
        });
        lista.appendChild(li);
      });
    })
    .catch(err => console.error("Errore nel fetch:", err));

  document.getElementById("aggiornaColori").addEventListener("click", () => {
    document.body.style.backgroundColor = "#00796b";
    document.querySelectorAll("nav a").forEach(link => {
      link.style.color = "#313131";
    });
  });
});
