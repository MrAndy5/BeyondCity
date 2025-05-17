// static/restaurant.js

document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
      window.location.href = '/index.html';
      return;
    }
  
    let restaurantId = null;
  
    // Intentamos cargar datos del restaurante
    fetch('/api/restaurants/me', {
      headers: {
        'Authorization': 'Bearer ' + token,
        'Content-Type': 'application/json'
      }
    })
    .then(res => {
      if (res.status === 404) {
        // No existe aún: formulario en modo "crear"
        return null;
      }
      if (!res.ok) throw new Error('No autorizado');
      return res.json();
    })
    .then(rest => {
      if (rest) {
        restaurantId = rest.id;
        document.getElementById('restName').value = rest.name;
        document.getElementById('restAddress').value = rest.address;
        document.getElementById('restCuisine').value = rest.cuisine;
      }
    })
    .catch(err => {
      console.error(err);
      alert('Error cargando restaurante: ' + err.message);
    });
  
    // Manejo de submit (crear o actualizar)
    document.getElementById('restaurantForm').addEventListener('submit', e => {
      e.preventDefault();
      const name = document.getElementById('restName').value;
      const address = document.getElementById('restAddress').value;
      const cuisine = document.getElementById('restCuisine').value;
  
      const payload = { name, address, cuisine };
  
      const url = restaurantId
        ? `/api/restaurants/${restaurantId}`
        : '/api/restaurants';
      const method = restaurantId ? 'PUT' : 'POST';
  
      fetch(url, {
        method,
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
      .then(saved => {
        restaurantId = saved.id;
        alert(restaurantId
          ? 'Restaurante actualizado'
          : 'Restaurante creado con ID ' + saved.id);
      })
      .catch(err => {
        console.error(err);
        alert('No se pudo guardar: ' + err.message);
      });
    });
  });
  