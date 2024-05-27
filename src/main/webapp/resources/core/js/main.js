function mostrarMensaje() {
    const existingMessage = document.querySelector('.alert');
    if (!existingMessage) {
        const mensaje = document.createElement('div');
        mensaje.classList.add('alert', 'alert-warning');
        mensaje.textContent = 'Funcionalidad disponible proximamente. Disculpe las molestias';

        mensaje.style.position = 'fixed';
        mensaje.style.top = '50px';
        mensaje.style.left = '50%';
        mensaje.style.transform = 'translateX(-50%)';
        mensaje.style.zIndex = '1050';

        const mainContainer = document.getElementById('prox');
        mainContainer.insertBefore(mensaje, mainContainer.firstChild);
        mensaje.classList.add('in');

        setTimeout(() => mensaje.classList.remove('in'), 3500);
        setTimeout(() => mensaje.remove(), 3500);
    }
}

