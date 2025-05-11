const baseUrl = "http://localhost:8080/api/auth"; // Cambia el puerto si es necesario

document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const username = document.getElementById("loginUsername").value;
  const password = document.getElementById("loginPassword").value;

  const response = await fetch(`${baseUrl}/signin`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password })
  });

  if (response.ok) {
    const data = await response.json();
    alert("Inicio de sesión exitoso. Token: " + data.token);
    // Aquí podrías guardar el token en localStorage y redirigir al dashboard
  } else {
    alert("Error al iniciar sesión");
  }
});

document.getElementById("registerForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const username = document.getElementById("registerUsername").value;
  const password = document.getElementById("registerPassword").value;
  const isRestaurant = document.getElementById("isRestaurant").checked;

  const response = await fetch(`${baseUrl}/signup`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password, restaurant: isRestaurant })
  });

  if (response.ok) {
    alert("Usuario registrado con éxito");
  } else {
    alert("Error al registrarse");
  }
});
