document.addEventListener('DOMContentLoaded', () => {
    const closeBtn = document.querySelector('.close-repetido');
    const errorBox = document.querySelector('.error-message');

    if (closeBtn && errorBox) {
        closeBtn.addEventListener('click', () => {
            errorBox.classList.remove('show'); // Elimina la clase 'show' para cerrar el modal
        });
    }
});

function mostrarCampos() {
    const tipoUsuario = document.getElementById("tipoUsuario").value;
    document.getElementById("clienteFields").style.display = tipoUsuario === "CLIENTE" ? "block" : "none";
    document.getElementById("colaboradorFields").style.display = tipoUsuario === "COLABORADOR" ? "block" : "none";
}


const select = document.getElementById("tipoUsuario");
const firstOption = select.options[0]; // La primera opción

select.addEventListener("change", function () {
    if (select.selectedIndex !== 0) {
        firstOption.disabled = true; // Deshabilita la opción inicial
    }
});


