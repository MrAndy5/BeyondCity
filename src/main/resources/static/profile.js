// static/profile.js

document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
      // Redirige al login si no hay token
      window.location.href = '/index.html';
      return;
    }
  
    // Cargar datos del usuario
    fetch('/api/users/me', {
      headers: {
        'Authorization': 'Bearer ' + token,
        'Content-Type': 'application/json'
      }
    })
    .then(res => {
      if (!res.ok) throw new Error('No autorizado');
      return res.json();
    })
    .then(user => {
      document.getElementById('username').value = user.username;
      document.getElementById('email').value = user.email || '';
    })
    .catch(err => {
      console.error(err);
      alert('Error cargando perfil: ' + err.message);
      window.location.href = '/index.html';
    });
  
    // Enviar cambios
    document.getElementById('profileForm').addEventListener('submit', e => {
      e.preventDefault();
      const email = document.getElementById('email').value;
      const newPassword = document.getElementById('newPassword').value;
  
      const payload = { email };
      if (newPassword.trim()) {
        payload.password = newPassword;
      }
  
      fetch('/api/users/me', {
        method: 'PUT',
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })
      .then(res => {
        if (!res.ok) throw new Error('Error al guardar');
        return res.json();
      })
      .then(updated => {
        alert('Perfil actualizado correctamente');
        document.getElementById('newPassword').value = '';
      })
      .catch(err => {
        console.error(err);
        alert('No se pudo actualizar: ' + err.message);
      });
    });
  });
  