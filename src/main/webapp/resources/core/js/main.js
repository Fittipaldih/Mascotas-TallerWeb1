function mostrarMensaje() {

    const existingMessage = document.querySelector('.alert');
    if (!existingMessage) {
        const mensaje = document.createElement('div');
        mensaje.classList.add('alert', 'alert-warning');
        mensaje.textContent = 'Funcionalidad disponible proximamente. Disculpe las molestias';
        const mainContainer = document.getElementById('prox');
        mainContainer.insertBefore(mensaje, mainContainer.firstChild);
        mensaje.classList.add('in');

        setTimeout(() => mensaje.classList.remove('in'), 3500);
        setTimeout(() => mensaje.remove(), 3500);
    }
}
