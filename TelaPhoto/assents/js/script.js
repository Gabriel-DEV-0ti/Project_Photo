const api = "http://localhost:8080/api/Photo_NetWork";

async function carregarGaleria() {
    const res = await fetch(api);
    const imagens = await res.json();
    const galeria = document.getElementById("galeria");
    galeria.innerHTML = "";

    imagens.forEach(img => {
        const div = document.createElement("div");
        div.className = "imagem";
        div.innerHTML = `
            <p><strong>${img.nome}</strong></p>
            <img src="${img.url}" alt="${img.nome}">
            <button onclick="removerImagem(${img.id})">Remover</button>
            <button onclick="editarImagem(${img.id}, '${img.nome}', '${img.url}')">Editar</button>
        `;
        galeria.appendChild(div);
    });
}

async function adicionarImagem(e) {
    e.preventDefault();
    const nome = document.getElementById("nome").value;
    const url = document.getElementById("url").value;
    await fetch(api, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, url })
    });
    carregarGaleria();
    e.target.reset();
}

async function removerImagem(id) {
    await fetch(`${api}/${id}`, { method: "DELETE" });
    carregarGaleria();
}

function editarImagem(id, nomeAtual, urlAtual) {
    const novoNome = prompt("Novo nome:", nomeAtual);
    const novaUrl = prompt("Nova URL:", urlAtual);
    if (novoNome && novaUrl) {
        fetch(`${api}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ nome: novoNome, url: novaUrl })
        }).then(carregarGaleria);
    }
}

document.getElementById("formImagem").addEventListener("submit", adicionarImagem);
carregarGaleria();