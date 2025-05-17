// static/js/home.js

document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
      // Si no hay token, redirige al login
      window.location.href = '/index.html';
      return;
    }
  
    // Inicializar el mapa
    const defaultCenter = [40.4168, -3.7038]; // Madrid
    const map = L.map('map').setView(defaultCenter, 13);
  
    // Intentar geolocalizar al usuario
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        pos => {
          const { latitude, longitude } = pos.coords;
          map.setView([latitude, longitude], 13);
        },
        err => {
          console.warn('Geolocalización no disponible:', err.message);
        }
      );
    }
  
    // Capa de teselas de OpenStreetMap
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);
  
    // Capa para marcadores
    const markersLayer = L.layerGroup().addTo(map);
  
    // Controles de filtros
    const searchInput    = document.getElementById('searchInput');
    const distanceRange  = document.getElementById('distanceRange');
    const distanceValue  = document.getElementById('distanceValue');
    const ratingRange    = document.getElementById('ratingRange');
    const ratingValue    = document.getElementById('ratingValue');
    const applyBtn       = document.getElementById('applyFilters');
  
    // Actualizar etiquetas de rango
    distanceRange.addEventListener('input', () => {
      distanceValue.textContent = `${distanceRange.value} km`;
    });
    ratingRange.addEventListener('input', () => {
      ratingValue.textContent = `${ratingRange.value} ★`;
    });
  
    // Obtener tipos de cocina seleccionados
    function getSelectedCuisines() {
      return Array.from(
        document.querySelectorAll('.filters-panel input[type=checkbox]:checked')
      ).map(cb => cb.value);
    }
  
    // Función para cargar y dibujar restaurantes
    async function loadRestaurants() {
      // Limpia marcadores previos
      markersLayer.clearLayers();
  
      // Parámetros de búsqueda
      const center = map.getCenter();
      const params = new URLSearchParams({
        lat: center.lat,
        lng: center.lng,
        maxDistanceKm: distanceRange.value,
        minRating: ratingRange.value
      });
      const cuisines = getSelectedCuisines();
      cuisines.forEach(c => params.append('cuisines', c));
      if (searchInput.value.trim()) {
        params.append('query', searchInput.value.trim());
      }
  
      try {
        const res = await fetch(`/api/restaurants?${params.toString()}`, {
          headers: { 'Authorization': 'Bearer ' + token }
        });
        if (!res.ok) throw new Error(`Error ${res.status}`);
        const data = await res.json();
  
        // Añadir marcador por cada restaurante
        data.forEach(r => {
          if (r.latitude && r.longitude) {
            const marker = L.marker([r.latitude, r.longitude])
              .bindPopup(`
                <strong>${r.name}</strong><br/>
                ${r.cuisine} — ${r.rating || 'Sin puntuación'} ★<br/>
                <button onclick="location.href='/restaurant-details.html?id=${r.id}'">
                  Ver detalles
                </button>
              `);
            markersLayer.addLayer(marker);
          }
        });
      } catch (err) {
        console.error('Error cargando restaurantes:', err);
        alert('No se pudieron cargar los restaurantes.');
      }
    }
  
    // Event listeners
    applyBtn.addEventListener('click', loadRestaurants);
    searchInput.addEventListener('keypress', e => {
      if (e.key === 'Enter') {
        e.preventDefault();
        loadRestaurants();
      }
    });
    map.on('moveend', loadRestaurants);
  
    // Carga inicial
    loadRestaurants();
  });
  