function mostrarAlerta(mensaje, tipoAlerta = 'alert-info') {
    const existingMessage = document.querySelector('.alert');
    if (existingMessage) {
        existingMessage.remove();
    }

    const alerta = document.createElement('div');
    alerta.classList.add('alert', tipoAlerta);
    alerta.textContent = mensaje;

    alerta.style.position = 'fixed';
    alerta.style.top = '10%';
    alerta.style.left = '50%';
    alerta.style.transform = 'translateX(-50%)';
    alerta.style.zIndex = '1050';

    const mainContainer = document.getElementById('prox');
    mainContainer.insertBefore(alerta, mainContainer.firstChild);
    alerta.classList.add('in');

    setTimeout(() => alerta.classList.remove('in'), 4000);
    setTimeout(() => alerta.remove(), 4000);
}

function validateMonto(input) {
    const value = parseInt(input.value, 10);
    if (value > 999999) {
        input.value = "";
        mostrarAlerta("El monto ingresado supera el limite de 999999. Intente de nuevo.");
    }
}

function validateTelefono(input) {
    const telefono = input.value.trim();
    const regex = /^[0-9]+$/;
    if (!regex.test(telefono) || telefono.length > 10 || telefono.includes('.')) {
        input.value = "";
        mostrarAlerta("El telefono de contacto es invalido. Intente de nuevo.");
    }
}

